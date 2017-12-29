package com.yooyu.backend.manager;

import java.util.List;

import com.yooyu.backend.request.dto.PictureSearchDTO;
import com.yooyu.backend.request.dto.PictureUploadDTO;
import com.yooyu.backend.response.dto.PictureSearchResultDTO;
import com.yooyu.backend.response.dto.PictureUploadResultDTO;

public interface PictureManager {
	public PictureUploadResultDTO upload(PictureUploadDTO pictureUploadDTO);
	
	public List<PictureUploadResultDTO> multipleUpload(List<PictureUploadDTO> pictureUploadDTO);
	
	public List<PictureSearchResultDTO> getPicListByCondition(PictureSearchDTO pictureSearchDTO);
	
	public boolean deletePicByFileId(String fileId);
}
