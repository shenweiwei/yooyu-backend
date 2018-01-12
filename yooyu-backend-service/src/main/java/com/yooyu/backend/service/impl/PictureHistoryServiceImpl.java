package com.yooyu.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyu.backend.common.exception.AppException;
import com.yooyu.backend.common.utils.BeanUtil;
import com.yooyu.backend.db.pojo.Picture;
import com.yooyu.backend.db.pojo.PictureHistory;
import com.yooyu.backend.reponsitory.PictureHistoryMapper;
import com.yooyu.backend.reponsitory.PictureMapper;
import com.yooyu.backend.service.PictureHistoryService;

@Service
public class PictureHistoryServiceImpl implements PictureHistoryService{
	
	@Autowired
	private PictureHistoryMapper pictureHistoryMapper;
	
	@Autowired
	private PictureMapper pictureMapper;

	@Override
	public boolean save(String fileId) {
		Picture picture=pictureMapper.find(fileId);
		PictureHistory pictureHistory=BeanUtil.map(picture, PictureHistory.class);
		pictureHistory.setPictureId(picture.getId());
		int count=pictureHistoryMapper.save(pictureHistory);
		
		if(count<=0) throw new AppException("save picture history error");
		
		return true;
	}

}
