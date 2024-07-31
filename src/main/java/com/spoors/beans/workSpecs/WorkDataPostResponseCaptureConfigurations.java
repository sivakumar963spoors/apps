package com.spoors.beans.workSpecs;

public class WorkDataPostResponseCaptureConfigurations 
{
	private Long responseCaptureConfigId;
	private Long workDataPostingConfigurationId;
	private int workFormFieldType;
	private String workFormFieldUniqueId;
	private String responsePath;
	public Long getResponseCaptureConfigId() {
		return responseCaptureConfigId;
	}
	public void setResponseCaptureConfigId(Long responseCaptureConfigId) {
		this.responseCaptureConfigId = responseCaptureConfigId;
	}
	public Long getWorkDataPostingConfigurationId() {
		return workDataPostingConfigurationId;
	}
	public void setWorkDataPostingConfigurationId(Long workDataPostingConfigurationId) {
		this.workDataPostingConfigurationId = workDataPostingConfigurationId;
	}
	public int getWorkFormFieldType() {
		return workFormFieldType;
	}
	public void setWorkFormFieldType(int workFormFieldType) {
		this.workFormFieldType = workFormFieldType;
	}
	public String getWorkFormFieldUniqueId() {
		return workFormFieldUniqueId;
	}
	public void setWorkFormFieldUniqueId(String workFormFieldUniqueId) {
		this.workFormFieldUniqueId = workFormFieldUniqueId;
	}
	public String getResponsePath() {
		return responsePath;
	}
	public void setResponsePath(String responsePath) {
		this.responsePath = responsePath;
	}
		

}
