package com.spoors.beans;

import java.util.List;

public class WorkSpecFormSpecFollowUp {

	private Long workSpecFormSpecFollowUpId;
	private boolean workFormFollowUp;
	private String formSpecUniqueId;
	private String dateFieldSpecUniqueId;
	private String workFormSpecUniqueId;
	private List<WorkFormFieldMap> workFormFieldMaps;

	public Long getWorkSpecFormSpecFollowUpId() {
		return workSpecFormSpecFollowUpId;
	}
	public void setWorkSpecFormSpecFollowUpId(Long workSpecFormSpecFollowUpId) {
		this.workSpecFormSpecFollowUpId = workSpecFormSpecFollowUpId;
	}
	public boolean isWorkFormFollowUp() {
		return workFormFollowUp;
	}
	public void setWorkFormFollowUp(boolean workFormFollowUp) {
		this.workFormFollowUp = workFormFollowUp;
	}
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	public String getDateFieldSpecUniqueId() {
		return dateFieldSpecUniqueId;
	}
	public void setDateFieldSpecUniqueId(String dateFieldSpecUniqueId) {
		this.dateFieldSpecUniqueId = dateFieldSpecUniqueId;
	}
	public String getWorkFormSpecUniqueId() {
		return workFormSpecUniqueId;
	}
	public void setWorkFormSpecUniqueId(String workFormSpecUniqueId) {
		this.workFormSpecUniqueId = workFormSpecUniqueId;
	}
	public List<WorkFormFieldMap> getWorkFormFieldMaps() {
		return workFormFieldMaps;
	}
	public void setWorkFormFieldMaps(List<WorkFormFieldMap> workFormFieldMaps) {
		this.workFormFieldMaps = workFormFieldMaps;
	}
}
