package com.spoors.beans;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentMapping {

	
	private long id;
	private String formSpecUniqueId; 
	private String formFieldSpecUniqueId; 
	private Long paymentChannelId;
	private String createdBy;
	private String modifiedBy;
	private String createdTime;
	private String modifiedTime;
	private boolean deleted;
	private int type;
	private Integer companyId;
	
	private String formTitle;
	private String fieldLabel;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	public String getFormFieldSpecUniqueId() {
		return formFieldSpecUniqueId;
	}
	public void setFormFieldSpecUniqueId(String formFieldSpecUniqueId) {
		this.formFieldSpecUniqueId = formFieldSpecUniqueId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getFormTitle() {
		return formTitle;
	}
	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}
	public String getFieldLabel() {
		return fieldLabel;
	}
	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	public Long getPaymentChannelId() {
		return paymentChannelId;
	}
	public void setPaymentChannelId(long paymentChannelId) {
		this.paymentChannelId = paymentChannelId;
	}
	
	
}
