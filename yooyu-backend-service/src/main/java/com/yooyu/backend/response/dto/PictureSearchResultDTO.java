package com.yooyu.backend.response.dto;

import java.util.Date;

public class PictureSearchResultDTO {
	private Long id;
	private String fileId;
	private String appId;
	private String eTag;
	private String versionId;
	private Short valid;
	private String remark;
	private Long userId;
	private String url;
	private String diskLocation;
	private Date createDate;
	private Long createBy;
	private Date updateDate;
	private Long updateBy;
	
	public Long getId() {
		return id;
	}
	public PictureSearchResultDTO setId(Long id) {
		this.id = id;
		return this;
	}
	public String getFileId() {
		return fileId;
	}
	public PictureSearchResultDTO setFileId(String fileId) {
		this.fileId = fileId;
		return this;
	}
	public String getAppId() {
		return appId;
	}
	public PictureSearchResultDTO setAppId(String appId) {
		this.appId = appId;
		return this;
	}
	public String geteTag() {
		return eTag;
	}
	public PictureSearchResultDTO seteTag(String eTag) {
		this.eTag = eTag;
		return this;
	}
	public String getVersionId() {
		return versionId;
	}
	public PictureSearchResultDTO setVersionId(String versionId) {
		this.versionId = versionId;
		return this;
	}
	public Short getValid() {
		return valid;
	}
	public PictureSearchResultDTO setValid(Short valid) {
		this.valid = valid;
		return this;
	}
	public String getRemark() {
		return remark;
	}
	public PictureSearchResultDTO setRemark(String remark) {
		this.remark = remark;
		return this;
	}
	public Long getUserId() {
		return userId;
	}
	public PictureSearchResultDTO setUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public PictureSearchResultDTO setCreateDate(Date createDate) {
		this.createDate = createDate;
		return this;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public PictureSearchResultDTO setCreateBy(Long createBy) {
		this.createBy = createBy;
		return this;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public PictureSearchResultDTO setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		return this;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public PictureSearchResultDTO setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
		return this;
	}
	public String getUrl() {
		return url;
	}
	public PictureSearchResultDTO setUrl(String url) {
		this.url = url;
		return this;
	}
	public String getDiskLocation() {
		return diskLocation;
	}
	public PictureSearchResultDTO setDiskLocation(String diskLocation) {
		this.diskLocation = diskLocation;
		return this;
	}
	public static PictureSearchResultDTO builder(){
		return new PictureSearchResultDTO();
	}
	
}
