package com.spoors.beans;

public class DataSourceRequestHeader {

	private Long dataSourceRequestHeaderId;
	private Long formSpecDataSourceId;
	private String headerName;
	private String headerText;
	private Boolean isStatic = false;
	private Integer  fieldType;
	private String fieldSpecUniqueId;
	private Boolean isListItem = false;
	private Long entitySpecId;
	private Long entityFieldSpecId;
	
	private Boolean jspDeleted = true;
	private String headerNameError;
	private String headerTextError;
	
	public Long getDataSourceRequestHeaderId() {
		return dataSourceRequestHeaderId;
	}
	public void setDataSourceRequestHeaderId(Long dataSourceRequestHeaderId) {
		this.dataSourceRequestHeaderId = dataSourceRequestHeaderId;
	}
	public Long getFormSpecDataSourceId() {
		return formSpecDataSourceId;
	}
	public void setFormSpecDataSourceId(Long formSpecDataSourceId) {
		this.formSpecDataSourceId = formSpecDataSourceId;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public String getHeaderText() {
		return headerText;
	}
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
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
	public String getHeaderNameError() {
		return headerNameError;
	}
	public void setHeaderNameError(String headerNameError) {
		this.headerNameError = headerNameError;
	}
	public String getHeaderTextError() {
		return headerTextError;
	}
	public void setHeaderTextError(String headerTextError) {
		this.headerTextError = headerTextError;
	}

}
