package com.yooyu.backend.common.exception;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 3741119815407858134L;

	public AppException(String msg) {
		super(msg);
	}

	public AppException(String msg, Exception ex) {
		super(msg, ex);
	}
}
