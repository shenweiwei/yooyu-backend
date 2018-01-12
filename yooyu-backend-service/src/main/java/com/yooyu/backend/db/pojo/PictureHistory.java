package com.yooyu.backend.db.pojo;

import java.util.Date;

public class PictureHistory {
	private long id;
	private String fileId;
	private String appId;
	private String url;
	private String diskLocation;
	private long userId;
	private long pictureId;
	private String remark;
	private Date createDate;
	private long createBy;
	private String eTag;
	private String versionId;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDiskLocation() {
		return diskLocation;
	}
	public void setDiskLocation(String diskLocation) {
		this.diskLocation = diskLocation;
	}
	public String geteTag() {
		return eTag;
	}
	public void seteTag(String eTag) {
		this.eTag = eTag;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
}
