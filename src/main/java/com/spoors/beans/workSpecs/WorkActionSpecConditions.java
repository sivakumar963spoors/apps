package com.spoors.beans.workSpecs;

public class WorkActionSpecConditions {

	private Long id;
	private Long actionSpecId;
	private Long nextActionSpecId;
	private Long fieldType;
	private String targetFieldExpression;
	private String value;
	private int fieldDataType;
	private int condition;
	private int visibilityType;
	private int conjunction;
	private Long workSpecId;
	
	private Integer type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getActionSpecId() {
		return actionSpecId;
	}
	public void setActionSpecId(Long actionSpecId) {
		this.actionSpecId = actionSpecId;
	}
	public Long getNextActionSpecId() {
		return nextActionSpecId;
	}
	public void setNextActionSpecId(Long nextActionSpecId) {
		this.nextActionSpecId = nextActionSpecId;
	}
	public Long getFieldType() {
		return fieldType;
	}
	public void setFieldType(Long fieldType) {
		this.fieldType = fieldType;
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
	public int getFieldDataType() {
		return fieldDataType;
	}
	public void setFieldDataType(int fieldDataType) {
		this.fieldDataType = fieldDataType;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public int getVisibilityType() {
		return visibilityType;
	}
	public void setVisibilityType(int visibilityType) {
		this.visibilityType = visibilityType;
	}
	public int getConjunction() {
		return conjunction;
	}
	public void setConjunction(int conjunction) {
		this.conjunction = conjunction;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
