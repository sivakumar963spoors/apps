package com.spoors.beans.workSpecs;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WorkSpecCustomerCallApi {

	private Long workSpecId;
	private String serviceApi;
	private Long remoteUrlId;
	private String phoneNoFieldSpecUniqueId;
	private Long createdBy;
	private Long modifiedBy;
	private String createdTime;
	private String modifiedTime;
	
	
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public String getServiceApi() {
		return serviceApi;
	}
	public void setServiceApi(String serviceApi) {
		this.serviceApi = serviceApi;
	}
	public Long getRemoteUrlId() {
		return remoteUrlId;
	}
	public void setRemoteUrlId(Long remoteUrlId) {
		this.remoteUrlId = remoteUrlId;
	}
	@JsonIgnore
	public Long getCreatedBy() {
		return createdBy;
	}
	@JsonIgnore
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	@JsonIgnore
	public Long getModifiedBy() {
		return modifiedBy;
	}
	@JsonIgnore
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@JsonIgnore
	public String getCreatedTime() {
		return createdTime;
	}
	@JsonIgnore
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	@JsonIgnore
	public String getModifiedTime() {
		return modifiedTime;
	}
	@JsonIgnore
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getPhoneNoFieldSpecUniqueId() {
		return phoneNoFieldSpecUniqueId;
	}
	public void setPhoneNoFieldSpecUniqueId(String phoneNoFieldSpecUniqueId) {
		this.phoneNoFieldSpecUniqueId = phoneNoFieldSpecUniqueId;
	}
	
	
}
