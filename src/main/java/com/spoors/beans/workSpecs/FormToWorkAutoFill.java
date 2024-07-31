package com.spoors.beans.workSpecs;

import java.util.List;

public class FormToWorkAutoFill {

	
	private Long formToWorkAutoFillId;
	private Long workSpecId;
	private Long workActionSpecId;
	private String workActionFormSpecUniqueId;
	private boolean enable;
	private boolean sectionAutoFill;
	private List<FormToWorkAutoFillField> formToWorkAutoFillFields;
	private List<FormToWorkAutoFillSectionConfiguration> formtoWorkAutoFillSectionConfigurations;


	
	public Long getFormToWorkAutoFillId() {
		return formToWorkAutoFillId;
	}

	public void setFormToWorkAutoFillId(Long formToWorkAutoFillId) {
		this.formToWorkAutoFillId = formToWorkAutoFillId;
	}

	public Long getWorkActionSpecId() {
		return workActionSpecId;
	}

	public void setWorkActionSpecId(Long workActionSpecId) {
		this.workActionSpecId = workActionSpecId;
	}

	public String getWorkActionFormSpecUniqueId() {
		if(workActionFormSpecUniqueId == "")
			workActionFormSpecUniqueId =  null;
		return workActionFormSpecUniqueId;
	}

	public void setWorkActionFormSpecUniqueId(String workActionFormSpecUniqueId) {
		if(workActionFormSpecUniqueId == "")
			workActionFormSpecUniqueId =  null;
		this.workActionFormSpecUniqueId = workActionFormSpecUniqueId;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<FormToWorkAutoFillField> getFormToWorkAutoFillFields() {
		return formToWorkAutoFillFields;
	}

	public void setFormToWorkAutoFillFields(
			List<FormToWorkAutoFillField> formToWorkAutoFillFields) {
		this.formToWorkAutoFillFields = formToWorkAutoFillFields;
	}

	public Long getWorkSpecId() {
		return workSpecId;
	}

	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	
	public List<FormToWorkAutoFillSectionConfiguration> getFormtoWorkAutoFillSectionConfigurations() {
		return formtoWorkAutoFillSectionConfigurations;
	}

	public void setFormtoWorkAutoFillSectionConfigurations(
			List<FormToWorkAutoFillSectionConfiguration> formtoWorkAutoFillSectionConfigurations) {
		this.formtoWorkAutoFillSectionConfigurations = formtoWorkAutoFillSectionConfigurations;
	}

	public boolean isSectionAutoFill() {
		return sectionAutoFill;
	}

	public void setSectionAutoFill(boolean sectionAutoFill) {
		this.sectionAutoFill = sectionAutoFill;
	}

	@Override
	public String toString() {
		return "FormToWorkAutoFill [formToWorkAutoFillId="
				+ formToWorkAutoFillId + ", workSpecId=" + workSpecId
				+ ", workActionSpecId=" + workActionSpecId
				+ ", workActionFormSpecUniqueId=" + workActionFormSpecUniqueId
				+ ", enable=" + enable + ", formToWorkAutoFillFields="
				+ formToWorkAutoFillFields + ", formtoWorkAutoFillSectionConfigurations="
				+ formtoWorkAutoFillSectionConfigurations + "]";
	}
	
}
