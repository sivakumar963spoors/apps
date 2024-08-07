package com.spoors.setting;

public class MobileSqls {
	
	public static final String FORMS_SPECS_LIST_FILTERING_CRITERIAS = "CREATE TABLE forms_specs_list_filtering_criterias (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_FORM_SPECS_VISIBILITY_MAP = "CREATE TABLE forms_specs_form_specs_visibility_map (json_data TEXT DEFAULT (null))";
	public static final String FORMS_SPECS_FORM_SPEC_DATA_SOURCE  = "CREATE TABLE forms_specs_form_spec_data_source (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_JOB_FORM_FIELD_SPEC_MAPPING  = "CREATE TABLE forms_specs_job_form_field_spec_mapping (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_DATA_SOURCE_REQUEST_PARAMS ="CREATE TABLE forms_specs_data_source_request_params (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_DATA_SOURCE_REQUEST_HEADERS = "CREATE TABLE forms_specs_data_source_request_headers (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_DATA_SOURCE_RESPONSE_MAPPINGS = "CREATE TABLE forms_specs_data_source_response_mappings (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_FIELDS = "CREATE TABLE forms_specs_fields (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_SECTION_FIELDS = "CREATE TABLE forms_specs_section_fields (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_FORM_SPECS = "CREATE TABLE forms_specs_form_specs (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_PAGE_SPECS = "CREATE TABLE forms_specs_page_specs (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_FIELD_VALID_VALUES = "CREATE TABLE forms_specs_field_valid_values (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_SECTIONS = "CREATE TABLE forms_specs_sections (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_SECTION_FIELD_VALID_VALUES = "CREATE TABLE forms_specs_section_field_valid_values (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_VISIBILITY_DEPENDENCY_CRITERIAS = "CREATE TABLE forms_specs_visibility_dependency_criterias (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORM_FIELDS_COLOR_DEPENDENCY_CRITERIAS = "CREATE TABLE form_fields_Color_dependency_criterias (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORM_CLEAN_UP_RULES = "CREATE TABLE form_clean_up_rules (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_CUSTOMER_AUTO_FILTERING_CRITERIAS = "CREATE TABLE forms_specs_customer_auto_filtering_criterias (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String REMAINDER_FIELDS_MAP = "CREATE TABLE remainder_fields_map (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String FORMS_SPECS_CUSTOMER_FILTERING_CRITERIAS = "CREATE TABLE forms_specs_customer_filtering_criterias (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_EMPLOYEE_FILTERING_CRITERIAS = "CREATE TABLE forms_specs_employee_filtering_criterias (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_FORM_FILTERING_CRITIRIAS = "CREATE TABLE forms_specs_form_filtering_criterias (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_FIELD_VALIDATION_CRITIRIAS = "CREATE TABLE forms_specs_field_validation_critirias (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_STOCK_FORM_CONFIGURATIONS = "CREATE TABLE forms_specs_stock_form_configurations (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_OFFLINE_LIST_UPDATE_CONFIGURATIONS = "CREATE TABLE forms_specs_offline_list_update_configurations (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_OFFLINE_CUSTOMENTITY_UPDATE_CONFIGURATIONS ="CREATE TABLE forms_specs_offline_customEntity_update_configurations (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";

	public static final String FORMS_SPECS_WORKSPEC_FORMSPEC_FOLLOW_UP = "CREATE TABLE forms_specs_workspec_formspec_follow_up (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_WORK_FORM_FIELD_MAP = "CREATE TABLE forms_specs_work_form_field_map (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORM_FIELD_GROUP_SPECS = "CREATE TABLE form_field_group_specs (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String FORM_FIELD_SPECS_EXTRA = "CREATE TABLE form_field_specs_extra (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORM_SECTION_FIELD_SPECS_EXTRA = "CREATE TABLE form_section_field_specs_extra (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORMS_SPECS_CUSTOM_ENTITY_FILTERING_CRITIRIAS ="CREATE TABLE forms_specs_custom_entity_filtering_criterias (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";

	public static final String FORM_SPEC_PERMISSIONS = "CREATE TABLE form_spec_permissions (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String SAVE_FORM_ON_OTP_VERIFY = "CREATE TABLE save_form_on_otp_verify (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String WORKS_SPECS_WORK_SPECS = "CREATE TABLE works_specs_work_specs (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORKS_SPECS_NEXT_WORK_SPECS = "CREATE TABLE works_specs_next_work_specs (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORKS_SPECS_NEXT_ACTION_SPECS = "CREATE TABLE works_specs_next_action_specs (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORKS_SPECS_WORK_ACTION_SPECS = "CREATE TABLE works_specs_work_action_specs (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORKS_SPECS_WORK_SPEC_FORM_SPEC_MAPS = "CREATE TABLE works_specs_work_spec_form_spec_maps (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORKS_SPECS_WORK_ACTION_SPEC_CONDITIONS= "CREATE TABLE works_specs_work_action_spec_conditions (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORKS_SPECS_WORK_ACTION_SPEC_END_CONDITIONS= "CREATE TABLE works_specs_work_action_spec_end_conditions (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORKS_SPECS_WORK_FIELDS_UNIQUE_CONFIGURATIONS = "CREATE TABLE works_specs_work_fields_unique_configurations (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String WORK_SPEC_CUSTOMER_CALL_APIS = "CREATE TABLE work_spec_customer_call_apis (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORKS_SPECS_FORM_AUTO_FILL_ACTION_CONFIG = "CREATE TABLE works_specs_form_auto_fill_action_config (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORKS_SPECS_FORM_AUTO_FILL_FIELD_MAPPING = "CREATE TABLE works_specs_form_auto_fill_field_mapping (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String WORKS_SPECS_FORM_ATTACHMENT_AUTO_FILL_ACTION_CONFIG = "CREATE TABLE works_specs_attchment_form_auto_fill_action_config (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORKS_SPECS_FORM_ATTACHMENT_AUTO_FILL_FIELD_MAPPING = "CREATE TABLE works_specs_attchment_form_auto_fill_field_mapping (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String ATTACHMENT_FORM_AUTO_FILL_SECTION_CONFIG = "CREATE TABLE attachment_form_auto_fill_section_config (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String ATTACHMENT_FORM_AUTO_FILL_SECTION_FIELDS_CONFIG = "CREATE TABLE attachment_form_auto_fill_section_fields_config (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String WORK_SPECS_WORK_ACTION_GROUPS = "CREATE TABLE work_specs_work_action_groups (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORK_SPECS_FORM_TO_WORK_AUTO_FILL_ACTION_CONFIG = "CREATE TABLE works_specs_form_to_work_auto_fill_action_config (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORK_SPECS_FORM_TO_WORK_AUTO_FILL__FIELD_MAPPING = "CREATE TABLE works_specs_form_to_work_auto_fill_field_mapping (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String FORM_AUTO_FILL_SECTION_CONFIG = "CREATE TABLE form_auto_fill_section_config (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String FORM_AUTO_FILL_SECTION_FIELDS_CONFIG = "CREATE TABLE form_auto_fill_section_fields_config (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORM_TO_WORK_AUTO_FILL_SECTION_CONFIG = "CREATE TABLE form_to_work_auto_fill_section_config (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String FORM_TO_WORK_AUTO_FILL_SECTION_FIELDS_CONFIG = "CREATE TABLE form_to_work_auto_fill_section_fields_config (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String WORKS_SPECS_OPEN_WORK_FIELDS_UNIQUE_CONFIGURATIONS = "CREATE TABLE works_specs_open_work_fields_unique_configurations (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORK_RE_ASSIGNMENT_RULES = "CREATE TABLE work_re_assignment_rules (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORK_SPEC_APP_LABELS = "CREATE TABLE work_spec_app_labels (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String WORK_ACTION_VISIBILITY_CONFIGURATION = "CREATE TABLE work_action_visibility_configuration (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORK_ACTION_VISIBILITY_ASSIGNMENTS = "CREATE TABLE work_action_visibility_assignments (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String WORK_PROCESS_SUB_TASK_SPEC_CONFIGURATIONS = "CREATE TABLE work_process_sub_task_spec_configurations (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	public static final String WORK_PROCESS_SUB_TASK_EMPLOYEES_CONFIGURATIONS = "CREATE TABLE work_process_sub_task_employees_configurations (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String WORK_TO_SUBTASK_AUTO_FILL_CONFIGURATION = "CREATE TABLE work_to_sub_task_auto_fill_configuration (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	public static final String HIDE_ADD_SUB_TASK_CONFIGURATIONS = "CREATE TABLE hide_add_sub_task_configurations (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	
	 public static final String WORK_SPEC_PERMISSIONS = "CREATE TABLE work_spec_permissions (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	 public static final String WORK_UNASSIGNMENT_CRITERIAS = "CREATE TABLE work_unassignment_criterias (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	 public static final String WORK_ASSIGNMENT_CRITERIA_CONDITIONS = "CREATE TABLE work_assignment_criteria_conditions (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	 public static final String WORKFLOW_SPECS_VISIBILITY_CONDITIONS= "CREATE TABLE workflow_entity_visibility_conditions (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	 public static final String WORKS_SPECS_WORK_ACTION_SPEC_EXTERNAL_ACTION_CONFIG = "CREATE TABLE works_specs_work_action_spec_external_action_config (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	 
	 public static final String WORK_SPEC_LIST_LEVEL_VISIBILITY_CONFIGURATIONS = "CREATE TABLE work_spec_list_level_visibility_configurations (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	 
	 public static final String WORK_ACTION_FORM_VISIBLITY = "CREATE TABLE work_action_form_visibility (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	 public static final String WORKSPEC_CUSTOM_DASHBOARD_METRICS = "CREATE TABLE workSpec_custom_dashboard_metrics (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	 public static final String ACTIONABLE_EMPLOYEE_GROUP_SPECS = "CREATE TABLE actionable_employee_group_specs (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	 public static final String WORK_ACTION_NOTIFICATION_ESCALATION_MATRIX = "CREATE TABLE work_action_notification_escalation_matrix (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	 
	 public static final String WORK_ACTION_EXCALATION_EMPIDS = "CREATE TABLE work_action_excalated_empIds (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	 public static final String WORK_SPEC_CUSTOM_DASHBOARD_CONFIGURATIONS = "CREATE TABLE work_spec_custom_dashboard_configurations (json_data TEXT DEFAULT (null),processed INTEGER,autoId INTEGER)";
	    


	
	

}
