package com.yooyu.backend.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yooyu.backend.client.bucket.PictureBucket;
import com.yooyu.backend.common.exception.AppException;
import com.yooyu.backend.common.exception.BizException;
import com.yooyu.backend.common.utils.Base64Util;
import com.yooyu.backend.db.pojo.Picture;
import com.yooyu.backend.dto.PictureSearchDTO;
import com.yooyu.backend.dto.PictureUploadDTO;
import com.yooyu.backend.reponsitory.PictureMapper;
import com.yooyu.backend.service.PictureService;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseBytes;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
@Transactional(rollbackFor=Exception.class)
public class PictureServiceImpl implements PictureService{
	@Autowired
	private PictureMapper pictureMapper;
	
	@Autowired
	private PictureBucket pictureBucket;
	
	@Value("${app.id}")
	private String app_id;

	@Override
	public boolean savePic(PutObjectResponse response,String key) {
		//初始化图片对象
		Picture picture=initPicture(response,key);
		
		int count=pictureMapper.upload(picture);
		
		if(count < 0) throw new BizException("insert picture error");
		return true;
	}

	@Override
	public PutObjectResponse uploadPicToAwsS3(PictureUploadDTO pictureUploadDTO) {
		byte[] bytes=Base64Util.GenerateBytes(pictureUploadDTO.getData());
		RequestBody requestBody=RequestBody.of(bytes);
		PutObjectResponse response=pictureBucket.putObject(pictureUploadDTO.getFileName(), requestBody);
		if(response == null) throw new AppException("upload picture error");
		return response;
	}
	
	public Picture initPicture(PutObjectResponse response,String key) {
		Picture picture = Picture.builder()
				.setAppId(app_id)
				.setFileId(key)
				.seteTag(response.eTag())
				.setVersionId(response.versionId());
		return picture;
	}

	@Override
	public List<String> getPicByCondition(PictureSearchDTO pictureSearchDTO) {
		List<Picture> list=pictureMapper.findAll(pictureSearchDTO);
		List<String> imageList=new LinkedList<>();
		
		list.forEach( picture -> { 
			ResponseBytes<GetObjectResponse> response=pictureBucket.getObject(picture.getFileId());
			String image=Base64Util.GenerateBase64(response.asByteArray());
			imageList.add(image);
		});
		
		return imageList;
	}

}
