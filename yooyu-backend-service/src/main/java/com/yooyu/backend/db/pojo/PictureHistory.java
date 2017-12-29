package com.yooyu.backend.db.pojo;

import java.util.Date;

public class PictureHistory {
	private long id;
	private String fileId;
	private String appId;
	private String data;
	private long userId;
	private long pictureId;
	private String remark;
	private Date createDate;
	private long createBy;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPictureId() {
		return pictureId;
	}
	public void setPictureId(long pictureId) {
		this.pictureId = pictureId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}
}
