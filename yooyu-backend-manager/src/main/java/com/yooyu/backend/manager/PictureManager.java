package com.yooyu.backend.manager;

import com.yooyu.backend.dto.PictureUploadDTO;

public interface PictureManager {
	public boolean upload(PictureUploadDTO pictureUploadDTO);
}
