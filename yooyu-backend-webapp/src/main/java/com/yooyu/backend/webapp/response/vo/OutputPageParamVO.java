package com.yooyu.backend.webapp.response.vo;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class OutputPageParamVO<T> extends PageInfo<T>{

	private static final long serialVersionUID = 7400652538914470025L;
	
	public OutputPageParamVO(List<T> t) {
		super(t);
		
	}
	
	public static <T> OutputPageParamVO<T> builder(List<T> t){
		return new OutputPageParamVO<T>(t);
	}

}
