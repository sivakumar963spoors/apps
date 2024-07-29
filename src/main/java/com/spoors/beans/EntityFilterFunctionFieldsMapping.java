package com.spoors.beans;

import java.io.Serializable;

public class EntityFilterFunctionFieldsMapping implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String formSpecUniqueId;
	private long functionId;
	private String functionName;
	private String fieldExpression;
	private int fieldType;
	private int fieldDataType;
	private long paramId;
	private String paramName;
	private int paramDataType;
	private int sourceType;
	private String sourceFieldExpression;
	private int sourceDataType;
	private String createdTime;
	private long createdBy;
	
	public long getFunctionId() {
		return functionId;
	}
	public void setFunctionId(long functionId) {
		this.functionId = functionId;
	}
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
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	public int getFieldType() {
		return fieldType;
	}
	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}
	public int getFieldDataType() {
		return fieldDataType;
	}
	public void setFieldDataType(int fieldDataType) {
		this.fieldDataType = fieldDataType;
	}
	public long getParamId() {
		return paramId;
	}
	public void setParamId(long paramId) {
		this.paramId = paramId;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public int getParamDataType() {
		return paramDataType;
	}
	public void setParamDataType(int paramDataType) {
		this.paramDataType = paramDataType;
	}
	public int getSourceType() {
		return sourceType;
	}
	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}
	public String getSourceFieldExpression() {
		return sourceFieldExpression;
	}
	public void setSourceFieldExpression(String sourceFieldExpression) {
		this.sourceFieldExpression = sourceFieldExpression;
	}
	public int getSourceDataType() {
		return sourceDataType;
	}
	public void setSourceDataType(int sourceDataType) {
		this.sourceDataType = sourceDataType;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public String getFieldExpression() {
		return fieldExpression;
	}
	public void setFieldExpression(String fieldExpression) {
		this.fieldExpression = fieldExpression;
	}
	
	@Override
	public String toString() {
		return "EntityFilterFunctionFieldsMapping [id=" + id + ", formSpecUniqueId=" + formSpecUniqueId
				+ ", functionId=" + functionId + ", functionName=" + functionName + ", fieldExpression="
				+ fieldExpression + ", fieldType=" + fieldType + ", fieldDataType=" + fieldDataType + ", paramId="
				+ paramId + ", paramName=" + paramName + ", paramDataType=" + paramDataType + ", sourceType="
				+ sourceType + ", sourceFieldExpression=" + sourceFieldExpression + ", sourceDataType=" + sourceDataType
				+ ", createdTime=" + createdTime + ", createdBy=" + createdBy + "]";
	}
	
}
