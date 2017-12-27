package com.yooyu.backend.manager;

import java.util.List;

import com.yooyu.backend.request.dto.PictureSearchDTO;
import com.yooyu.backend.request.dto.PictureUploadDTO;

public interface PictureManager {
	public boolean upload(PictureUploadDTO pictureUploadDTO);
	
	public List<String> getPicDatasByCondition(PictureSearchDTO pictureSearchDTO);
}
