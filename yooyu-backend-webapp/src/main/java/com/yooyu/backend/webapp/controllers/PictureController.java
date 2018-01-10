package com.yooyu.backend.webapp.controllers;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageInfo;
import com.yooyu.backend.common.exception.AppException;
import com.yooyu.backend.common.utils.BeanUtil;
import com.yooyu.backend.manager.PictureManager;
import com.yooyu.backend.request.dto.InputPageParamDTO;
import com.yooyu.backend.request.dto.PictureSearchConditionDTO;
import com.yooyu.backend.request.dto.PictureSearchDTO;
import com.yooyu.backend.request.dto.PictureUploadDTO;
import com.yooyu.backend.response.dto.PictureSearchResultDTO;
import com.yooyu.backend.webapp.request.vo.PictureSearchVO;
import com.yooyu.backend.webapp.request.vo.PictureUploadVO;
import com.yooyu.backend.webapp.response.vo.OutputPageParamVO;
import com.yooyu.backend.webapp.response.vo.PictureSearchResultVO;

@Controller
@Path("/picture")
public class PictureController {

	@Autowired
	private PictureManager pictureManager;

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void uploadPic(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition disposition,
			@HeaderParam("content-length") String contentLength) throws AppException {
		
		PictureUploadDTO pictureUploadDTO = initPictureUploadDTO(fileInputStream, disposition,contentLength);
		pictureManager.upload(pictureUploadDTO);
	}

	@POST
	@Path("/multiple_upload")
	public void uploadMultiplePic(List<PictureUploadVO> pictureUploadVOList) throws AppException {
		List<PictureUploadDTO> pictureUploadDTOList = BeanUtil.mapList(pictureUploadVOList, PictureUploadDTO.class);

		pictureManager.multipleUpload(pictureUploadDTOList);
	}

	@POST
	@Path("/take/datas")
	@Consumes("application/json;charset=utf-8")
	@Produces({ "application/json;charset=utf-8", MediaType.TEXT_PLAIN })
	public PageInfo<PictureSearchResultVO> getPicList(PictureSearchVO pictureSearchVO) throws AppException {
		PictureSearchDTO pictureSearchDTO = packagePictureSearchDTO(pictureSearchVO);

		List<PictureSearchResultDTO> datas = pictureManager.getPicListByCondition(pictureSearchDTO);

		return packagePageInfo(datas);
	}

	@DELETE
	@Path("/delete/{fileId}")
	@Consumes("application/json;charset=utf-8")
	@Produces({ "application/json;charset=utf-8", MediaType.TEXT_PLAIN })
	public void deletePic(@PathParam("fileId") String fileId) throws AppException {
		pictureManager.deletePicByFileId(fileId);
	}

	/**
	 * 包装pictureSearchDTO对象
	 * 
	 * @param pictureSearchVO
	 * @return
	 */
	private PictureSearchDTO packagePictureSearchDTO(PictureSearchVO pictureSearchVO) {
		InputPageParamDTO inputPageParamDTO = BeanUtil.map(pictureSearchVO.getInputPage(), InputPageParamDTO.class);
		PictureSearchConditionDTO pictureSearchConditionDTO = BeanUtil.map(pictureSearchVO.getPicture(),
				PictureSearchConditionDTO.class);
		PictureSearchDTO pictureSearchDTO = PictureSearchDTO.builder().setInputPage(inputPageParamDTO)
				.setPicture(pictureSearchConditionDTO);
		return pictureSearchDTO;
	}

	/**
	 * 包装PageInfo数据
	 * 
	 * @param datas
	 * @return
	 */
	private PageInfo<PictureSearchResultVO> packagePageInfo(List<PictureSearchResultDTO> datas) {
		List<PictureSearchResultVO> resultList = new LinkedList<>();

		datas.forEach(pictureSearchResultDTO -> {
			PictureSearchResultVO pictureSearchResultVO = BeanUtil.map(pictureSearchResultDTO,
					PictureSearchResultVO.class);
			resultList.add(pictureSearchResultVO);
		});

		return OutputPageParamVO.builder(resultList);
	}

	private PictureUploadDTO initPictureUploadDTO(InputStream fileInputStream, FormDataContentDisposition disposition,String contentLength) {
		System.out.println(contentLength);
		PictureUploadDTO pictureUploadDTO = PictureUploadDTO.builder().setContentLength(Long.parseLong(contentLength))
				.setInputStream(fileInputStream)
				.setFileName(disposition.getFileName())
				.setFileType(disposition.getFileName().substring(disposition.getFileName().indexOf(".")+1));

		return pictureUploadDTO;
	}
}
