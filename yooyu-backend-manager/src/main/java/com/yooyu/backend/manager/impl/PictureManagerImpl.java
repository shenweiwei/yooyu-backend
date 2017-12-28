package com.yooyu.backend.manager.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yooyu.backend.manager.PictureManager;
import com.yooyu.backend.request.dto.PictureSearchDTO;
import com.yooyu.backend.request.dto.PictureUploadDTO;
import com.yooyu.backend.response.dto.PictureSearchResultDTO;
import com.yooyu.backend.service.PictureService;

import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Component
public class PictureManagerImpl implements PictureManager{
	@Autowired
	private PictureService pictureService;

	@Override
	public boolean upload(PictureUploadDTO pictureUploadDTO) {
		pictureUploadDTO.setFileName(generatekey(pictureUploadDTO.getFileName()));
		
		PutObjectResponse response=pictureService.uploadPicToAwsS3(pictureUploadDTO);
		boolean saveResult=pictureService.savePic(response,pictureUploadDTO.getFileName());
		
		if(saveResult){
			return true;
		}
		
		return false;
	}
	

	@Override
	public List<PictureSearchResultDTO> getPicDatasByCondition(PictureSearchDTO pictureSearchDTO){
		List<PictureSearchResultDTO> datas=pictureService.getPicDatasByCondition(pictureSearchDTO);
		return datas;	
	}

	/**
	 * 生成KEY,规则是DATE_TEST.TYPE_UUID
	 * 
	 * @param filename
	 * @return
	 */
	private String generatekey(String filename){
		StringBuilder key=new StringBuilder();
		String date=new Timestamp(System.currentTimeMillis()).toString().substring(0,10);
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		key.append(date).append("_").append(uuid).append("_").append(filename);
		return key.toString();
	}

}
