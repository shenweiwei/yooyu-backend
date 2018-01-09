/*
* Copyright 2017 SAIC General Motors Corporation Ltd. All Rights Reserved.
*
* This software is published under the terms of the SGM Software
* License version 1.0, a copy of which has been included with this
* distribution in the LICENSE.txt file.
*
* @Project Name : ndol-job-rest
*
* @File name : CORSFilter.java
*
* @Author : s8xriw
*
* @Date : 2017年8月11日
*
----------------------------------------------------------------------------------
*     Date       Who       Version     Comments
* 1. 2017年8月11日    s8xriw    1.0
*
*
*
*
----------------------------------------------------------------------------------
*/

package com.yooyu.backend.webapp.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/*
* 解除跨域访问限制
* @author shenweiwei
* @date 2018年1月9日
*/
@Provider
public class CORSFilter implements ContainerResponseFilter {
	
	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
		response.getHeaders().add("Access-Control-Allow-Origin", "*");
		response.getHeaders().add("Access-Control-Max-Age", "1728000");
		response.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		response.getHeaders().add("Access-Control-Allow-Credentials", "true");
		response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
	}
}
