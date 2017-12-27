package com.yooyu.backend.manager;

import java.util.List;

import com.yooyu.backend.dto.PictureSearchDTO;
import com.yooyu.backend.dto.PictureUploadDTO;

public interface PictureManager {
	public boolean upload(PictureUploadDTO pictureUploadDTO);
	
	public List<String> getPicByCondition(PictureSearchDTO pictureSearchDTO);
}
