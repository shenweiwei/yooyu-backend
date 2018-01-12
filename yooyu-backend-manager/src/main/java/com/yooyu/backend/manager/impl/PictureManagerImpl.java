package com.yooyu.backend.manager.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yooyu.backend.common.constants.AppConstant;
import com.yooyu.backend.manager.PictureManager;
import com.yooyu.backend.request.dto.InputPageParamDTO;
import com.yooyu.backend.request.dto.PictureSearchConditionDTO;
import com.yooyu.backend.request.dto.PictureSearchDTO;
import com.yooyu.backend.request.dto.PictureUploadDTO;
import com.yooyu.backend.response.dto.PictureSearchResultDTO;
import com.yooyu.backend.response.dto.PictureUploadResultDTO;
import com.yooyu.backend.service.PictureCacheService;
import com.yooyu.backend.service.PictureHistoryService;
import com.yooyu.backend.service.PictureService;

@Component
@Transactional(rollbackFor = Exception.class)
public class PictureManagerImpl implements PictureManager {
	@Autowired
	private PictureService pictureService;
	@Autowired
	private PictureHistoryService pictureHistoryService;
	@Autowired
	private PictureCacheService pictureCacheService;

	@Override
	public PictureUploadResultDTO upload(PictureUploadDTO pictureUploadDTO) {
		pictureUploadDTO.setFileId(generatekey(pictureUploadDTO.getFileName()));

		final File file = pictureService.uploadPicToDisk(pictureUploadDTO);
		pictureService.savePic(pictureUploadDTO.getFileId(), file.getPath());

		PictureUploadResultDTO pictureUploadResultDTO = PictureUploadResultDTO.builder()
				.setFileId(pictureUploadDTO.getFileId());

		pictureService.uploadPicToAwsS3(pictureUploadDTO, file);

		return pictureUploadResultDTO.setResult(true);
	}

	@Override
	public List<PictureUploadResultDTO> multipleUpload(List<PictureUploadDTO> pictureUploadDTOList) {
		List<PictureUploadResultDTO> list = new LinkedList<>();

		pictureUploadDTOList.forEach(pictureUploadDTO -> {
			pictureUploadDTO.setFileId(generatekey(pictureUploadDTO.getFileName()));

			final File file = pictureService.uploadPicToDisk(pictureUploadDTO);
			pictureService.savePic(pictureUploadDTO.getFileId(), file.getPath());

			PictureUploadResultDTO pictureUploadResultDTO = PictureUploadResultDTO.builder()
					.setFileId(pictureUploadDTO.getFileId());

			pictureService.uploadPicToAwsS3(pictureUploadDTO, file);

			list.add(pictureUploadResultDTO.setResult(true));
		});

		return list;
	}

	@Override
	public List<PictureSearchResultDTO> getPicListByCondition(PictureSearchDTO pictureSearchDTO) {
		int dbCount = pictureService.getPicListByConditionCount(pictureSearchDTO);
		int cachedCount = pictureCacheService.getPicListByConditionCount(pictureSearchDTO);

		List<PictureSearchResultDTO> datas = null;
		if (dbCount == cachedCount) {
			datas = pictureCacheService.getPicListByCondition(pictureSearchDTO);
		} else {
			datas = pictureService.getPicListByCondition(pictureSearchDTO, false);

			// 同步到缓存Redis里
			this.syncRedis();
		}

		return datas;
	}

	/**
	 * 生成KEY,规则是DATE_TEST.TYPE_UUID
	 * 
	 * @param filename
	 * @return
	 */
	private String generatekey(String filename) {
		StringBuilder key = new StringBuilder();
		String date = new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		key.append(date).append("_").append(uuid).append("_").append(filename);
		return key.toString();
	}

	@Override
	public boolean deletePicByFileId(String fileId) {
		// 保存到历史表
		pictureHistoryService.save(fileId);
		// 删除图片表
		pictureService.deletePicByFileId(fileId);

		return true;
	}

	private PictureSearchDTO initPictureSearchDTO() {
		PictureSearchConditionDTO pictureSearchConditionDTO = new PictureSearchConditionDTO();
		pictureSearchConditionDTO.setAppId(AppConstant.APP_ID);
		PictureSearchDTO pictureSearchDTO = PictureSearchDTO.builder();
		pictureSearchDTO.setInputPage(InputPageParamDTO.builder());
		pictureSearchDTO.setPicture(pictureSearchConditionDTO);
		return pictureSearchDTO;
	}

	@Async("threadPoolExecuter")
	private void syncRedis() {
		List<PictureSearchResultDTO> list = pictureService.getPicListByCondition(initPictureSearchDTO(), true);
		pictureCacheService.savePicList(list);
	}
}
