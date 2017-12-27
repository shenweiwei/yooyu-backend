package com.yooyu.backend.request.dto;

/**
 * 图片上传DTO
 * 
 * @author shen_
 *
 */
public class PictureUploadDTO {
	private String data;
	private String fileName;
	private String fileType;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
}
