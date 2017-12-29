package com.yooyu.backend.response.dto;

public class PictureUploadResultDTO {
	private String fileId;
	private boolean result;
	
	public String getFileId() {
		return fileId;
	}
	public PictureUploadResultDTO setFileId(String fileId) {
		this.fileId = fileId;
		return this;
	}
	public boolean isResult() {
		return result;
	}
	public PictureUploadResultDTO setResult(boolean result) {
		this.result = result;
		return this;
	}
	
	public static PictureUploadResultDTO builder(){
		return new PictureUploadResultDTO();
	}
}
