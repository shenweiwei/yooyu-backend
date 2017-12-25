package com.yooyu.backend.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yooyu.backend.client.bucket.PictureBucket;
import com.yooyu.backend.reponsitory.PictureMapper;
import com.yooyu.backend.service.PictureService;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
@Transactional(rollbackFor=Exception.class)
public class PictureServiceImpl implements PictureService{
	@Autowired
	private PictureMapper pictureMapper;
	
	@Autowired
	private PictureBucket pictureBucket;

	@Override
	public boolean savePic() {
		System.out.println(234);
		int count=pictureMapper.upload();
		System.out.println(count);
		return true;
	}


	@Override
	public boolean uploadPicToAwsS3(String fileUri) {
		RequestBody requestBody=RequestBody.of(new File(fileUri));
		PutObjectResponse response=pictureBucket.putObject("aaa", requestBody);
		System.out.println(response);
		return true;
	}

}
