package com.yooyu.backend.manager;

import java.util.List;

import com.yooyu.backend.request.dto.PictureSearchDTO;
import com.yooyu.backend.request.dto.PictureUploadDTO;
import com.yooyu.backend.response.dto.PictureSearchResultDTO;

public interface PictureManager {
	public boolean upload(PictureUploadDTO pictureUploadDTO);
	
	public List<PictureSearchResultDTO> getPicDatasByCondition(PictureSearchDTO pictureSearchDTO);
}
