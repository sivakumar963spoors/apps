package com.spoors.beans;

public class WorkFormFieldMap {

	private Long workFormFieldMapId;
	private Long workSpecFormSpecFollowUpId;
	private String fieldSpecUniqueId;
	private String workFieldSpecUniqueId;
	
	//added on 5-may-2015 by ameer for pickAnother value feature
	private Integer pickAnother = 1;
	
	public Long getWorkFormFieldMapId() {
		return workFormFieldMapId;
	}
	public void setWorkFormFieldMapId(Long workFormFieldMapId) {
		this.workFormFieldMapId = workFormFieldMapId;
	}
	public Long getWorkSpecFormSpecFollowUpId() {
		return workSpecFormSpecFollowUpId;
	}
	public void setWorkSpecFormSpecFollowUpId(Long workSpecFormSpecFollowUpId) {
		this.workSpecFormSpecFollowUpId = workSpecFormSpecFollowUpId;
	}
	public String getFieldSpecUniqueId() {
		return fieldSpecUniqueId;
	}
	public void setFieldSpecUniqueId(String fieldSpecUniqueId) {
		this.fieldSpecUniqueId = fieldSpecUniqueId;
	}
	public String getWorkFieldSpecUniqueId() {
		return workFieldSpecUniqueId;
	}
	public void setWorkFieldSpecUniqueId(String workFieldSpecUniqueId) {
		this.workFieldSpecUniqueId = workFieldSpecUniqueId;
	}
	public Integer getPickAnother() {
		return pickAnother;
	}
	public void setPickAnother(Integer pickAnother) {
		this.pickAnother = pickAnother;
	}
}
