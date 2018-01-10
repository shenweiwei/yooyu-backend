package com.yooyu.backend.webapp.controllers;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
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
import com.yooyu.backend.response.dto.PictureSearchResultDTO;
import com.yooyu.backend.webapp.request.vo.PictureSearchVO;
import com.yooyu.backend.webapp.request.vo.PictureUploadVO;
import com.yooyu.backend.webapp.response.vo.OutputPageParamVO;
import com.yooyu.backend.webapp.response.vo.PictureSearchResultVO;

@RestController
@Path("/picture")
@Produces({ "application/json;charset=utf-8", MediaType.TEXT_PLAIN })
public class PictureController {

	@Autowired
	private PictureManager pictureManager;

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void uploadPic(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition disposition) throws AppException {

		PictureUploadDTO pictureUploadDTO = initPictureUploadDTO(fileInputStream, disposition);
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
	public PageInfo<PictureSearchResultVO> getPicList(PictureSearchVO pictureSearchVO) throws AppException {
		PictureSearchDTO pictureSearchDTO = packagePictureSearchDTO(pictureSearchVO);

		List<PictureSearchResultDTO> datas = pictureManager.getPicListByCondition(pictureSearchDTO);

		return packagePageInfo(datas);
	}

	@DELETE
	@Path("/delete/{fileId}")
	@Consumes("application/json;charset=utf-8")
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

	private PictureUploadDTO initPictureUploadDTO(InputStream fileInputStream, FormDataContentDisposition disposition) {
		PictureUploadDTO pictureUploadDTO = PictureUploadDTO.builder().setContentLength(disposition.getSize())
				.setInputStream(fileInputStream)
				.setFileName(disposition.getFileName())
				.setFileType(disposition.getType());

		return pictureUploadDTO;
	}
}
