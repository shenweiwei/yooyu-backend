package com.yooyu.backend.reponsitory;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.yooyu.backend.db.pojo.Picture;
import com.yooyu.backend.request.dto.PictureSearchConditionDTO;

@Repository
public interface PictureMapper {
	public int upload(Picture picture);
	
	public int delete(@Param("fileId") String fileId);

	public List<Picture> findAll(@Param("ps") PictureSearchConditionDTO pictureSearchConditionDTO,Page<?> page);
	
	public Picture find(@Param("fileId") String fileId);
}
