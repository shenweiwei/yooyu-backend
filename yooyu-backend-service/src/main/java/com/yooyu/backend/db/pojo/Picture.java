package com.yooyu.backend.db.pojo;

import java.util.Date;

public class Picture {
	private long id;
	private String fileId;
	private String appId;
	private String eTag;
	private String versionId;
	private String url;
	private String diskLocation;
	private short valid;
	private String remark;
	private long userId;
	private Date createDate;
	private long createBy;
	private Date updateDate;
	private long updateBy;
	
	public long getId() {
		return id;
	}
	public Picture setId(long id) {
		this.id = id;
		return this;
	}
	public String getFileId() {
		return fileId;
	}
	public Picture setFileId(String fileId) {
		this.fileId = fileId;
		return this;
	}
	public String getAppId() {
		return appId;
	}
	public Picture setAppId(String appId) {
		this.appId = appId;
		return this;
	}
	public String geteTag() {
		return eTag;
	}
	public Picture seteTag(String eTag) {
		this.eTag = eTag;
		return this;
	}
	public String getVersionId() {
		return versionId;
	}
	public Picture setVersionId(String versionId) {
		this.versionId = versionId;
		return this;
	}
	public short getValid() {
		return valid;
	}
	public Picture setValid(short valid) {
		this.valid = valid;
		return this;
	}
	public String getRemark() {
		return remark;
	}
	public Picture setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	public long getUserId() {
		return userId;
	}
	public Picture setUserId(long userId) {
		this.userId = userId;
		return this;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Picture setCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}
	public long getCreateBy() {
		return createBy;
	}
	public Picture setCreateBy(long createBy) {
		this.createBy = createBy;
		return this;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public Picture setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		return this;
	}
	public long getUpdateBy() {
		return updateBy;
	}
	public Picture setUpdateBy(long updateBy) {
		this.updateBy = updateBy;
		return this;
	}
	public String getUrl() {
		return url;
	}
	public Picture setUrl(String url) {
		this.url = url;
		return this;
	}
	public String getDiskLocation() {
		return diskLocation;
	}
	public Picture setDiskLocation(String diskLocation) {
		this.diskLocation = diskLocation;
		return this;
	}
	public static Picture builder(){
		return new Picture();
	}
}
