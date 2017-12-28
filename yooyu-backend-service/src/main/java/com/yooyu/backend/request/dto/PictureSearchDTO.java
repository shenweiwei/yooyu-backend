package com.yooyu.backend.request.dto;

public class PictureSearchDTO {
	private PictureSearchConditionDTO picture;
	private InputPageParamDTO inputPage;

	
	public PictureSearchConditionDTO getPicture() {
		return picture;
	}

	public PictureSearchDTO setPicture(PictureSearchConditionDTO picture) {
		this.picture = picture;
		return this;
	}

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
