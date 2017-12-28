package com.yooyu.backend.webapp.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yooyu.backend.common.exception.AppException;
import com.yooyu.backend.common.utils.BeanUtil;
import com.yooyu.backend.manager.PictureManager;
import com.yooyu.backend.request.dto.InputPageParamDTO;
import com.yooyu.backend.request.dto.PictureSearchConditionDTO;
import com.yooyu.backend.request.dto.PictureSearchDTO;
import com.yooyu.backend.request.dto.PictureUploadDTO;
import com.yooyu.backend.webapp.request.vo.PictureSearchVO;
import com.yooyu.backend.webapp.request.vo.PictureUploadVO;
import com.yooyu.backend.webapp.response.vo.OutputPageParamVO;
import com.yooyu.backend.webapp.response.vo.PictureSearchResultVO;

@RestController
@Path("/picture")
@Produces({ "application/json;charset=utf-8", MediaType.TEXT_PLAIN })
@Consumes("application/json;charset=utf-8")
public class PictureController {
	
	@Autowired
	private PictureManager pictureManager;

	@POST
	@Path("/upload")
	public void uploadPic(PictureUploadVO pictureUploadVO) throws AppException{
		PictureUploadDTO pictureUploadDTO=BeanUtil.map(pictureUploadVO, PictureUploadDTO.class);
		
		pictureManager.upload(pictureUploadDTO);
	}
	
	@POST
	@Path("/take/datas")
	public PageInfo<PictureSearchResultVO> getPicUrls(PictureSearchVO pictureSearchVO) throws AppException{
		PictureSearchDTO pictureSearchDTO = packagePictureSearchDTO(pictureSearchVO);
		
		List<String> datas = pictureManager.getPicDatasByCondition(pictureSearchDTO);
		
		return packagePageInfo(datas);
	}
	
	/**
	 * 包装pictureSearchDTO对象
	 * 
	 * @param pictureSearchVO
	 * @return
	 */
	private PictureSearchDTO packagePictureSearchDTO(PictureSearchVO pictureSearchVO){
		InputPageParamDTO inputPageParamDTO = BeanUtil.map(pictureSearchVO.getInputPage(), InputPageParamDTO.class);
		PictureSearchConditionDTO pictureSearchConditionDTO = BeanUtil.map(pictureSearchVO.getPicture(), PictureSearchConditionDTO.class);
		PictureSearchDTO pictureSearchDTO = PictureSearchDTO.builder().setInputPage(inputPageParamDTO).setPicture(pictureSearchConditionDTO);				
		return pictureSearchDTO;
	}

	/**
	 * 包装PageInfo数据
	 * 
	 * @param datas
	 * @return
	 */
	private PageInfo<PictureSearchResultVO> packagePageInfo(List<String> datas){
		List<PictureSearchResultVO> resultList=new LinkedList<>();
		
		datas.forEach(data -> {
			resultList.add(PictureSearchResultVO.builder().setData(data));
		});
		
		return OutputPageParamVO.builder(resultList);
	}
}
