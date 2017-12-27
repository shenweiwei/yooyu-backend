package com.yooyu.backend.webapp.response.vo;

public class PictureSearchResultVO {
	private String url;
	private String data;
	
	public String getUrl() {
		return url;
	}
	public PictureSearchResultVO setUrl(String url) {
		this.url = url;
		return this;
	}
	public String getData() {
		return data;
	}
	public PictureSearchResultVO setData(String data) {
		this.data = data;
		return this;
	}

	public static PictureSearchResultVO builder(){
		return new PictureSearchResultVO();
	}
}
