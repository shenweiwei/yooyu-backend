//package com.yooyu.backend.manager.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.yooyu.backend.common.constants.AppConstant;
//import com.yooyu.backend.manager.PictureCacheManager;
//import com.yooyu.backend.request.dto.InputPageParamDTO;
//import com.yooyu.backend.request.dto.PictureSearchConditionDTO;
//import com.yooyu.backend.request.dto.PictureSearchDTO;
//import com.yooyu.backend.response.dto.PictureSearchResultDTO;
//import com.yooyu.backend.service.PictureCacheService;
//import com.yooyu.backend.service.PictureService;
//
//@Component
//public class PictureCacheManagerImpl implements PictureCacheManager{
//	
//	@Autowired
//	private PictureCacheService pictureCacheService;
//	
//	@Autowired
//	private PictureService pictureService;
//
//	@Override
//	public List<PictureSearchResultDTO> getPicListByCondition(PictureSearchDTO pictureSearchDTO) {
//		return pictureCacheService.getPicListByCondition(pictureSearchDTO);
//	}
//
//	@Override
//	public PictureSearchResultDTO getPicByFileId(String fileId) {
//		return pictureCacheService.getPicByFileId(fileId);
//	}
//
//	@Override
//	public int syncPicList() {
//		List<PictureSearchResultDTO> list = pictureService.getPicListByCondition(initPictureSearchDTO());
//		int count = pictureCacheService.savePicList(list);
//		return count;
//	}
//
//	private PictureSearchDTO initPictureSearchDTO() {
//		PictureSearchConditionDTO pictureSearchConditionDTO=new PictureSearchConditionDTO();
//		pictureSearchConditionDTO.setAppId(AppConstant.APP_ID);
//		PictureSearchDTO pictureSearchDTO = PictureSearchDTO.builder();
//		pictureSearchDTO.setInputPage(InputPageParamDTO.builder());
//		pictureSearchDTO.setPicture(pictureSearchConditionDTO);
//		return pictureSearchDTO;
//	}
//}
