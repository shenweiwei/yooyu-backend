package com.yooyu.backend.service;

import java.util.List;

import com.yooyu.backend.request.dto.PictureSearchDTO;
import com.yooyu.backend.request.dto.PictureUploadDTO;
import com.yooyu.backend.response.dto.PictureSearchResultDTO;

import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public interface PictureService {

	public boolean savePic(PutObjectResponse response,String key);
	
	public PutObjectResponse uploadPicToAwsS3(PictureUploadDTO pictureUploadDTO);
	
	public List<PictureSearchResultDTO> getPicDatasByCondition(PictureSearchDTO pictureSearchDTO);
}
