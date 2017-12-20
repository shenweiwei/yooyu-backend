package com.yooyu.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yooyu.backend.reponsitory.PictureMapper;
import com.yooyu.backend.service.PictureService;

@Service
@Transactional(rollbackFor=Exception.class)
public class PictureServiceImpl implements PictureService{
	@Autowired
	private PictureMapper pictureMapper;

	@Override
	public boolean upload() {
		System.out.println(234);
		int count=pictureMapper.upload();
		System.out.println(count);
		return true;
	}

}
