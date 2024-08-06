package com.spoors.beans.workSpecs;

public class WorkActionSpecVisibilityCondition {

	public static final int FIELD_IS_FORM_FIELD=1;
	public static final int FIELD_IS_SECTION_FIELD=2;
	public static final int FIELD_IS_WORK_ACTION=3;
	public static final int FIELD_IS_SUB_TASK=4;
	
	
	private Long id;
	private Long workActionSpecId;
	private Long workSpecId;
	private Long fieldType;//1-Form Field,2-Section Field,3-Work Action,4-Sub Task
	private String targetFieldExpression;
	private String valueIds;
	private String value;
	private String fieldDataType;
	private String condition;
	private String visibilityType;
	private String conjunction;
	private String createdTime;
	private String modifiedTime;
	
	private Integer operator2;
	private String value2;
	private String errorMessage;
	private Integer advancedDateCriteria;
	private Long conditionalWorkActionSpecId;
	private Long workProcessSubTaskSpecId;
	private Long fieldCondtionSourceActionSpecId;
	
	private boolean considerDraftForm;
	
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
	public String getValueIds() {
		return valueIds;
	}
	public void setValueIds(String valueIds) {
		this.valueIds = valueIds;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getFieldDataType() {
		return fieldDataType;
	}
	public void setFieldDataType(String fieldDataType) {
		this.fieldDataType = fieldDataType;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getVisibilityType() {
		return visibilityType;
	}
	public void setVisibilityType(String visibilityType) {
		this.visibilityType = visibilityType;
	}
	public String getConjunction() {
		return conjunction;
	}
	public void setConjunction(String conjunction) {
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
	public Integer getOperator2() {
		return operator2;
	}
	public void setOperator2(Integer operator2) {
		this.operator2 = operator2;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getAdvancedDateCriteria() {
		return advancedDateCriteria;
	}
	public void setAdvancedDateCriteria(Integer advancedDateCriteria) {
		this.advancedDateCriteria = advancedDateCriteria;
	}
	public Long getConditionalWorkActionSpecId() {
		return conditionalWorkActionSpecId;
	}
	public void setConditionalWorkActionSpecId(Long conditionalWorkActionSpecId) {
		this.conditionalWorkActionSpecId = conditionalWorkActionSpecId;
	}
	public Long getWorkProcessSubTaskSpecId() {
		return workProcessSubTaskSpecId;
	}
	public void setWorkProcessSubTaskSpecId(Long workProcessSubTaskSpecId) {
		this.workProcessSubTaskSpecId = workProcessSubTaskSpecId;
	}
	public Long getFieldCondtionSourceActionSpecId() {
		return fieldCondtionSourceActionSpecId;
	}
	public void setFieldCondtionSourceActionSpecId(Long fieldCondtionSourceActionSpecId) {
		this.fieldCondtionSourceActionSpecId = fieldCondtionSourceActionSpecId;
	}
	public boolean isConsiderDraftForm() {
		return considerDraftForm;
	}
	public void setConsiderDraftForm(boolean considerDraftForm) {
		this.considerDraftForm = considerDraftForm;
	}
	
	
	
}
