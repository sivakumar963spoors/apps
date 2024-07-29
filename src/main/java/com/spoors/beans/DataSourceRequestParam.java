package com.spoors.beans;

public class DataSourceRequestParam {
 
	private Long dataSourceRequestParamId;
	private Long formSpecDataSourceId;
	private String paramName;
	private String paramText;
	private Boolean isStatic = false;
	private Integer fieldType;
	private String fieldSpecUniqueId;
	private Boolean isListItem = false;
	private Long entitySpecId;
	private Long entityFieldSpecId;
	
	private Boolean jspDeleted = true;
	private String paramNameError;
	private String paramTextError;
	
	
	public Long getDataSourceRequestParamId() {
		return dataSourceRequestParamId;
	}
	public void setDataSourceRequestParamId(Long dataSourceRequestParamId) {
		this.dataSourceRequestParamId = dataSourceRequestParamId;
	}
	public Long getFormSpecDataSourceId() {
		return formSpecDataSourceId;
	}
	public void setFormSpecDataSourceId(Long formSpecDataSourceId) {
		this.formSpecDataSourceId = formSpecDataSourceId;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamText() {
		return paramText;
	}
	public void setParamText(String paramText) {
		this.paramText = paramText;
	}
	public Boolean getIsStatic() {
		return isStatic;
	}
	public void setIsStatic(Boolean isStatic) {
		this.isStatic = isStatic;
	}
	public Integer getFieldType() {
		return fieldType;
	}
	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldSpecUniqueId() {
		return fieldSpecUniqueId;
	}
	public void setFieldSpecUniqueId(String fieldSpecUniqueId) {
		this.fieldSpecUniqueId = fieldSpecUniqueId;
	}
	public Boolean getIsListItem() {
		return isListItem;
	}
	public void setIsListItem(Boolean isListItem) {
		this.isListItem = isListItem;
	}
	public Long getEntitySpecId() {
		return entitySpecId;
	}
	public void setEntitySpecId(Long entitySpecId) {
		this.entitySpecId = entitySpecId;
	}
	public Long getEntityFieldSpecId() {
		return entityFieldSpecId;
	}
	public void setEntityFieldSpecId(Long entityFieldSpecId) {
		this.entityFieldSpecId = entityFieldSpecId;
	}
	
	public Boolean getJspDeleted() {
		return jspDeleted;
	}
	public void setJspDeleted(Boolean jspDeleted) {
		this.jspDeleted = jspDeleted;
	}
	public String getParamNameError() {
		return paramNameError;
	}
	public void setParamNameError(String paramNameError) {
		this.paramNameError = paramNameError;
	}
	public String getParamTextError() {
		return paramTextError;
	}
	public void setParamTextError(String paramTextError) {
		this.paramTextError = paramTextError;
	}

}
