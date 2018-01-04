package com.yooyu.backend.webapp.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yooyu.backend.common.exception.AppException;
import com.yooyu.backend.common.exception.BizException;
import com.yooyu.backend.common.utils.BeanUtil;
import com.yooyu.backend.manager.PictureCacheManager;
import com.yooyu.backend.request.dto.InputPageParamDTO;
import com.yooyu.backend.request.dto.PictureSearchConditionDTO;
import com.yooyu.backend.request.dto.PictureSearchDTO;
import com.yooyu.backend.response.dto.PictureSearchResultDTO;
import com.yooyu.backend.webapp.request.vo.PictureSearchVO;
import com.yooyu.backend.webapp.response.vo.OutputPageParamVO;
import com.yooyu.backend.webapp.response.vo.PictureSearchResultVO;

@RestController
@Path("/picture/cache")
@Produces({ "application/json;charset=utf-8", MediaType.TEXT_PLAIN })
@Consumes("application/json;charset=utf-8")
public class PictureCacheController {
	
	@Autowired
	private PictureCacheManager pictureCacheManager;

	@GET
	@Path("/sync")
	public void syncPicList() throws AppException {
		pictureCacheManager.syncPicList();
	}
	
	@GET
	@Path("/take/{fileId}")
	public PictureSearchResultVO getPicByFileId(@PathParam("fileId") String fileId) throws AppException {
		if(fileId == null || "".equals(fileId)){
			throw new BizException("fileId is not empty");
		}
		
		PictureSearchResultDTO pictureSearchResultDTO=pictureCacheManager.getPicByFileId(fileId);
		PictureSearchResultVO pictureSearchResultVO = BeanUtil.map(pictureSearchResultDTO, PictureSearchResultVO.class);
		return pictureSearchResultVO;
	}
	
	@POST
	@Path("/take/datas")
	public PageInfo<PictureSearchResultVO> getPicByFileId(PictureSearchVO pictureSearchVO) throws AppException {
		if(pictureSearchVO == null){
			throw new BizException("picture search object is not empty");
		}
		
		PictureSearchDTO pictureSearchDTO = packagePictureSearchDTO(pictureSearchVO);
		List<PictureSearchResultDTO> datas = pictureCacheManager.getPicListByCondition(pictureSearchDTO);
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
	private PageInfo<PictureSearchResultVO> packagePageInfo(List<PictureSearchResultDTO> datas){
		List<PictureSearchResultVO> resultList=new LinkedList<>();
		
		datas.forEach( pictureSearchResultDTO -> {
			PictureSearchResultVO pictureSearchResultVO = BeanUtil.map(pictureSearchResultDTO, PictureSearchResultVO.class);
			resultList.add(pictureSearchResultVO);
		});
		
		return OutputPageParamVO.builder(resultList);
	}
	
}
