package com.spoors.beans.workSpecs;

public class WorkInvitationCriteriaCondition {

	private Long workInvitationCriteriaConditionId;
	private Long workSpecSchedulingConfigId;
	private Long workSpecId;
	private Integer conjunction;
	private String fieldSpecUniqueId;
	private String fieldExpression;
	private boolean section;
	private Long fieldDataType;
	private int condition;
	private String conditionValue;
	private boolean deleted;
	
	public Long getWorkInvitationCriteriaConditionId() {
		return workInvitationCriteriaConditionId;
	}
	public void setWorkInvitationCriteriaConditionId(Long workInvitationCriteriaConditionId) {
		this.workInvitationCriteriaConditionId = workInvitationCriteriaConditionId;
	}
	public Long getWorkSpecSchedulingConfigId() {
		return workSpecSchedulingConfigId;
	}
	public void setWorkSpecSchedulingConfigId(Long workSpecSchedulingConfigId) {
		this.workSpecSchedulingConfigId = workSpecSchedulingConfigId;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public Integer getConjunction() {
		return conjunction;
	}
	public void setConjunction(Integer conjunction) {
		this.conjunction = conjunction;
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
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
}
