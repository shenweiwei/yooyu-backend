package com.yooyu.backend.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yooyu.backend.dto.PictureUploadDTO;
import com.yooyu.backend.manager.PictureManager;
import com.yooyu.backend.service.PictureService;

import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Component
public class PictureManagerImpl implements PictureManager{
	@Autowired
	private PictureService pictureService;

	@Override
	public boolean upload(PictureUploadDTO pictureUploadDTO) {
		PutObjectResponse response=pictureService.uploadPicToAwsS3(pictureUploadDTO);
		boolean saveResult=pictureService.savePic(response,pictureUploadDTO.getFileName());
		
		if(saveResult){
			return true;
		}
		
		return false;
	}

}
