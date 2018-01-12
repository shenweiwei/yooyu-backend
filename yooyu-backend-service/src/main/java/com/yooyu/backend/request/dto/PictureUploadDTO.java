package com.yooyu.backend.request.dto;

import java.io.InputStream;

/**
 * 图片上传DTO
 * 
 * @author shen_
 *
 */
public class PictureUploadDTO {
	private String fileName;
	private String fileId;
	private String fileType;
	private InputStream inputStream;
	private long contentLength;
	
	public String getFileName() {
		return fileName;
	}
	public PictureUploadDTO setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}
	public String getFileType() {
		return fileType;
	}
	public PictureUploadDTO setFileType(String fileType) {
		this.fileType = fileType;
		return this;
	}
	public String getFileId() {
		return fileId;
	}
	public PictureUploadDTO setFileId(String fileId) {
		this.fileId = fileId;
		return this;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public PictureUploadDTO setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
		return this;
	}
	public long getContentLength() {
		return contentLength;
	}
	public PictureUploadDTO setContentLength(long contentLength) {
		this.contentLength = contentLength;
		return this;
	}	
	public static PictureUploadDTO builder() {
		return new PictureUploadDTO();
	}
}
