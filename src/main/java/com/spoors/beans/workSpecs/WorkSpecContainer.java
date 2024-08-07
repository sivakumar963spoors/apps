package com.spoors.beans.workSpecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkSpecContainer {
	private Map<Long, Boolean> workSpecVisibilityMap = new HashMap<Long, Boolean>();
	List<WorkSpec> WorkSpecs;
	List<WorkActionSpec> workActionSpecs;
	List<NextActionSpec> nextActionSpecs;
	List<NextWorkSpec> nextWorkSpecs;
	List<WorkSpecFormSpecMap> workSpecFormSpecMaps;
	private List<WorkActionSpecConditions> workActionSpecConditions;
	private List<WorkFormAutoFill> workFormAutoFillStageConfig;
	private List<WorkFormAutoFillField> workFormAutoFillFieldMapping;
	
	private List<WorkAttachmentAutoFill> workAttachmentFormAutoFillStageConfig;
	private List<WorkAttachmentFormAutoFillField> workAttachmentFormAutoFillFieldMapping;
	
	private List<AttachmnetFormAutoFillSectionConfiguration> attachmentFormAutoFillSectionConfiguration;
	private List<WorkAttachmentAutoFillSectionFieldsConfiguration> attachmentFormAutoFillSectionFieldsConfiguration;
	
	private List<FormToWorkAutoFill> formToWorkAutoFillStageConfig;
	private List<FormToWorkAutoFillField> formToWorkAutoFillFieldMapping;
	
	private List<FormAutoFillSectionConfiguration> formAutoFillSectionConfiguration;
	private List<FormAutoFillSectionFieldsConfiguration> formAutoFillSectionFieldsConfiguration;
	
	
	private List<WorkActionSpecEndCondition> workActionSpecEndConditions;
	
	private List<WorkActionVisibilityConfiguration> workActionVisibilityConfigurations;
	
	private List<WorkProcessSubTaskSpec> workProcessSubTaskSpecConfigurations;
	
	private List<AddingSubTaskEmployeeConfiguration> workProcessSubTaskEmployeesConfigurations;
	
	private List<WorkToSubTaskAutoFillConfiguration> workToSubTaskAutoFillConfigurations;
	
	private List<WorkFieldsUniqueConfigurations> workFieldsUniqueConfigurations;
	
	private List<WorkReassignmentRules> workReassignmentRules;
	
	private List<WorkSpecAppLabel> workSpecAppLabels;
	
	private List<HideAddSubTaskConfiguration> hideAddSubTaskConfigurations;
	
	private List<WorkSpecPermission> workSpecPermissions;
	
	private List<WorkUnassignmentCriterias> workUnassignmentCriterias;
	private List<WorkAssignmentCriteriaConditions> workAssignmentCriteriaConditions;
	
	private List<WorkSpecCustomerCallApi> workSpecCustomerCallApis;
	private List<WorkActionGroup> workActionGroups;
	
	private List<ExternalActionConfiguration> externalActionConfigurations;
	private List<FormToWorkAutoFillSectionConfiguration> formToWorkAutoFillSectionConfiguration;
	private List<FormToWorkAutoFillSectionFieldsConfiguration> formToWorkAutoFillSectionFieldsConfiguration;
	
	private List<WorkFieldsUniqueConfigurations> openWorkFieldsUniqueConfigurations;
	
	private List<WorkSpecFeedBackFormSpecMap> workSpecFeedBackFormSpecMap; // have to send in sqlite 
	
	
	private List<WorkSpecListLevelVisibilityConfiguration> workSpecListLevelVisibilityConfigurations;
	private List<WorkActionFormVisibility> workActionFormVisibility;
	private List<WorkSpecCustomDashboardConfiguration> workSpecCustomDashboardConfigurations;
	private List<WorkSpecCustomDashboardMetric> workSpecCustomDashboardMetrics;
	private List<ActionableEmployeeGroupSpecs> actionableEmployeeGroupSpecs;
	private List<WorkActionNotificationEscalationMatrix> workActionNotificationEscalationMatrix;
	private List<WorkActionExcalatedEmpIds> workActionExcalatedEmpIds;

	public WorkSpecContainer() {
		WorkSpecs=new ArrayList<WorkSpec>();
		workActionSpecs=new ArrayList<WorkActionSpec>();
		nextActionSpecs=new ArrayList<NextActionSpec>();
		nextWorkSpecs=new ArrayList<NextWorkSpec>();
		workSpecFormSpecMaps=new ArrayList<WorkSpecFormSpecMap>();
		workActionSpecConditions = new ArrayList<WorkActionSpecConditions>();
		workFormAutoFillStageConfig = new ArrayList<WorkFormAutoFill>();
		workFormAutoFillFieldMapping = new ArrayList<WorkFormAutoFillField>();
		workAttachmentFormAutoFillStageConfig = new ArrayList<WorkAttachmentAutoFill>();
		workAttachmentFormAutoFillFieldMapping = new ArrayList<WorkAttachmentFormAutoFillField>();
		setWorkActionSpecEndConditions(new ArrayList<WorkActionSpecEndCondition>());
		workActionVisibilityConfigurations = new ArrayList<WorkActionVisibilityConfiguration>();
		workProcessSubTaskSpecConfigurations = new ArrayList<WorkProcessSubTaskSpec>();
		workProcessSubTaskEmployeesConfigurations = new ArrayList<AddingSubTaskEmployeeConfiguration>();
		workToSubTaskAutoFillConfigurations = new ArrayList<WorkToSubTaskAutoFillConfiguration>();
		workFieldsUniqueConfigurations = new ArrayList<WorkFieldsUniqueConfigurations>();
		workReassignmentRules = new ArrayList<WorkReassignmentRules>();
		workSpecAppLabels = new ArrayList<WorkSpecAppLabel>();
		hideAddSubTaskConfigurations = new ArrayList<HideAddSubTaskConfiguration>();
		setWorkSpecPermissions(new ArrayList<WorkSpecPermission>());
		setWorkUnassignmentCriterias(new ArrayList<WorkUnassignmentCriterias>());
		setWorkAssignmentCriteriaConditions(new ArrayList<WorkAssignmentCriteriaConditions>());
		workSpecCustomerCallApis = new ArrayList<WorkSpecCustomerCallApi>();
		setWorkActionGroups(new ArrayList<WorkActionGroup>());
		openWorkFieldsUniqueConfigurations = new ArrayList<WorkFieldsUniqueConfigurations>();
		workSpecFeedBackFormSpecMap = new ArrayList<WorkSpecFeedBackFormSpecMap>();
		workSpecCustomDashboardConfigurations = new ArrayList<WorkSpecCustomDashboardConfiguration>();
		workSpecCustomDashboardMetrics = new ArrayList<WorkSpecCustomDashboardMetric>();
		actionableEmployeeGroupSpecs = new ArrayList<ActionableEmployeeGroupSpecs>();
		workActionNotificationEscalationMatrix = new ArrayList<WorkActionNotificationEscalationMatrix>();
		workActionExcalatedEmpIds = new ArrayList<WorkActionExcalatedEmpIds>();
	}
	
	public List<WorkSpec> getWorkSpecs() {
		return WorkSpecs;
	}
	public void setWorkSpecs(List<WorkSpec> workSpecs) {
		WorkSpecs = workSpecs;
	}
	public List<WorkActionSpec> getWorkActionSpecs() {
		return workActionSpecs;
	}
	public void setWorkActionSpecs(List<WorkActionSpec> workActionSpecs) {
		this.workActionSpecs = workActionSpecs;
	}
	public List<NextActionSpec> getNextActionSpecs() {
		return nextActionSpecs;
	}
	public void setNextActionSpecs(List<NextActionSpec> nextActionSpecs) {
		this.nextActionSpecs = nextActionSpecs;
	}
	public List<NextWorkSpec> getNextWorkSpecs() {
		return nextWorkSpecs;
	}
	public void setNextWorkSpecs(List<NextWorkSpec> nextWorkSpecs) {
		this.nextWorkSpecs = nextWorkSpecs;
	}
	
	public Map<Long, Boolean> getWorkSpecVisibilityMap() {
		return workSpecVisibilityMap;
	}

	public void setWorkSpecVisibilityMap(Map<Long, Boolean> workSpecVisibilityMap) {
		this.workSpecVisibilityMap = workSpecVisibilityMap;
	}

	public List<WorkSpecFormSpecMap> getWorkSpecFormSpecMaps() {
		return workSpecFormSpecMaps;
	}

	public void setWorkSpecFormSpecMaps(
			List<WorkSpecFormSpecMap> workSpecFormSpecMaps) {
		this.workSpecFormSpecMaps = workSpecFormSpecMaps;
	}

	public List<WorkActionSpecConditions> getWorkActionSpecConditions() {
		return workActionSpecConditions;
	}

	public void setWorkActionSpecConditions(
			List<WorkActionSpecConditions> workActionSpecConditions) {
		this.workActionSpecConditions = workActionSpecConditions;
	}

	public List<WorkFormAutoFill> getWorkFormAutoFillStageConfig() {
		return workFormAutoFillStageConfig;
	}

	public void setWorkFormAutoFillStageConfig(
			List<WorkFormAutoFill> workFormAutoFillStageConfig) {
		this.workFormAutoFillStageConfig = workFormAutoFillStageConfig;
	}

	public List<WorkFormAutoFillField> getWorkFormAutoFillFieldMapping() {
		return workFormAutoFillFieldMapping;
	}

	public void setWorkFormAutoFillFieldMapping(
			List<WorkFormAutoFillField> workFormAutoFillFieldMapping) {
		this.workFormAutoFillFieldMapping = workFormAutoFillFieldMapping;
	}


	public List<FormToWorkAutoFill> getFormToWorkAutoFillStageConfig() {
		return formToWorkAutoFillStageConfig;
	}

	public void setFormToWorkAutoFillStageConfig(
			List<FormToWorkAutoFill> formToWorkAutoFillStageConfig) {
		this.formToWorkAutoFillStageConfig = formToWorkAutoFillStageConfig;
	}

	public List<FormToWorkAutoFillField> getFormToWorkAutoFillFieldMapping() {
		return formToWorkAutoFillFieldMapping;
	}

	public void setFormToWorkAutoFillFieldMapping(
			List<FormToWorkAutoFillField> formToWorkAutoFillFieldMapping) {
		this.formToWorkAutoFillFieldMapping = formToWorkAutoFillFieldMapping;
	}

	public List<WorkActionSpecEndCondition> getWorkActionSpecEndConditions() {
		return workActionSpecEndConditions;
	}

	public void setWorkActionSpecEndConditions(
			List<WorkActionSpecEndCondition> workActionSpecEndConditions) {
		this.workActionSpecEndConditions = workActionSpecEndConditions;
	}

	public List<WorkActionVisibilityConfiguration> getWorkActionVisibilityConfigurations() {
		return workActionVisibilityConfigurations;
	}

	public void setWorkActionVisibilityConfigurations(
			List<WorkActionVisibilityConfiguration> workActionVisibilityConfigurations) {
		this.workActionVisibilityConfigurations = workActionVisibilityConfigurations;
	}

	public List<WorkProcessSubTaskSpec> getWorkProcessSubTaskSpecConfigurations() {
		return workProcessSubTaskSpecConfigurations;
	}

	public void setWorkProcessSubTaskSpecConfigurations(
			List<WorkProcessSubTaskSpec> workProcessSubTaskSpecConfigurations) {
		this.workProcessSubTaskSpecConfigurations = workProcessSubTaskSpecConfigurations;
	}

	public List<AddingSubTaskEmployeeConfiguration> getWorkProcessSubTaskEmployeesConfigurations() {
		return workProcessSubTaskEmployeesConfigurations;
	}

	public void setWorkProcessSubTaskEmployeesConfigurations(
			List<AddingSubTaskEmployeeConfiguration> workProcessSubTaskEmployeesConfigurations) {
		this.workProcessSubTaskEmployeesConfigurations = workProcessSubTaskEmployeesConfigurations;
	}

	public List<WorkToSubTaskAutoFillConfiguration> getWorkToSubTaskAutoFillConfigurations() {
		return workToSubTaskAutoFillConfigurations;
	}

	public void setWorkToSubTaskAutoFillConfigurations(
			List<WorkToSubTaskAutoFillConfiguration> workToSubTaskAutoFillConfigurations) {
		this.workToSubTaskAutoFillConfigurations = workToSubTaskAutoFillConfigurations;
	}

	public List<WorkFieldsUniqueConfigurations> getWorkFieldsUniqueConfigurations() {
		return workFieldsUniqueConfigurations;
	}

	public void setWorkFieldsUniqueConfigurations(
			List<WorkFieldsUniqueConfigurations> workFieldsUniqueConfigurations) {
		this.workFieldsUniqueConfigurations = workFieldsUniqueConfigurations;
	}
	
	public List<WorkReassignmentRules> getWorkReassignmentRules() {
		return workReassignmentRules;
	}

	public void setWorkReassignmentRules(
			List<WorkReassignmentRules> workReassignmentRules) {
		this.workReassignmentRules = workReassignmentRules;
	}

	public List<WorkSpecAppLabel> getWorkSpecAppLabels() {
		return workSpecAppLabels;
	}

	public void setWorkSpecAppLabels(List<WorkSpecAppLabel> workSpecAppLabel) {
		workSpecAppLabels = workSpecAppLabel;
	}

	public List<HideAddSubTaskConfiguration> getHideAddSubTaskConfigurations() {
		return hideAddSubTaskConfigurations;
	}

	public void setHideAddSubTaskConfigurations(
			List<HideAddSubTaskConfiguration> hideAddSubTaskConfigurations) {
		this.hideAddSubTaskConfigurations = hideAddSubTaskConfigurations;
	}

	public List<WorkSpecPermission> getWorkSpecPermissions() {
		return workSpecPermissions;
	}

	public void setWorkSpecPermissions(List<WorkSpecPermission> workSpecPermissions) {
		this.workSpecPermissions = workSpecPermissions;
	}

	public List<WorkUnassignmentCriterias> getWorkUnassignmentCriterias() {
		return workUnassignmentCriterias;
	}

	public void setWorkUnassignmentCriterias(List<WorkUnassignmentCriterias> workUnassignmentCriterias) {
		this.workUnassignmentCriterias = workUnassignmentCriterias;
	}

	public List<WorkAssignmentCriteriaConditions> getWorkAssignmentCriteriaConditions() {
		return workAssignmentCriteriaConditions;
	}

	public void setWorkAssignmentCriteriaConditions(List<WorkAssignmentCriteriaConditions> workAssignmentCriteriaConditions) {
		this.workAssignmentCriteriaConditions = workAssignmentCriteriaConditions;
	}

	public List<WorkSpecCustomerCallApi> getWorkSpecCustomerCallApis() {
		return workSpecCustomerCallApis;
	}

	public void setWorkSpecCustomerCallApis(List<WorkSpecCustomerCallApi> workSpecCustomerCallApis) {
		this.workSpecCustomerCallApis = workSpecCustomerCallApis;
	}

	public List<WorkActionGroup> getWorkActionGroups() {
		return workActionGroups;
	}

	public void setWorkActionGroups(List<WorkActionGroup> workActionGroups) {
		this.workActionGroups = workActionGroups;
	}

	public List<FormAutoFillSectionFieldsConfiguration> getFormAutoFillSectionFieldsConfiguration() {
		return formAutoFillSectionFieldsConfiguration;
	}

	public void setFormAutoFillSectionFieldsConfiguration(
			List<FormAutoFillSectionFieldsConfiguration> formAutoFillSectionFieldsConfiguration) {
		this.formAutoFillSectionFieldsConfiguration = formAutoFillSectionFieldsConfiguration;
	}

	public List<FormAutoFillSectionConfiguration> getFormAutoFillSectionConfiguration() {
		return formAutoFillSectionConfiguration;
	}

	public void setFormAutoFillSectionConfiguration(
			List<FormAutoFillSectionConfiguration> formAutoFillSectionConfiguration) {
		this.formAutoFillSectionConfiguration = formAutoFillSectionConfiguration;
	}

	public List<ExternalActionConfiguration> getExternalActionConfigurations() {
		return externalActionConfigurations;
	}

	public void setExternalActionConfigurations(List<ExternalActionConfiguration> externalActionConfigurations) {
		this.externalActionConfigurations = externalActionConfigurations;
	}

	public List<WorkFieldsUniqueConfigurations> getOpenWorkFieldsUniqueConfigurations() {
		return openWorkFieldsUniqueConfigurations;
	}

	public void setOpenWorkFieldsUniqueConfigurations(
			List<WorkFieldsUniqueConfigurations> openWorkFieldsUniqueConfigurations) {
		this.openWorkFieldsUniqueConfigurations = openWorkFieldsUniqueConfigurations;
	}
	
	public List<FormToWorkAutoFillSectionConfiguration> getFormToWorkAutoFillSectionConfiguration() {
		return formToWorkAutoFillSectionConfiguration;
	}

	public void setFormToWorkAutoFillSectionConfiguration(
			List<FormToWorkAutoFillSectionConfiguration> formToWorkAutoFillSectionConfiguration) {
		this.formToWorkAutoFillSectionConfiguration = formToWorkAutoFillSectionConfiguration;
	}

	public List<FormToWorkAutoFillSectionFieldsConfiguration> getFormToWorkAutoFillSectionFieldsConfiguration() {
		return formToWorkAutoFillSectionFieldsConfiguration;
	}

	public void setFormToWorkAutoFillSectionFieldsConfiguration(
			List<FormToWorkAutoFillSectionFieldsConfiguration> formToWorkAutoFillSectionFieldsConfiguration) {
		this.formToWorkAutoFillSectionFieldsConfiguration = formToWorkAutoFillSectionFieldsConfiguration;
	}

	public List<WorkAttachmentAutoFill> getWorkAttachmentFormAutoFillStageConfig() {
		return workAttachmentFormAutoFillStageConfig;
	}

	public void setWorkAttachmentFormAutoFillStageConfig(
			List<WorkAttachmentAutoFill> workAttachmentFormAutoFillStageConfig) {
		this.workAttachmentFormAutoFillStageConfig = workAttachmentFormAutoFillStageConfig;
	}

	public List<WorkAttachmentFormAutoFillField> getWorkAttachmentFormAutoFillFieldMapping() {
		return workAttachmentFormAutoFillFieldMapping;
	}

	public void setWorkAttachmentFormAutoFillFieldMapping(
			List<WorkAttachmentFormAutoFillField> workAttachmentFormAutoFillFieldMapping) {
		this.workAttachmentFormAutoFillFieldMapping = workAttachmentFormAutoFillFieldMapping;
	}

	public List<AttachmnetFormAutoFillSectionConfiguration> getAttachmentFormAutoFillSectionConfiguration() {
		return attachmentFormAutoFillSectionConfiguration;
	}

	public void setAttachmentFormAutoFillSectionConfiguration(
			List<AttachmnetFormAutoFillSectionConfiguration> attachmentFormAutoFillSectionConfiguration) {
		this.attachmentFormAutoFillSectionConfiguration = attachmentFormAutoFillSectionConfiguration;
	}

	public List<WorkAttachmentAutoFillSectionFieldsConfiguration> getAttachmentFormAutoFillSectionFieldsConfiguration() {
		return attachmentFormAutoFillSectionFieldsConfiguration;
	}

	public void setAttachmentFormAutoFillSectionFieldsConfiguration(
			List<WorkAttachmentAutoFillSectionFieldsConfiguration> attachmentFormAutoFillSectionFieldsConfiguration) {
		this.attachmentFormAutoFillSectionFieldsConfiguration = attachmentFormAutoFillSectionFieldsConfiguration;
	}
	public List<WorkSpecFeedBackFormSpecMap> getWorkSpecFeedBackFormSpecMap() {
		return workSpecFeedBackFormSpecMap;
	}

	public void setWorkSpecFeedBackFormSpecMap(List<WorkSpecFeedBackFormSpecMap> workSpecFeedBackFormSpecMap) {
		this.workSpecFeedBackFormSpecMap = workSpecFeedBackFormSpecMap;
	}

	public List<WorkSpecListLevelVisibilityConfiguration> getWorkSpecListLevelVisibilityConfigurations() {
		return workSpecListLevelVisibilityConfigurations;
	}

	public void setWorkSpecListLevelVisibilityConfigurations(
			List<WorkSpecListLevelVisibilityConfiguration> workSpecListLevelVisibilityConfigurations) {
		this.workSpecListLevelVisibilityConfigurations = workSpecListLevelVisibilityConfigurations;
	}

	public List<WorkActionFormVisibility> getWorkActionFormVisibility() {
		return workActionFormVisibility;
	}

	public void setWorkActionFormVisibility(List<WorkActionFormVisibility> workActionFormVisibility) {
		this.workActionFormVisibility = workActionFormVisibility;
	}

	public List<WorkSpecCustomDashboardConfiguration> getWorkSpecCustomDashboardConfigurations() {
		return workSpecCustomDashboardConfigurations;
	}

	public void setWorkSpecCustomDashboardConfigurations(
			List<WorkSpecCustomDashboardConfiguration> workSpecCustomDashboardConfigurations) {
		this.workSpecCustomDashboardConfigurations = workSpecCustomDashboardConfigurations;
	}

	public List<WorkSpecCustomDashboardMetric> getWorkSpecCustomDashboardMetrics() {
		return workSpecCustomDashboardMetrics;
	}

	public void setWorkSpecCustomDashboardMetrics(List<WorkSpecCustomDashboardMetric> workSpecCustomDashboardMetrics) {
		this.workSpecCustomDashboardMetrics = workSpecCustomDashboardMetrics;
	}

	public List<ActionableEmployeeGroupSpecs> getActionableEmployeeGroupSpecs() {
		return actionableEmployeeGroupSpecs;
	}

	public void setActionableEmployeeGroupSpecs(List<ActionableEmployeeGroupSpecs> actionableEmployeeGroupSpecs) {
		this.actionableEmployeeGroupSpecs = actionableEmployeeGroupSpecs;
	}

	public List<WorkActionNotificationEscalationMatrix> getWorkActionNotificationEscalationMatrix() {
		return workActionNotificationEscalationMatrix;
	}

	public void setWorkActionNotificationEscalationMatrix(
			List<WorkActionNotificationEscalationMatrix> workActionNotificationEscalationMatrix) {
		this.workActionNotificationEscalationMatrix = workActionNotificationEscalationMatrix;
	}

	public List<WorkActionExcalatedEmpIds> getWorkActionExcalatedEmpIds() {
		return workActionExcalatedEmpIds;
	}

	public void setWorkActionExcalatedEmpIds(List<WorkActionExcalatedEmpIds> workActionExcalatedEmpIds) {
		this.workActionExcalatedEmpIds = workActionExcalatedEmpIds;
	}	
	

}
