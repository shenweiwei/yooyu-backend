package com.yooyu.backend.service;

public interface PictureService {

	public boolean savePic();
	
	public boolean uploadPicToAwsS3(String fileUri);
}
