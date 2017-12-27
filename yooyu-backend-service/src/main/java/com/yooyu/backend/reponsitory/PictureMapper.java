package com.yooyu.backend.reponsitory;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yooyu.backend.db.pojo.Picture;
import com.yooyu.backend.dto.PictureSearchDTO;

@Repository
public interface PictureMapper {
	public int upload(Picture picture);

	public List<Picture> findAll(PictureSearchDTO pictureSearchDTO);
	
	public Picture find(@Param("fileId") String fileId);
}
