package com.yooyu.backend.service;

import java.io.File;
import java.util.List;

import com.yooyu.backend.request.dto.PictureSearchDTO;
import com.yooyu.backend.request.dto.PictureUploadDTO;
import com.yooyu.backend.response.dto.PictureSearchResultDTO;

import software.amazon.awssdk.core.sync.ResponseBytes;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public interface PictureService {

	/**
	 * 把图片信息到数据库
	 * 
	 * @param key
	 * @return
	 */
	public boolean savePic(String key);

	/**
	 * 把图片上传到AWS S3
	 * 
	 * @param pictureUploadDTO
	 * @return
	 */
	public PutObjectResponse uploadPicToAwsS3(PictureUploadDTO pictureUploadDTO, File file);

	/**
	 * 把图片上传到本地
	 * 
	 * @return
	 */
	public File uploadPicToDisk(PictureUploadDTO pictureUploadDTO);

	/**
	 * 根据条件获取到图片列表
	 * 
	 * @param pictureSearchDTO
	 * @return
	 */
	public List<PictureSearchResultDTO> getPicListByCondition(PictureSearchDTO pictureSearchDTO);

	/**
	 * 根据条件获取到图片列表总数
	 * 
	 * @param pictureSearchDTO
	 * @return
	 */
	public int getPicListByConditionCount(PictureSearchDTO pictureSearchDTO);

	/**
	 * 根据FileId获取到图片
	 * 
	 * @param key
	 * @return
	 */
	public ResponseBytes<GetObjectResponse> getPicByFileId(String key);

	/**
	 * 根据FileId从数据库删除图片
	 * 
	 * @param key
	 * @return
	 */
	public boolean deletePicByFileId(String key);

	/**
	 * 把图片从S3删除
	 * 
	 * @param key
	 * @return
	 */
	public DeleteObjectResponse deletePicToAwsS3(String key);

	/**
	 * 根据
	 * 
	 * @param pictureUploadDTO
	 * @return
	 */
	public boolean updatePicByFileId(PictureUploadDTO pictureUploadDTO, PutObjectResponse putObjectResponse);
}
