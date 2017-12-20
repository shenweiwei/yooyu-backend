package com.yooyu.backend.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yooyu.backend.manager.PictureManager;
import com.yooyu.backend.service.PictureService;

@Component
public class PictureManagerImpl implements PictureManager{
	@Autowired
	private PictureService pictureService;

	@Override
	public boolean upload() {
		boolean result=pictureService.upload();
		return result;
	}

}
