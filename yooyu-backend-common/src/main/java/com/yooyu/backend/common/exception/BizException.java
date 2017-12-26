package com.yooyu.backend.common.exception;

public class BizException extends RuntimeException{

	private static final long serialVersionUID = -2052681474685586081L;

	public BizException(String msg) {
		super(msg);
	}

	public BizException(String msg, Exception ex) {
		super(msg, ex);
	}
}
