package com.yooyu.backend.service;

import com.yooyu.backend.dto.PictureUploadDTO;

import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public interface PictureService {

	public boolean savePic(PutObjectResponse response,String key);
	
	public PutObjectResponse uploadPicToAwsS3(PictureUploadDTO pictureUploadDTO);
}
