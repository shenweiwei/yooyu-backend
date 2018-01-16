package com.yooyu.backend.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.yooyu.backend.common.exception.BizException;
import com.yooyu.backend.request.dto.InputPageParamDTO;
import com.yooyu.backend.request.dto.PictureSearchDTO;
import com.yooyu.backend.response.dto.PictureSearchResultDTO;
import com.yooyu.backend.service.PictureCacheService;

@Service
public class PictureCacheServiceImpl extends BaseService implements PictureCacheService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, PictureSearchResultDTO> valueOperations;

	@Override
	public List<PictureSearchResultDTO> getPicListByCondition(PictureSearchDTO pictureSearchDTO) {
		super.changePictureRedisDB();
		Set<String> keys = redisTemplate.keys("*");

		List<PictureSearchResultDTO> pictureSearchResultDTOList = setResultList(pictureSearchDTO.getInputPage(), keys);

		super.changeSessionRedisDB();
		return pictureSearchResultDTOList;
	}

	@Override
	public PictureSearchResultDTO getPicByFileId(String key) {
		super.changePictureRedisDB();
		PictureSearchResultDTO pictureSearchResultDTO = valueOperations.get(key);
		if (pictureSearchResultDTO == null)
			throw new BizException("get picture is not exist");
		
		super.changeSessionRedisDB();
		return pictureSearchResultDTO;
	}

	@Override
	public int savePicList(List<PictureSearchResultDTO> list) {
		int count = 0;
		
		super.changePictureRedisDB();
		
		list.forEach(pictureSearchResultDTO -> {
			boolean exists = redisTemplate.hasKey(pictureSearchResultDTO.getFileId());
			
			if(!exists){
				valueOperations.set(pictureSearchResultDTO.getFileId(), pictureSearchResultDTO);
			}
		});

		super.changeSessionRedisDB();
		return count;
	}

	private List<PictureSearchResultDTO> setResultList(InputPageParamDTO inputPage, Set<String> keys) {
		List<PictureSearchResultDTO> pictureSearchResultDTOList = new LinkedList<>();
		int pageNum = inputPage.getPageNum();
		int pageSize = inputPage.getPageSize();
		int start = pageNum * pageSize - pageSize +1;
		int end = pageNum * pageSize;

		int count = 1;
		keys.forEach(key -> {
			if (count > end) {
				return;
			} else if (count >= start) {
				PictureSearchResultDTO pictureSearchResultDTO = valueOperations.get(key);
				pictureSearchResultDTOList.add(pictureSearchResultDTO);
			}
		});
		
		return pictureSearchResultDTOList;
	}

	@Override
	public int getPicListByConditionCount(PictureSearchDTO pictureSearchDTO) {
		super.changePictureRedisDB();
		Set<String> keys = redisTemplate.keys("*");
		super.changeSessionRedisDB();
		return keys.size();
	}
}
