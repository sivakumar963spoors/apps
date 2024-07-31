package com.spoors.beans.workSpecs;

public class WorkActionSpecEndCondition {

	private Long id;
	private Long workActionSpecId;
	private Long workSpecId;
	private Integer isSectionField = 0;
	private String targetFieldExpression;
	private String value;
	private Integer fieldType;
	private Integer condition;
	private Integer conditionType;
	private Integer conjunction;
	private String createdTime;
	private String modifiedTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWorkActionSpecId() {
		return workActionSpecId;
	}
	public void setWorkActionSpecId(Long workActionSpecId) {
		this.workActionSpecId = workActionSpecId;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public Integer getIsSectionField() {
		return isSectionField;
	}
	public void setIsSectionField(Integer isSectionField) {
		this.isSectionField = isSectionField;
	}
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
	public Integer getFieldType() {
		return fieldType;
	}
	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}
	public Integer getCondition() {
		return condition;
	}
	public void setCondition(Integer condition) {
		this.condition = condition;
	}
	public Integer getConditionType() {
		return conditionType;
	}
	public void setConditionType(Integer conditionType) {
		this.conditionType = conditionType;
	}
	public Integer getConjunction() {
		return conjunction;
	}
	public void setConjunction(Integer conjunction) {
		this.conjunction = conjunction;
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
	
}
