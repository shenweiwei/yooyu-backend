package com.yooyu.backend.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.yooyu.backend.client.bucket.PictureBucket;
import com.yooyu.backend.common.exception.AppException;
import com.yooyu.backend.common.exception.BizException;
import com.yooyu.backend.common.utils.BeanUtil;
import com.yooyu.backend.common.utils.PageUtil;
import com.yooyu.backend.db.pojo.Picture;
import com.yooyu.backend.reponsitory.PictureMapper;
import com.yooyu.backend.request.dto.PictureSearchDTO;
import com.yooyu.backend.request.dto.PictureUploadDTO;
import com.yooyu.backend.response.dto.PictureSearchResultDTO;
import com.yooyu.backend.service.PictureService;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseBytes;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
public class PictureServiceImpl extends BaseService implements PictureService {
	@Autowired
	private PictureMapper pictureMapper;

	@Autowired
	private PictureBucket pictureBucket;

	@Value("${app.id}")
	private String app_id;

	@Value("${app.pic-disk-location}")
	private String pic_disk_location;

	@Value("${app.pic-disk-url-location}")
	private String pic_disk_url_location;

	@Override
	public boolean savePic(String key, String filePath) {
		// 初始化图片对象
		Picture picture = initPicture(key, filePath);

		int count = pictureMapper.upload(picture);

		if (count <= 0)
			throw new BizException("insert picture error");
		return true;
	}

	@Override
	@Async("threadPoolExecuter")
	public PutObjectResponse uploadPicToAwsS3(PictureUploadDTO pictureUploadDTO, File file) {
		RequestBody requestBody = RequestBody.of(file);

		PutObjectResponse response = pictureBucket.putObject(pictureUploadDTO.getFileId(), requestBody);
		if (response == null)
			throw new AppException("upload picture error");

		this.updatePicByFileId(pictureUploadDTO, response);
		return response;
	}

	@Override
	public File uploadPicToDisk(PictureUploadDTO pictureUploadDTO) {
		String dirPath = this.createNowDayDir();

		try {
			File picFile = new File(dirPath.concat(pictureUploadDTO.getFileId()));
			FileUtils.copyInputStreamToFile(pictureUploadDTO.getInputStream(), picFile);
			return picFile;
		} catch (IOException e) {
			throw new AppException("upload picture to disk error");
		}
	}

	@Override
	public List<PictureSearchResultDTO> getPicListByCondition(PictureSearchDTO pictureSearchDTO, boolean ischeck) {
		List<Picture> list = pictureMapper.findAll(pictureSearchDTO.getPicture(),
				PageUtil.getPage(pictureSearchDTO.getInputPage()));
		List<PictureSearchResultDTO> imageList = new ArrayList<>();

		list.forEach(picture -> {
			if(ischeck) this.checkLocationDiskFile(picture);

			PictureSearchResultDTO pictureSearchResultDTO = BeanUtil.map(picture, PictureSearchResultDTO.class);
			imageList.add(pictureSearchResultDTO);
		});

		if (list != null && !list.isEmpty() && imageList.isEmpty()) {
			throw new BizException("get pic datas is error");
		}

		return imageList;
	}

	@Override
	public boolean deletePicByFileId(String key) {
		int count = pictureMapper.delete(key);

		if (count <= 0)
			throw new BizException("insert picture error");

		return true;
	}

	@Override
	public DeleteObjectResponse deletePicToAwsS3(String key) {
		DeleteObjectResponse response = pictureBucket.deleteObject(key);
		if (response == null)
			throw new AppException("delete picture error");
		return response;
	}

	@Override
	public ResponseBytes<GetObjectResponse> getPicByFileId(String key) {
		ResponseBytes<GetObjectResponse> response = pictureBucket.getObject(key);
		if (response == null)
			throw new AppException("get picture error");
		return response;
	}

	@Override
	public int getPicListByConditionCount(PictureSearchDTO pictureSearchDTO) {
		int count = pictureMapper.findAllCount(pictureSearchDTO.getPicture());
		return count;
	}

	private Picture initPicture(String key, String filePath) {
		String[] paths = filePath.split("\\\\");
		String fileName = paths[paths.length - 1];
		String childPath = paths[paths.length - 2];
		StringBuffer fullPath = new StringBuffer(pic_disk_url_location).append(childPath).append("/").append(fileName);

		Picture picture = Picture.builder().setAppId(app_id).setFileId(key).setUrl(fullPath.toString())
				.setDiskLocation(filePath);
		return picture;
	}

	@Override
	public boolean updatePicByFileId(PictureUploadDTO pictureUploadDTO, PutObjectResponse response) {
		Picture picture = Picture.builder().setFileId(pictureUploadDTO.getFileId()).seteTag(response.eTag())
				.setVersionId(response.versionId()).setRemark("synced");
		int count = pictureMapper.update(picture);

		return count <= 0 ? false : true;
	}

	/**
	 * 创建当前日期的目录（精确到天）
	 * 
	 * @return file path
	 */
	private String createNowDayDir() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String date = ts.toString().substring(0, 10);
		String path = pic_disk_location.concat(date).concat("/");
		File file = new File(path);

		try {
			FileUtils.forceMkdir(file);
		} catch (IOException e) {
			throw new AppException("mkdir error");
		}

		return path;
	}

	/**
	 * 验证文件是否在本地
	 * 
	 * @param filePath
	 * @param fileId
	 */
	@Async("threadPoolExecuter")
	private void checkLocationDiskFile(Picture picture) {
		File file = FileUtils.getFile(picture.getDiskLocation());
		if (!file.exists() && !file.isDirectory()) {
			ResponseBytes<GetObjectResponse> response = pictureBucket.getObject(picture.getFileId());
			try {
				FileUtils.writeByteArrayToFile(file, response.asByteArray());
			} catch (IOException e) {
				throw new AppException("byte array to file error", e);
			}
		}
	}
}
