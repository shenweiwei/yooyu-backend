package com.yooyu.backend.webapp.request.vo;

import com.yooyu.backend.webapp.request.vo.InputPageParamVO;

public class PictureSearchVO {
	private PictureSearchConditionVO picture;
	private InputPageParamVO inputPage;

	public PictureSearchConditionVO getPicture() {
		return picture;
	}

	public void setPicture(PictureSearchConditionVO picture) {
		this.picture = picture;
	}

	public InputPageParamVO getInputPage() {
		return inputPage;
	}

	public void setInputPage(InputPageParamVO inputPage) {
		this.inputPage = inputPage;
	}

}
