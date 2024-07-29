package com.spoors.beans;

public class DataSourceResponseMapping {
	private Long dataSourceResponseMappingId;
	private Long formSpecDataSourceId;
	private String fieldSpecUniqueId;
	private Integer fieldType;
	private String responseFieldKey;
	private String valuePath;
	
	private boolean jspDeleted = (boolean) true;
	private String valuePathError;
	private String fieldLableError;
	
	public Long getDataSourceResponseMappingId() {
		return dataSourceResponseMappingId;
	}
	public void setDataSourceResponseMappingId(Long dataSourceResponseMappingId) {
		this.dataSourceResponseMappingId = dataSourceResponseMappingId;
	}
	public Long getFormSpecDataSourceId() {
		return formSpecDataSourceId;
	}
	public void setFormSpecDataSourceId(Long formSpecDataSourceId) {
		this.formSpecDataSourceId = formSpecDataSourceId;
	}
	public String getFieldSpecUniqueId() {
		return fieldSpecUniqueId;
	}
	public void setFieldSpecUniqueId(String fieldSpecUniqueId) {
		this.fieldSpecUniqueId = fieldSpecUniqueId;
	}
	public Integer getFieldType() {
		return fieldType;
	}
	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}
	public String getResponseFieldKey() {
		return responseFieldKey;
	}
	public void setResponseFieldKey(String responseFieldKey) {
		this.responseFieldKey = responseFieldKey;
	}
	public String getValuePath() {
		return valuePath;
	}
	public void setValuePath(String valuePath) {
		this.valuePath = valuePath;
	}
	public Boolean getJspDeleted() {
		return jspDeleted;
	}
	public void setJspDeleted(Boolean jspDeleted) {
		this.jspDeleted = jspDeleted;
	}
	public String getValuePathError() {
		return valuePathError;
	}
	public void setValuePathError(String valuePathError) {
		this.valuePathError = valuePathError;
	}
	public String getFieldLableError() {
		return fieldLableError;
	}
	public void setFieldLableError(String fieldLableError) {
		this.fieldLableError = fieldLableError;
	}

}
