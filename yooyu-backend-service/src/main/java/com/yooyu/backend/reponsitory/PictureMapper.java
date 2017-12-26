package com.yooyu.backend.reponsitory;

import org.springframework.stereotype.Repository;

import com.yooyu.backend.db.pojo.Picture;

@Repository
public interface PictureMapper {
	public int upload(Picture picture);

}
