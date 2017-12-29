package com.yooyu.backend.reponsitory;

import org.springframework.stereotype.Repository;

import com.yooyu.backend.db.pojo.PictureHistory;

@Repository
public interface PictureHistoryMapper {
	public int save(PictureHistory pictureHistory);
}
