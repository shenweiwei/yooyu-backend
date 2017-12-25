package com.yooyu.backend.webapp.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yooyu.backend.common.exception.AppException;
import com.yooyu.backend.manager.PictureManager;



@Controller
@Path("/picture")
@Produces({ "application/json;charset=utf-8", MediaType.TEXT_PLAIN })
@Consumes("application/json;charset=utf-8")
public class PictureController {
	@Autowired
	private PictureManager pictureManager;

	@GET
	@Path("/upload")
	public void upload() throws AppException{
		pictureManager.upload("C:\\log\\agentAll.log");
	}
}
