package com.spoors.beans.workSpecs;

public class WorkReassignmentRuleConditions {
	
	private String targetFieldExpression;
	private String value;
	private Integer fieldDataType;
	private String condition;
	private Integer fieldType;
	
	public String getTargetFieldExpression() {
		return targetFieldExpression;
	}
	public void setTargetFieldExpression(String targetFieldExpression) {
		this.targetFieldExpression = targetFieldExpression;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getFieldDataType() {
		return fieldDataType;
	}
	public void setFieldDataType(Integer fieldDataType) {
		this.fieldDataType = fieldDataType;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Integer getFieldType() {
		return fieldType;
	}
	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}

}
