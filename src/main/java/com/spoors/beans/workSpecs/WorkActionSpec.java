package com.spoors.beans.workSpecs;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class WorkActionSpec {

	private Long workActionSpecId;
	private Long workSpecId;
	private String actionName;
	private String actionDesccription;
	private String formSpecUniqueId;
	private Long createdBy;
	private Long modifiedBy;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String createdTime;
	private String modifiedTime;
	private boolean deleted;
	private String actionNameEmpty;
	private String formSpecUniqueIdEmpty;
	private boolean active;
	private Boolean isStartAction=false;
	private Boolean isEndAction=false;
	private Long skeletonWorkActionSpecId;
	private String empGroupIds;
	private Long latestFormSpecId;
	private Long assignTo;
	private Boolean workFlowApplicable = false;
	private Integer formReUse;
	private Long actionTat;
	private boolean enableAutoCopyConfiguration = false;
	private boolean enableSectionToSectionAutoCopyConfiguration = false;
	private boolean enableAutoCopyConfigurationForWork = false;
	private List<ActionTat> actionSpecTat;
	private Integer sequenceOrder;
	private int count;
	private boolean startActionByHistroy;
	private boolean endActionByHistroy;
	private boolean lastActionPerformedByHistroy;
	
	public static final int FORM_RE_USE_OPEN_NEW_FORM = 101;
	public static final int FORM_RE_USE_PREVIOUS_FORM_DATA = 102;
	
	public final static int ACTION_TYPE_USER = 11;
    public final static int ACTION_TYPE_SYSTEM = 12;
    public final static int ACTION_TYPE_DECISION = 13;
    public final static int ACTION_TYPE_API_SERVICE = 14;
    public final static int ACTION_TYPE_EXTERNAL_ACTION = 15;
    public final static int ACTION_TYPE_JAVASCRIPT = 16;
	
	private String workActionSpecVisibilityConditionString;
	private List<WorkActionSpecVisibilityCondition> workActionSpecVisibilityConditionList;
	
	private String workFlowIdForActionForm;
	
	// 
	private boolean actionLevelAssignment;
	private boolean manual;
	private Integer allocationMethod;
	
	private boolean assignToImmediateManager;
	private Long immediateManagerActionSpecId;
	private boolean canOverrideAuto;
	private boolean canReassignAction;
		
	//private boolean otherActionVisibility;
	private boolean employeeGroupBase;
	private boolean onlyThisActionShouldReassign;
	
	private boolean assignToHigherLevelOfWorkAssigne;
	
	private boolean accessToEditCompletedAction;
	
	private String rejectedEmpName;
	
	private List<Employee> eligibleEmployees;
	
	private List<WorkActionEmployeeGroupSpecs> workActionEmployeeGroupSpecs;
	private List<WorkActionVisibilitySpecs> workActionVisibilitySpecs;
	
	private Map<String, List<Long>> workActionAndEmployeeIdsMap;
	private List<Long> allEmpIdsList;
	
	private boolean assignToPreviousActionsEmp;
	private Long previousActionEmployeeActionSpecId;
	
	private boolean assignToWorkFieldEmp;
	private String assignToWorkFieldEmpExpression;
	
	
	private String workReassignmentRulesString;
	private List<WorkReassignmentRules> workReassignmentRulesList;
	private boolean sendInvitationOnlyWhenActionable;
	
	private boolean showNextActionInvitationWorkFieldGroup;
	private String nextActionInvitationWorkFieldGroupExp;
	private boolean restrictRepetition = false;
	private boolean systemAction = false;
	private Long nextActionSpecPrimaryId;
	private int repetitionType;
	private Long workActionGroupId;
	private boolean draft;
	private boolean actionPerformed;
	private Long formId;
	
	private Integer workActionActionableType;
	private boolean enableOnlineApiForm;
	private boolean systemActionStatus;
	private boolean reTriggerFlag;
	private int botType;
	private boolean workSystemAction;
	private boolean skipSystemAction;
	private boolean caseManagementAction;
	private boolean docuSignRequired;
	private String actionPerformedTime;
	private String empGroupIdsForAllocation;
	private boolean groupBasedAssignment;
	private boolean enableSendInSync;
	private boolean enableActionAssignmentGroups;
	private boolean addPermission;
	private boolean modifyPermission;
	private boolean formSpecPermission;
	private boolean viewPermission;
	
	private boolean workCheckInRequiredForActivity;
	private boolean geoLocationCheckRequiredForActivity;
	private boolean actionPerformedAndInSequence;
	private String remarks;
	private Long workStatusHistoryId;
	
	
	public boolean isAddPermission() {
		return addPermission;
	}
	public void setAddPermission(boolean addPermission) {
		this.addPermission = addPermission;
	}
	public boolean isModifyPermission() {
		return modifyPermission;
	}
	public void setModifyPermission(boolean modifyPermission) {
		this.modifyPermission = modifyPermission;
	}
	public boolean isFormSpecPermission() {
		return formSpecPermission;
	}
	public void setFormSpecPermission(boolean formSpecPermission) {
		this.formSpecPermission = formSpecPermission;
	}
	public boolean isViewPermission() {
		return viewPermission;
	}
	public void setViewPermission(boolean viewPermission) {
		this.viewPermission = viewPermission;
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
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionDesccription() {
		return actionDesccription;
	}
	public void setActionDesccription(String actionDesccription) {
		this.actionDesccription = actionDesccription;
	}
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	public String getActionNameEmpty() {
		return actionNameEmpty;
	}
	public void setActionNameEmpty(String actionNameEmpty) {
		this.actionNameEmpty = actionNameEmpty;
	}
	public String getFormSpecUniqueIdEmpty() {
		return formSpecUniqueIdEmpty;
	}
	public void setFormSpecUniqueIdEmpty(String formSpecUniqueIdEmpty) {
		this.formSpecUniqueIdEmpty = formSpecUniqueIdEmpty;
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
//	
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WorkActionSpec){
		   WorkActionSpec workActionSpec = (WorkActionSpec) obj;
		   if(workActionSpecId!=null && workActionSpec.getWorkActionSpecId()!=null){
		        boolean result= workActionSpec.getWorkActionSpecId().longValue() == this.getWorkActionSpecId().longValue();
		        return result;
		   }
		   return false;
		}  else {
			return super.equals(obj);
		}
}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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
	public Long getSkeletonWorkActionSpecId() {
		return skeletonWorkActionSpecId;
	}
	public void setSkeletonWorkActionSpecId(Long skeletonWorkActionSpecId) {
		this.skeletonWorkActionSpecId = skeletonWorkActionSpecId;
	}
	public String getEmpGroupIds() {
		return empGroupIds;
	}
	public void setEmpGroupIds(String empGroupIds) {
		this.empGroupIds = empGroupIds;
	}
	public Integer getFormReUse() {
		return formReUse;
	}
	public void setFormReUse(Integer formReUse) {
		this.formReUse = formReUse;
	}
	public String getWorkActionSpecVisibilityConditionString() {
		return workActionSpecVisibilityConditionString;
	}
	public void setWorkActionSpecVisibilityConditionString(
			String workActionSpecVisibilityConditionString) {
		this.workActionSpecVisibilityConditionString = workActionSpecVisibilityConditionString;
	}
	public List<WorkActionSpecVisibilityCondition> getWorkActionSpecVisibilityConditionList() {
		return workActionSpecVisibilityConditionList;
	}
	public void setWorkActionSpecVisibilityConditionList(
			List<WorkActionSpecVisibilityCondition> workActionSpecVisibilityConditionList) {
		this.workActionSpecVisibilityConditionList = workActionSpecVisibilityConditionList;
	}
	public Long getLatestFormSpecId() {
		return latestFormSpecId;
	}
	public void setLatestFormSpecId(Long latestFormSpecId) {
		this.latestFormSpecId = latestFormSpecId;
	}
	public boolean isActionLevelAssignment() {
		return actionLevelAssignment;
	}
	public void setActionLevelAssignment(boolean actionLevelAssignment) {
		this.actionLevelAssignment = actionLevelAssignment;
	}
	public boolean isManual() {
		return manual;
	}
	public void setManual(boolean manual) {
		this.manual = manual;
	}
	public Integer getAllocationMethod() {
		return allocationMethod;
	}
	public void setAllocationMethod(Integer allocationMethod) {
		this.allocationMethod = allocationMethod;
	}
	public boolean isAssignToImmediateManager() {
		return assignToImmediateManager;
	}
	public void setAssignToImmediateManager(boolean assignToImmediateManager) {
		this.assignToImmediateManager = assignToImmediateManager;
	}
	public Long getImmediateManagerActionSpecId() {
		return immediateManagerActionSpecId;
	}
	public void setImmediateManagerActionSpecId(Long immediateManagerActionSpecId) {
		this.immediateManagerActionSpecId = immediateManagerActionSpecId;
	}
	public boolean isCanOverrideAuto() {
		return canOverrideAuto;
	}
	public void setCanOverrideAuto(boolean canOverrideAuto) {
		this.canOverrideAuto = canOverrideAuto;
	}
	/*public boolean isOtherActionVisibility() {
		return otherActionVisibility;
	}
	public void setOtherActionVisibility(boolean otherActionVisibility) {
		this.otherActionVisibility = otherActionVisibility;
	}*/
	public boolean isEmployeeGroupBase() {
		return employeeGroupBase;
	}
	public void setEmployeeGroupBase(boolean employeeGroupBase) {
		this.employeeGroupBase = employeeGroupBase;
	}
	public List<Employee> getEligibleEmployees() {
		return eligibleEmployees;
	}
	public void setEligibleEmployees(List<Employee> eligibleEmployees) {
		this.eligibleEmployees = eligibleEmployees;
	}
	public List<WorkActionEmployeeGroupSpecs> getWorkActionEmployeeGroupSpecs() {
		return workActionEmployeeGroupSpecs;
	}
	public void setWorkActionEmployeeGroupSpecs(List<WorkActionEmployeeGroupSpecs> workActionEmployeeGroupSpecs) {
		this.workActionEmployeeGroupSpecs = workActionEmployeeGroupSpecs;
	}
	public List<WorkActionVisibilitySpecs> getWorkActionVisibilitySpecs() {
		return workActionVisibilitySpecs;
	}
	public void setWorkActionVisibilitySpecs(List<WorkActionVisibilitySpecs> workActionVisibilitySpecs) {
		this.workActionVisibilitySpecs = workActionVisibilitySpecs;
	}
	
	public Map<String, List<Long>> getWorkActionAndEmployeeIdsMap() {
		return workActionAndEmployeeIdsMap;
	}
	public void setWorkActionAndEmployeeIdsMap(Map<String, List<Long>> workActionAndEmployeeIdsMap) {
		this.workActionAndEmployeeIdsMap = workActionAndEmployeeIdsMap;
	}
	public List<Long> getAllEmpIdsList() {
		return allEmpIdsList;
	}
	public void setAllEmpIdsList(List<Long> allEmpIdsList) {
		this.allEmpIdsList = allEmpIdsList;
	}
	public Long getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(Long assignTo) {
		this.assignTo = assignTo;
	}
	public boolean isAssignToPreviousActionsEmp() {
		return assignToPreviousActionsEmp;
	}
	public void setAssignToPreviousActionsEmp(boolean assignToPreviousActionsEmp) {
		this.assignToPreviousActionsEmp = assignToPreviousActionsEmp;
	}
	public Long getPreviousActionEmployeeActionSpecId() {
		return previousActionEmployeeActionSpecId;
	}
	public void setPreviousActionEmployeeActionSpecId(
			Long previousActionEmployeeActionSpecId) {
		this.previousActionEmployeeActionSpecId = previousActionEmployeeActionSpecId;
	}
	public boolean isCanReassignAction() {
		return canReassignAction;
	}
	public void setCanReassignAction(boolean canReassignAction) {
		this.canReassignAction = canReassignAction;
	}
	public boolean isOnlyThisActionShouldReassign() {
		return onlyThisActionShouldReassign;
	}
	public void setOnlyThisActionShouldReassign(boolean onlyThisActionShouldReassign) {
		this.onlyThisActionShouldReassign = onlyThisActionShouldReassign;
	}
	public String getRejectedEmpName() {
		return rejectedEmpName;
	}
	public void setRejectedEmpName(String rejectedEmpName) {
		this.rejectedEmpName = rejectedEmpName;
	}
	public boolean isAssignToHigherLevelOfWorkAssigne() {
		return assignToHigherLevelOfWorkAssigne;
	}
	public void setAssignToHigherLevelOfWorkAssigne(
			boolean assignToHigherLevelOfWorkAssigne) {
		this.assignToHigherLevelOfWorkAssigne = assignToHigherLevelOfWorkAssigne;
	}
	public boolean isAccessToEditCompletedAction() {
		return accessToEditCompletedAction;
	}
	public void setAccessToEditCompletedAction(boolean accessToEditCompletedAction) {
		this.accessToEditCompletedAction = accessToEditCompletedAction;
	}
	public Boolean getWorkFlowApplicable() {
		return workFlowApplicable;
	}
	public void setWorkFlowApplicable(Boolean workFlowApplicable) {
		this.workFlowApplicable = workFlowApplicable;
	}
	public String getWorkFlowIdForActionForm() {
		return workFlowIdForActionForm;
	}
	public void setWorkFlowIdForActionForm(String workFlowIdForActionForm) {
		this.workFlowIdForActionForm = workFlowIdForActionForm;
	}
	public boolean isAssignToWorkFieldEmp() {
		return assignToWorkFieldEmp;
	}
	public void setAssignToWorkFieldEmp(boolean assignToWorkFieldEmp) {
		this.assignToWorkFieldEmp = assignToWorkFieldEmp;
	}
	public String getAssignToWorkFieldEmpExpression() {
		return assignToWorkFieldEmpExpression;
	}
	public void setAssignToWorkFieldEmpExpression(String assignToWorkFieldEmpExpression) {
		this.assignToWorkFieldEmpExpression = assignToWorkFieldEmpExpression;
	}
	public String getWorkReassignmentRulesString() {
		return workReassignmentRulesString;
	}
	public void setWorkReassignmentRulesString(String workReassignmentRulesString) {
		this.workReassignmentRulesString = workReassignmentRulesString;
	}
	
	public List<WorkReassignmentRules> getWorkReassignmentRulesList() {
		return workReassignmentRulesList;
	}
	public void setWorkReassignmentRulesList(
			List<WorkReassignmentRules> workReassignmentRulesList) {
		this.workReassignmentRulesList = workReassignmentRulesList;
	}
	public boolean isSendInvitationOnlyWhenActionable() {
		return sendInvitationOnlyWhenActionable;
	}
	public void setSendInvitationOnlyWhenActionable(boolean sendInvitationOnlyWhenActionable) {
		this.sendInvitationOnlyWhenActionable = sendInvitationOnlyWhenActionable;
	}
	public boolean isShowNextActionInvitationWorkFieldGroup() {
		return showNextActionInvitationWorkFieldGroup;
	}
	public void setShowNextActionInvitationWorkFieldGroup(boolean showNextActionInvitationWorkFieldGroup) {
		this.showNextActionInvitationWorkFieldGroup = showNextActionInvitationWorkFieldGroup;
	}
	public String getNextActionInvitationWorkFieldGroupExp() {
		return nextActionInvitationWorkFieldGroupExp;
	}
	public void setNextActionInvitationWorkFieldGroupExp(String nextActionInvitationWorkFieldGroupExp) {
		this.nextActionInvitationWorkFieldGroupExp = nextActionInvitationWorkFieldGroupExp;
	}
	public boolean isRestrictRepetition() {
		return restrictRepetition;
	}
	public void setRestrictRepetition(boolean restrictRepetition) {
		this.restrictRepetition = restrictRepetition;
	}
	
	public boolean isSystemAction() {
		return systemAction;
	}
	
	public void setSystemAction(boolean systemAction) {
		this.systemAction = systemAction;
	}
	
	public Long getNextActionSpecPrimaryId() {
		return nextActionSpecPrimaryId;
	}
	public void setNextActionSpecPrimaryId(Long nextActionSpecPrimaryId) {
		this.nextActionSpecPrimaryId = nextActionSpecPrimaryId;
	}
	public int getRepetitionType() {
		return repetitionType;
	}
	public void setRepetitionType(int repetitionType) {
		this.repetitionType = repetitionType;
	}
	public Long getActionTat() {
		return actionTat;
	}
	public void setActionTat(Long actionTat) {
		this.actionTat = actionTat;
	}
	public List<ActionTat> getActionSpecTat() {
		return actionSpecTat;
	}
	public void setActionSpecTat(List<ActionTat> actionSpecTat) {
		this.actionSpecTat = actionSpecTat;
	}
	public Long getWorkActionGroupId() {
		return workActionGroupId;
	}
	public void setWorkActionGroupId(Long workActionGroupId) {
		this.workActionGroupId = workActionGroupId;
	}
	public boolean isDraft() {
		return draft;
	}
	public void setDraft(boolean draft) {
		this.draft = draft;
	}
	public boolean isActionPerformed() {
		return actionPerformed;
	}
	public void setActionPerformed(boolean actionPerformed) {
		this.actionPerformed = actionPerformed;
	}
	public Long getFormId() {
		return formId;
	}
	public void setFormId(Long formId) {
		this.formId = formId;
	}
	
	public Integer getWorkActionActionableType() {
		return workActionActionableType;
	}
	public void setWorkActionActionableType(Integer workActionActionableType) {
		this.workActionActionableType = workActionActionableType;
	}
	public boolean isEnableOnlineApiForm() {
		return enableOnlineApiForm;
	}
	public void setEnableOnlineApiForm(boolean enableOnlineApiForm) {
		this.enableOnlineApiForm = enableOnlineApiForm;
	}
	public boolean isSystemActionStatus() {
		return systemActionStatus;
	}
	public void setSystemActionStatus(boolean systemActionStatus) {
		this.systemActionStatus = systemActionStatus;
	}
	public boolean isReTriggerFlag() {
		return reTriggerFlag;
	}
	public void setReTriggerFlag(boolean reTriggerFlag) {
		this.reTriggerFlag = reTriggerFlag;
	}
	public int getBotType() {
		return botType;
	}
	public void setBotType(int botType) {
		this.botType = botType;
	}
	public boolean isWorkSystemAction() {
		return workSystemAction;
	}
	public void setWorkSystemAction(boolean workSystemAction) {
		this.workSystemAction = workSystemAction;
	}
	public boolean isSkipSystemAction() {
		return skipSystemAction;
	}
	public void setSkipSystemAction(boolean skipSystemAction) {
		this.skipSystemAction = skipSystemAction;
	}
	public boolean isEnableAutoCopyConfiguration() {
		return enableAutoCopyConfiguration;
	}
	public void setEnableAutoCopyConfiguration(boolean enableAutoCopyConfiguration) {
		this.enableAutoCopyConfiguration = enableAutoCopyConfiguration;
	}
	public boolean isEnableSectionToSectionAutoCopyConfiguration() {
		return enableSectionToSectionAutoCopyConfiguration;
	}
	public void setEnableSectionToSectionAutoCopyConfiguration(boolean enableSectionToSectionAutoCopyConfiguration) {
		this.enableSectionToSectionAutoCopyConfiguration = enableSectionToSectionAutoCopyConfiguration;
	}
	public boolean isEnableAutoCopyConfigurationForWork() {
		return enableAutoCopyConfigurationForWork;
	}
	public void setEnableAutoCopyConfigurationForWork(boolean enableAutoCopyConfigurationForWork) {
		this.enableAutoCopyConfigurationForWork = enableAutoCopyConfigurationForWork;
	}
	public boolean isCaseManagementAction() {
		return caseManagementAction;
	}
	public void setCaseManagementAction(boolean caseManagementAction) {
		this.caseManagementAction = caseManagementAction;
	}
	public boolean isDocuSignRequired() {
		return docuSignRequired;
	}
	public void setDocuSignRequired(boolean docuSignRequired) {
		this.docuSignRequired = docuSignRequired;
	}
	public String getActionPerformedTime() {
		return actionPerformedTime;
	}
	public void setActionPerformedTime(String actionPerformedTime) {
		this.actionPerformedTime = actionPerformedTime;
	}
	public String getEmpGroupIdsForAllocation() {
		return empGroupIdsForAllocation;
	}
	public void setEmpGroupIdsForAllocation(String empGroupIdsForAllocation) {
		this.empGroupIdsForAllocation = empGroupIdsForAllocation;
	}
	public boolean isGroupBasedAssignment() {
		return groupBasedAssignment;
	}
	public void setGroupBasedAssignment(boolean groupBasedAssignment) {
		this.groupBasedAssignment = groupBasedAssignment;
	}
	public boolean isEnableSendInSync() {
		return enableSendInSync;
	}
	public void setEnableSendInSync(boolean enableSendInSync) {
		this.enableSendInSync = enableSendInSync;
	}
	public boolean isEnableActionAssignmentGroups() {
		return enableActionAssignmentGroups;
	}
	public void setEnableActionAssignmentGroups(boolean enableActionAssignmentGroups) {
		this.enableActionAssignmentGroups = enableActionAssignmentGroups;
	}
	public boolean isWorkCheckInRequiredForActivity() {
		return workCheckInRequiredForActivity;
	}
	public void setWorkCheckInRequiredForActivity(boolean workCheckInRequiredForActivity) {
		this.workCheckInRequiredForActivity = workCheckInRequiredForActivity;
	}
	public boolean isGeoLocationCheckRequiredForActivity() {
		return geoLocationCheckRequiredForActivity;
	}
	public void setGeoLocationCheckRequiredForActivity(boolean geoLocationCheckRequiredForActivity) {
		this.geoLocationCheckRequiredForActivity = geoLocationCheckRequiredForActivity;
	}
	public boolean isActionPerformedAndInSequence() {
		return actionPerformedAndInSequence;
	}
	public void setActionPerformedAndInSequence(boolean actionPerformedAndInSequence) {
		this.actionPerformedAndInSequence = actionPerformedAndInSequence;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getSequenceOrder() {
		return sequenceOrder;
	}
	public void setSequenceOrder(Integer sequenceOrder) {
		this.sequenceOrder = sequenceOrder;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isStartActionByHistroy() {
		return startActionByHistroy;
	}
	public void setStartActionByHistroy(boolean startActionByHistroy) {
		this.startActionByHistroy = startActionByHistroy;
	}
	public boolean isEndActionByHistroy() {
		return endActionByHistroy;
	}
	public void setEndActionByHistroy(boolean endActionByHistroy) {
		this.endActionByHistroy = endActionByHistroy;
	}
	public boolean isLastActionPerformedByHistroy() {
		return lastActionPerformedByHistroy;
	}
	public void setLastActionPerformedByHistroy(boolean lastActionPerformedByHistroy) {
		this.lastActionPerformedByHistroy = lastActionPerformedByHistroy;
	}
	public Long getWorkStatusHistoryId() {
		return workStatusHistoryId;
	}
	public void setWorkStatusHistoryId(Long workStatusHistoryId) {
		this.workStatusHistoryId = workStatusHistoryId;
	}
}

