package com.yooyu.backend.request.dto;

public class PictureSearchDTO {
	private InputPageParamDTO inputPage;

	public InputPageParamDTO getInputPage() {
		return inputPage;
	}

	public PictureSearchDTO setInputPage(InputPageParamDTO inputPage) {
		this.inputPage = inputPage;
		return this;
	}
	
	public static PictureSearchDTO builder(){
		return new PictureSearchDTO();
	}
}
