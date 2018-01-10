//package com.yooyu.backend.webapp.filters;
//
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.ws.rs.HttpMethod;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.ext.Provider;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import com.yooyu.backend.common.constants.AppConstant;
//import com.yooyu.backend.common.utils.BufferingRequestWrapper;
//
//
///**
// * @author shenweiwei
// * @date 2018年1月9日
// */
//@Provider
//public class RequestCommonLogsFilter implements ContainerRequestFilter {
//	private Logger logger = LogManager.getLogger(this.getClass());
//	
//	// 文件白名单
//	private List<String> excludeFileList = new ArrayList<String>();
//	
//	private static boolean inited = false;
//	
//    @Context   
//    private HttpServletRequest servletRequest;  
//    @Context  
//    private HttpServletResponse servletResponse; 
//    
//	public void init(){
//		// 初始化文件白名单
//		for (String file : AppConstant.REQUEST_WHITE_LIST) {
//			if (!"".equals(file = file.trim()))
//				excludeFileList.add(file);
//		}
//		
//		inited=true;
//	}
//
//	@Override
//	public void filter(ContainerRequestContext requestContext) throws IOException {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//		if(!inited) this.init();
//		
//		
//		if(HttpMethod.GET.equals(httpServletRequest.getMethod())){//GET请求不打印日志
//			return;
//		}
//		
//		/*
//		 * 把servletRequest包装成可多次读取body的BufferingRequestWrapper
//		 */
//		BufferingRequestWrapper bufferingRequestWrapper = new BufferingRequestWrapper((HttpServletRequest) httpServletRequest);
//		
//		// 排除不需要检查的文件类型
//		String upperCaseURI = bufferingRequestWrapper.getRequestURI().toUpperCase();
//		for (String excludeFile : excludeFileList) {
//			if (upperCaseURI.endsWith(excludeFile.toUpperCase())) {
//				return;
//			}
//		}
//		
//		/*
//		 * 打印请求日志
//		 */
//		StringBuilder requestMsg = new StringBuilder("\n---------------------------------------------------------\n");
//		//起始行
//		requestMsg.append(bufferingRequestWrapper.getProtocol()).append(" ");//协议
//		requestMsg.append(bufferingRequestWrapper.getRequestURI());//URL
//		Enumeration<?> parameterNames = bufferingRequestWrapper.getParameterNames();//Parameter参数
//		StringBuilder parameterStr = new StringBuilder();
//		while(parameterNames.hasMoreElements()){
//			Object nextElement = parameterNames.nextElement();
//			if (parameterStr.length() == 0)
//				parameterStr.append("?");
//			else
//				parameterStr.append("&");
//			parameterStr.append(nextElement).append("=").append(bufferingRequestWrapper.getParameter(nextElement.toString()));
//		}
//		requestMsg.append(parameterStr).append(" ");
//		
//		requestMsg.append(bufferingRequestWrapper.getMethod());//METHOD
//		requestMsg.append("\n");
//		//Header头
//		Enumeration<?> headerNames = bufferingRequestWrapper.getHeaderNames();
//		while(headerNames.hasMoreElements()){
//			String headerName = (String)headerNames.nextElement();
//			requestMsg.append(headerName).append(":").append(bufferingRequestWrapper.getHeader(headerName)).append("\n");
//		}
//		requestMsg.append("\n");
//		//body
//		BufferedReader reader = bufferingRequestWrapper.getReader();
//		String lineStr = null;
//		StringBuilder bodyStr = new StringBuilder();
//		while((lineStr = reader.readLine()) != null){
//			bodyStr.append(lineStr);
//		}
//		requestMsg.append(bodyStr);
//		requestMsg.append("\n---------------------------------------------------------");
//		logger.info(requestMsg);
//	}
//}
