package com.spoors.beans.workSpecs;

public class WorkAssignmentCriteriaConditions {
	
	public static final int CRITERIA_TYPE_ACTION_FORM_FIELDS = 1;
	public static final int CRITERIA_TYPE_ACTION_RECETITION = 2;
	
	private Long conditionId;
	private Long workUnassignmentCriteriaId;
	private String fieldSpecUniqueId;
	private String fieldExpression;
	private boolean section;
	private Long fieldDataType;
	private int condition;
	private String conditionValue;
	private int criteriaType;
	private Long actionCount;
	  
	
	public Long getConditionId() {
		return conditionId;
	}
	public void setConditionId(Long conditionId) {
		this.conditionId = conditionId;
	}
	public Long getWorkUnassignmentCriteriaId() {
		return workUnassignmentCriteriaId;
	}
	public void setWorkUnassignmentCriteriaId(Long workUnassignmentCriteriaId) {
		this.workUnassignmentCriteriaId = workUnassignmentCriteriaId;
	}
	public String getFieldSpecUniqueId() {
		return fieldSpecUniqueId;
	}
	public void setFieldSpecUniqueId(String fieldSpecUniqueId) {
		this.fieldSpecUniqueId = fieldSpecUniqueId;
	}
	public String getFieldExpression() {
		return fieldExpression;
	}
	public void setFieldExpression(String fieldExpression) {
		this.fieldExpression = fieldExpression;
	}
	public boolean isSection() {
		return section;
	}
	public void setSection(boolean section) {
		this.section = section;
	}
	public Long getFieldDataType() {
		return fieldDataType;
	}
	public void setFieldDataType(Long fieldDataType) {
		this.fieldDataType = fieldDataType;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public String getConditionValue() {
		return conditionValue;
	}
	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	public int getCriteriaType() {
		return criteriaType;
	}
	public void setCriteriaType(int criteriaType) {
		this.criteriaType = criteriaType;
	}
	public Long getActionCount() {
		return actionCount;
	}
	public void setActionCount(Long actionCount) {
		this.actionCount = actionCount;
	}
	

}
