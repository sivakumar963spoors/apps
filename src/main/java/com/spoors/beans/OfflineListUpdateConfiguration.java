package com.spoors.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfflineListUpdateConfiguration {

	private Long id;
	private String formSpecUniqueId;
	private String listFieldSpecUniqueId;
	private Long sourceListFieldSpecId;
	private int isListFieldInSection = 0;
	@JsonProperty("expression")
	private int condition;
	private String targetFieldUniqueId;
	private Integer companyId;
	private String createdTime;
	private String modifiedTime;
	private Integer offlineListUpdateFormEnabled = 0;
	
	private String listFieldSpecUniqueIdError;
	private String sourceListFieldSpecIdError;
	private String conditionError;
	private String targetFieldUniqueIdError;
	
	private boolean error;
	private int stockUpdateType = 1;
	
	public final static int STOCK_UPDATE_TYPE_MOBILE = 1;
	public final static int STOCK_UPDATE_TYPE_SERVER = 2;
	public final static int STOCK_UPDATE_TYPE_BOTH = 3;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public String getTargetFieldUniqueId() {
		return targetFieldUniqueId;
	}
	public void setTargetFieldUniqueId(String targetFieldUniqueId) {
		this.targetFieldUniqueId = targetFieldUniqueId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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
	
	public String getConditionError() {
		return conditionError;
	}
	public void setConditionError(String conditionError) {
		this.conditionError = conditionError;
	}
	public String getTargetFieldUniqueIdError() {
		return targetFieldUniqueIdError;
	}
	public void setTargetFieldUniqueIdError(String targetFieldUniqueIdError) {
		this.targetFieldUniqueIdError = targetFieldUniqueIdError;
	}
	public boolean hasError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public String getListFieldSpecUniqueId() {
		return listFieldSpecUniqueId;
	}
	public void setListFieldSpecUniqueId(String listFieldSpecUniqueId) {
		this.listFieldSpecUniqueId = listFieldSpecUniqueId;
	}
	public Long getSourceListFieldSpecId() {
		return sourceListFieldSpecId;
	}
	public void setSourceListFieldSpecId(Long sourceListFieldSpecId) {
		this.sourceListFieldSpecId = sourceListFieldSpecId;
	}
	public int getIsListFieldInSection() {
		return isListFieldInSection;
	}
	public void setIsListFieldInSection(int isListFieldInSection) {
		this.isListFieldInSection = isListFieldInSection;
	}
	public String getListFieldSpecUniqueIdError() {
		return listFieldSpecUniqueIdError;
	}
	public void setListFieldSpecUniqueIdError(String listFieldSpecUniqueIdError) {
		this.listFieldSpecUniqueIdError = listFieldSpecUniqueIdError;
	}
	public String getSourceListFieldSpecIdError() {
		return sourceListFieldSpecIdError;
	}
	public void setSourceListFieldSpecIdError(String sourceListFieldSpecIdError) {
		this.sourceListFieldSpecIdError = sourceListFieldSpecIdError;
	}
	public boolean isError() {
		return error;
	}
	public Integer getOfflineListUpdateFormEnabled() {
		return offlineListUpdateFormEnabled;
	}
	public void setOfflineListUpdateFormEnabled(Integer offlineListUpdateFormEnabled) {
		this.offlineListUpdateFormEnabled = offlineListUpdateFormEnabled;
	}
	public int getStockUpdateType() {
		return stockUpdateType;
	}
	public void setStockUpdateType(int stockUpdateType) {
		this.stockUpdateType = stockUpdateType;
	}
	
}
