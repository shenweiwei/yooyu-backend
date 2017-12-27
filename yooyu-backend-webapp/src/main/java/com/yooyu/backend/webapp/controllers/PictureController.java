package com.yooyu.backend.webapp.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yooyu.backend.common.exception.AppException;
import com.yooyu.backend.common.utils.BeanUtil;
import com.yooyu.backend.dto.PictureSearchDTO;
import com.yooyu.backend.dto.PictureUploadDTO;
import com.yooyu.backend.manager.PictureManager;
import com.yooyu.backend.webapp.vo.PictureSearchVO;
import com.yooyu.backend.webapp.vo.PictureUploadVO;



@Controller
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
	
	@GET
	@Path("/take")
	public void getPic(PictureSearchVO pictureSearchVO) throws AppException{
		PictureSearchDTO pictureSearchDTO=BeanUtil.map(pictureSearchVO, PictureSearchDTO.class);
		
		List<String> list = pictureManager.getPicByCondition(pictureSearchDTO);
	}
}
