//package com.yooyu.backend.service;
//
//import java.util.List;
//
//import com.yooyu.backend.request.dto.PictureSearchDTO;
//import com.yooyu.backend.response.dto.PictureSearchResultDTO;
//
//public interface PictureCacheService {
//	/**
//	 * 根据条件获取到图片列表
//	 * 
//	 * @param pictureSearchDTO
//	 * @return
//	 */
//	public List<PictureSearchResultDTO> getPicListByCondition(PictureSearchDTO pictureSearchDTO);
//	
//	/**
//	 * 根据FileId获取到图片
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public PictureSearchResultDTO getPicByFileId(String key); 
//	
//	/**
//	 * 保存图片信息到Redis
//	 * 
//	 * @param list
//	 * @return
//	 */
//	public int savePicList(List<PictureSearchResultDTO> list);
//}
