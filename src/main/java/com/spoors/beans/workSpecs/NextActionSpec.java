package com.spoors.beans.workSpecs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class NextActionSpec {

	private Long id;
	private Long actionSpecId;//currentActionId;
	//private String nextActionIds;
	private Long nextActionSpecId;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Boolean isStartAction=false;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Boolean isEndAction=false;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String createdTime;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String modifiedTime;
	private Long workSpecId;
	private boolean deleted;
	private String workSpecConditionsJson;
	private String ruleName;
	public static final int RESTRICT_REPETITION_REPETITION_TYPE = 1;
	public static final int ALLOW_REPETITION_REPETITION_TYPE = 2;
	public static final int ALLOW_REPETITION_WITH_PREVIOUS_DATA_REPETITION_TYPE = 3;
	private int repetitionType = 1;
	//extra propeties
	private String actionName;
	/*private String nextActions; 
	private Long currentWorkSpecId;
	
	private String nextWorkSpecIds;
	private String nextWorkSpecName;
	private Long id;//pkey for Next work specs
	private int index;*/
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public Long getNextActionSpecId() {
		return nextActionSpecId;
	}
	public void setNextActionSpecId(Long nextActionSpecId) {
		this.nextActionSpecId = nextActionSpecId;
	}
	
	public Boolean getIsStartAction() {
		return isStartAction;
	}
	public void setIsStartAction(Boolean isStartAction) {
		this.isStartAction = isStartAction;
	}
	
	public Boolean getIsEndAction() {
		return isEndAction;
	}
	public void setIsEndAction(Boolean isEndAction) {
		this.isEndAction = isEndAction;
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
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getWorkSpecConditionsJson() {
		return workSpecConditionsJson;
	}
	public void setWorkSpecConditionsJson(String workSpecConditionsJson) {
		this.workSpecConditionsJson = workSpecConditionsJson;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	
	public int getRepetitionType() {
		return repetitionType;
	}
	public void setRepetitionType(int repetitionType) {
		this.repetitionType = repetitionType;
	}
	@Override
	public String toString() {
		return "NextActionSpec [id=" + id + ", actionSpecId=" + actionSpecId + ", nextActionSpecId=" + nextActionSpecId
				+ ", isStartAction=" + isStartAction + ", isEndAction=" + isEndAction + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + ", workSpecId=" + workSpecId + ", deleted=" + deleted
				+ ", workSpecConditionsJson=" + workSpecConditionsJson + ", ruleName=" + ruleName + ", repetitionType="
				+ repetitionType + "]";
	}
	
	
}
