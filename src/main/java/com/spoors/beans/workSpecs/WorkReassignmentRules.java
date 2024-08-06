package com.spoors.beans.workSpecs;

import java.util.List;

public class WorkReassignmentRules {
	
	public static final String CONDITION_RULE_ALWAYS = "1";
	public static final String CONDITION_RULE = "2";
	
	private long id;
	private Long workSpecId;
	private Long workActionSpecId;
	private boolean enableWorkReassignment;
	private String reassignFormFieldUniqueId;
	private String conditionRule;
	private String conjunction;
	private String targetFieldExpression;
	private String value;
	private Integer fieldDataType;
	private String condition;
	private Long createdBy;
	private Long modifiedBy;
	private String createdTime;
	private String modifiedTime;
	
	private String employeeFieldExpression;
	
	private List<WorkReassignmentRuleConditions> conditions ;
	private Long actionSpecId;
	private Long nextActionSpecId;
	
	public Long getNextActionSpecId() {
		return nextActionSpecId;
	}
	public void setNextActionSpecId(Long nextActionSpecId) {
		this.nextActionSpecId = nextActionSpecId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public Long getWorkActionSpecId() {
		return workActionSpecId;
	}
	public void setWorkActionSpecId(Long workActionSpecId) {
		this.workActionSpecId = workActionSpecId;
	}
	public boolean isEnableWorkReassignment() {
		return enableWorkReassignment;
	}
	public void setEnableWorkReassignment(boolean enableWorkReassignment) {
		this.enableWorkReassignment = enableWorkReassignment;
	}
	public String getReassignFormFieldUniqueId() {
		return reassignFormFieldUniqueId;
	}
	public void setReassignFormFieldUniqueId(String reassignFormFieldUniqueId) {
		this.reassignFormFieldUniqueId = reassignFormFieldUniqueId;
	}
	public String getConditionRule() {
		return conditionRule;
	}
	public void setConditionRule(String conditionRule) {
		this.conditionRule = conditionRule;
	}
	public String getConjunction() {
		return conjunction;
	}
	public void setConjunction(String conjunction) {
		this.conjunction = conjunction;
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
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
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
	public List<WorkReassignmentRuleConditions> getConditions() {
		return conditions;
	}
	public void setConditions(List<WorkReassignmentRuleConditions> conditions) {
		this.conditions = conditions;
	}
	public String getEmployeeFieldExpression() {
		return employeeFieldExpression;
	}
	public void setEmployeeFieldExpression(String employeeFieldExpression) {
		this.employeeFieldExpression = employeeFieldExpression;
	}
	public Long getActionSpecId() {
		return actionSpecId;
	}
	public void setActionSpecId(Long actionSpecId) {
		this.actionSpecId = actionSpecId;
	}

}
