package com.spoors.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfflineCustomEntityUpdateConfiguration {
	
	private Long offlineCustomEntityUpdateConfigurationId;
	private String formSpecUniqueId;
	private String customEntityFieldSpecUniqueId;
	private Long sourceCustomEntityFieldSpecId;
	private boolean customEntityFieldInSection;
	@JsonProperty("expression")
	private int condition;
	private String targetFieldUniqueId;
	private Integer companyId;
	private String createdTime;
	private String modifiedTime;
	
    private Integer offlineListUpdateFormEnabled = 0;
	
	private String customEntityFieldSpecUniqueIdError;
	private String sourceCustomEntityFieldSpecIdError;
	private String conditionError;
    private String targetFieldUniqueIdError;
	
	private boolean error;
    private int stockUpdateType = 1;
	
	public final static int STOCK_UPDATE_TYPE_MOBILE = 1;
	public final static int STOCK_UPDATE_TYPE_SERVER = 2;
	public final static int STOCK_UPDATE_TYPE_BOTH = 3;
	
	public Integer getOfflineListUpdateFormEnabled() {
		return offlineListUpdateFormEnabled;
	}
	public void setOfflineListUpdateFormEnabled(Integer offlineListUpdateFormEnabled) {
		this.offlineListUpdateFormEnabled = offlineListUpdateFormEnabled;
	}
	public String getCustomEntityFieldSpecUniqueIdError() {
		return customEntityFieldSpecUniqueIdError;
	}
	public void setCustomEntityFieldSpecUniqueIdError(String customEntityFieldSpecUniqueIdError) {
		this.customEntityFieldSpecUniqueIdError = customEntityFieldSpecUniqueIdError;
	}
	public String getSourceCustomEntityFieldSpecIdError() {
		return sourceCustomEntityFieldSpecIdError;
	}
	public void setSourceCustomEntityFieldSpecIdError(String sourceCustomEntityFieldSpecIdError) {
		this.sourceCustomEntityFieldSpecIdError = sourceCustomEntityFieldSpecIdError;
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
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	public Long getOfflineCustomEntityUpdateConfigurationId() {
		return offlineCustomEntityUpdateConfigurationId;
	}
	public void setOfflineCustomEntityUpdateConfigurationId(Long offlineCustomEntityUpdateConfigurationId) {
		this.offlineCustomEntityUpdateConfigurationId = offlineCustomEntityUpdateConfigurationId;
	}
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	public String getCustomEntityFieldSpecUniqueId() {
		return customEntityFieldSpecUniqueId;
	}
	public void setCustomEntityFieldSpecUniqueId(String customEntityFieldSpecUniqueId) {
		this.customEntityFieldSpecUniqueId = customEntityFieldSpecUniqueId;
	}
	public Long getSourceCustomEntityFieldSpecId() {
		return sourceCustomEntityFieldSpecId;
	}
	public void setSourceCustomEntityFieldSpecId(Long sourceCustomEntityFieldSpecId) {
		this.sourceCustomEntityFieldSpecId = sourceCustomEntityFieldSpecId;
	}
	public boolean isCustomEntityFieldInSection() {
		return customEntityFieldInSection;
	}
	public void setCustomEntityFieldInSection(boolean customEntityFieldInSection) {
		this.customEntityFieldInSection = customEntityFieldInSection;
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
	public int getStockUpdateType() {
		return stockUpdateType;
	}
	public void setStockUpdateType(int stockUpdateType) {
		this.stockUpdateType = stockUpdateType;
	}
	
}
