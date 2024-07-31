package com.spoors.beans.workSpecs;

import com.spoors.util.Api;

public class NotificationSpecView {
	private long id;
	private long companyId;
	private long empId;
	private int type;
	private String name;
	private String messageSubject;
	private String messageBody;
	private boolean deliverBySms;
	private boolean deliverByMail;
	private boolean deliverBySync;
//	private String deliverToSms;
//	private String deliverToEmail;
	private String deliverToWhatsApp;
	private String rule;
	private int ruleType;
	private String extraRule;
	private long createdBy;
	
	private int change;
	private boolean atCustomerLocation;
	
	private boolean jobIsDelayed;
	private Long jobEmpId;
	private Long visitTypeId;
	private Long visitStageId;
	private Integer formChange;
	
	private String phoneNumbersOpp;
	private String phoneNumbers;
	private String phoneNumbersForWhatsApp;
	private String emailAddressOpp;
	private String emailAddress;
	private long bufferTime;
//	private boolean unassignedJobs;
	private boolean jobNotClosed;
	private boolean notSynced;
	private boolean notNearClientLocation;
	private long radius;
	private long remindAfter;
	private int qualityType;
	private String workflowCriteria; 
	private String punchInPunchOutCritiria;
	private String punchInPunchOutEmpId;
	private String routePlanCriteria;
	private String workflowMaped; 
	private boolean pdfAttachment;
	private boolean mediaAttachment;
	private boolean canTriggerTwoMails;
	private String leaveCriteria;
	private Long leaveEmpId;
	private Long matrixId;
	private String notificationSpecRuleJson;
	private String extraWorkRule;
	//Deva,2017-01-26,Janak,Custom notifcation : sending notification based on dates selected in entity
	private String notificationSpecListRuleJson;  // in DB we are storing in notificationSpecRuleJson column
	private String extraListRule;                 //in DB we are storing in extraWorkRule
	private String extraListRuleError;
	private String workSpecIdForActionNotification;
	private String workActionSpecIds;
	private String workActionSpecsError;
	
	private String workFlowFormSpecUniqueId;
	
	private String notificationSpecFormsExtraRuleJson;
	private String formsExtraRule;
	
	private boolean toImmediateManager;
	private boolean toHierarchy;
	
	private boolean smsToImmediateManager;
	private boolean smsToHierarchy;
	
	private boolean syncToImmediateManager;
	private boolean syncToHierarchy;
	
	private String employeeIdOpp;
	private String employeeIds;
	
	private Long workActionSpecId;
	
	private Long matrixIdForWorkAction;
	
	private Long empGroupId;
	private String leaveTypeEntityIds;
	private String appliedDuration;
	
	private Long territoryEmpId;
	
	private String workActionSpecIdCsv;
	private String beforeOrAfter;
	private boolean approveOrRejectInMail;
	
	//Haritha
	private int searchCriteria;
	private int searchByTime;
	private int searchByTimeSpin;
	private int searchByOther;
	private int notificationTriggeringFeq;
	private int triggerMonthSpin;
	private int triggerWeekDay;
	private int triggerDaySpin;
	private int leaveRemainder;
	
	private String workInvitationCriteria;
	private String workInvitationWorkSpecId;
	
	  private boolean deliverByWhatsApp;
	  private String whatsAppMessageBody;
	  
	  private boolean whatsAppToImmediateManager;
	  private boolean whatsAppToHierarchy; 
	  
	  private String whatsAppNumbersOpp;
		private String whatsAppNumbers;
		
		private int formCriteria;
		private String timeBasedFormSpecUniqueId;
		private String timeBasedFormFieldSpecUniqueId;
		private String triggerTime;
		private String timeBasedFormExtraRule;
	   private Integer expiryDuration;
		private String dayPlanCriteria;
		private String activityFormUniqueId;
		private String checkinCheckOut = "1";
		private Long customEntitySpecIdForCheckIn;
		private String customEntityCheckInCheckOutEvent = "1";
		private String customEntityActivityFormUniqueId;
		
		private String customEntityFormFieldSpecId;
		private Long customEntitySpecIdForTimeBased;
		private String customEntityFormSpecUniqueId;
		private Integer timeBasedTriggerType;
		private Integer numberOfDays;
		
		private String customEntityFormFieldSpecIdError;
		private String numberOfDaysError;
		private String timeBasedRemainderMaxCountError;
		private String timeBasedBufferTimeError;
		
		
	//For work not created notification constants
	public static final int SEARCH_CRITERIA_FOR_TIME = 0;
	public static final int SEARCH_CRITERIA_FOR_OTHER = 1;
	public static final int NOTIFY_WORKNOT_CREATED_FROM_CREATED_TIME = 1;
	public static final int NOTIFY_WORKNOT_CREATED_FROM_START_TIME = 2;
	public static final int NOTIFY_WORKNOT_CREATED_FROM_END_TIME = 3;
	public static final int SEARCH_WORKS_FOR_LAST_MONTH = 1;
	public static final int SEARCH_WORKS_FOR_LAST_WEEK = 2;
	public static final int SEARCH_WORKS_FOR_YERSTARDAY = 3;
	public static final int NOTIFICATION_TRIGGER_DAYILY = 1;
	public static final int NOTIFICATION_TRIGGER_WEEKLY = 2;
	public static final int NOTIFICATION_TRIGGER_MONTHLY = 3;
	
	private int deleted;
	
	private String smsMessageBody;
	private boolean sendTrackingLink;
	private boolean sendEmployeeTrackingLink;
	String empGroupIds;
	
	private String timeBasedFormSpecUniqueIdError;
	private String timeBasedFormFieldSpecIdError;
	private String triggerTimeError;
	private boolean enableSignInRemainderNotification;
	private long signInRemainderMaxCount;
	private Long eventBasedBufferTime;
	private String sectionFieldsTabularFormatList;
	private boolean enableEventBasedRemainderNotification;
	private Long eventBasedRemainderMaxCount;
	private int workPdfType;
	private boolean sendApprovalStatusNotification;
	private boolean sendSmsApprovalStatusNotification;
	
	private Boolean timeBasedRemainderNotification;
	private Integer timeBasedRemainderMaxCount;
	private Integer timeBasedBufferTime;
	private boolean sendWhatsAppApprovalStatusNotification;


	public String getWorkActionSpecIdCsv() {
		return workActionSpecIdCsv;
	}

	public void setWorkActionSpecIdCsv(String workActionSpecIdCsv) {
		this.workActionSpecIdCsv = workActionSpecIdCsv;
	}
	
	public Long getEmpGroupId() {
		return empGroupId;
	}

	public void setEmpGroupId(Long empGroupId) {
		this.empGroupId = empGroupId;
	}

	public Long getWorkActionSpecId() {
		return workActionSpecId;
	}

	public void setWorkActionSpecId(Long workActionSpecId) {
		this.workActionSpecId = workActionSpecId;
	}

	public String getEmployeeIdOpp() {
		return employeeIdOpp;
	}

	public void setEmployeeIdOpp(String employeeIdOpp) {
		this.employeeIdOpp = employeeIdOpp;
	}

	public String getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String employeeIds) {
		this.employeeIds = employeeIds;
	}

	public boolean isPdfAttachment() {
		return pdfAttachment;
	}

	public void setPdfAttachment(boolean pdfAttachment) {
		this.pdfAttachment = pdfAttachment;
	}

	public boolean isMediaAttachment() {
		return mediaAttachment;
	}

	public void setMediaAttachment(boolean mediaAttachment) {
		this.mediaAttachment = mediaAttachment;
	}

	private Long workSpecId;
	private Long workStageId;
	private Long entitySpecId;
	
	
  public long getBufferTime() {
    return bufferTime;
  }

  public void setBufferTime(long bufferTime) {
    this.bufferTime = bufferTime;
  }

  
  public long getRemindAfter() {
	return remindAfter;
}

public void setRemindAfter(long remindAfter) {
	this.remindAfter = remindAfter;
}

public long getRadius() {
    return radius;
  }

  public void setRadius(long radius) {
    this.radius = radius;
  }

  public boolean isJobNotClosed() {
    return jobNotClosed;
  }

  public void setJobNotClosed(boolean jobNotClosed) {
    this.jobNotClosed = jobNotClosed;
  }

  public boolean isNotSynced() {
    return notSynced;
  }

  public void setNotSynced(boolean notSynced) {
    this.notSynced = notSynced;
  }

  public boolean isNotNearClientLocation() {
    return notNearClientLocation;
  }

  public void setNotNearClientLocation(boolean notNearClientLocation) {
    this.notNearClientLocation = notNearClientLocation;
  }

  private Long workflowId;
  
  
  
  public Long getWorkflowId() {
    return workflowId;
  }

  public void setWorkflowId(Long workflowId) {
    this.workflowId = workflowId;
  }

  private Long formSpecId;
	
	public NotificationSpecView() {
	}
	
	public NotificationSpecView(NotificationSpec notificationSpec) {
		this.id = notificationSpec.getId();
		this.companyId = notificationSpec.getCompanyId();
		this.empId = notificationSpec.getEmpId();
		this.type = notificationSpec.getType();
		this.name = notificationSpec.getName();
		this.messageSubject = notificationSpec.getMessageSubject();
		this.messageBody = notificationSpec.getMessageBody();
		this.deliverBySms = notificationSpec.isDeliverBySms();
		this.deliverByMail = notificationSpec.isDeliverByMail();
		this.deliverBySync = notificationSpec.isDeliverBySync();
		this.rule = notificationSpec.getRule();
		this.extraRule = notificationSpec.getExtraRule();
		this.createdBy = notificationSpec.getCreatedBy();
		this.ruleType = notificationSpec.getRuleType();
		this.qualityType=notificationSpec.getQualityType();
		this.pdfAttachment=notificationSpec.isPdfAttachment();
		this.mediaAttachment=notificationSpec.isMediaAttachment();
		this.matrixId=notificationSpec.getMatrixId();
		this.extraWorkRule = notificationSpec.getExtraWorkRule();
		this.notificationSpecRuleJson=notificationSpec.getNotificationSpecRuleJson();
		this.canTriggerTwoMails=notificationSpec.isCanTriggerTwoMails();
		this.phoneNumbers = "";
		this.notificationSpecFormsExtraRuleJson =notificationSpec.getNotificationSpecFormsExtraRuleJson();
		this.formsExtraRule=notificationSpec.getFormsExtraRule();
		this.toImmediateManager=notificationSpec.isToImmediateManager();
		this.toHierarchy=notificationSpec.isToHierarchy();
		this.smsToImmediateManager = notificationSpec.isSmsToImmediateManager();
		this.smsToHierarchy= notificationSpec.isSmsToHierarchy();
		this.syncToImmediateManager = notificationSpec.isSyncToImmediateManager();
		this.syncToHierarchy = notificationSpec.isSyncToHierarchy();
		this.smsMessageBody = notificationSpec.getSmsMessageBody();
		this.sendTrackingLink = notificationSpec.isSendTrackingLink();
		this.whatsAppNumbers = "";
		this.deliverByWhatsApp = notificationSpec.isDeliverByWhatsApp();
		this.whatsAppMessageBody = notificationSpec.getWhatsAppMessageBody();
		this.whatsAppToImmediateManager = notificationSpec.isWhatsAppToImmediateManager();
		this.whatsAppToHierarchy = notificationSpec.isWhatsAppToHierarchy();
		this.deleted = notificationSpec.isDeleted()?1:0;
		this.sendEmployeeTrackingLink = notificationSpec.isSendEmployeeTrackingLink();
		this.expiryDuration = notificationSpec.getExpiryDuration();
		this.enableSignInRemainderNotification = notificationSpec.isEnableSignInRemainderNotification();
		this.signInRemainderMaxCount = notificationSpec.getSignInRemainderMaxCount();
		this.eventBasedBufferTime = notificationSpec.getEventBasedBufferTime();
		this.workPdfType = notificationSpec.getWorkPdfType();
		this.sendApprovalStatusNotification = notificationSpec.isSendApprovalStatusNotification();
		this.sendSmsApprovalStatusNotification = notificationSpec.isSendSmsApprovalStatusNotification();
		this.sendWhatsAppApprovalStatusNotification = notificationSpec.isSendWhatsAppApprovalStatusNotification();

		
		if(!com.spoors.util.Api.isEmptyString(notificationSpec.getDeliverToWhatsApp())){
			String[] tokens = com.spoors.util.Api.csvToArray(notificationSpec.getDeliverToWhatsApp());
			for (String token : tokens) {
				if(!com.spoors.util.Api.isEmptyString(token) 
						&& !token.startsWith("<")
						&& !token.endsWith(">")){
					
					if(!Api.isEmptyString(whatsAppNumbers)){
						whatsAppNumbers += ",";
					}
					
					whatsAppNumbers += token;
				}
			}
		}

		if(!Api.isEmptyString(notificationSpec.getDeliverToSms())){
			String[] tokens = Api.csvToArray(notificationSpec.getDeliverToSms());
			for (String token : tokens) {
				if(!Api.isEmptyString(token) 
						&& !token.startsWith("<")
						&& !token.endsWith(">")){
					
					if(!Api.isEmptyString(phoneNumbers)){
						phoneNumbers += ",";
					}
					
					phoneNumbers += token;
				}
			}
		}
		
		this.emailAddress = "";
		if(!Api.isEmptyString(notificationSpec.getDeliverToEmail())){
			String[] tokens = Api.csvToArray(notificationSpec.getDeliverToEmail());
			for (String token : tokens) {
				if(!Api.isEmptyString(token) 
						&& !token.startsWith("<")
						&& !token.endsWith(">")){
					
					if(!Api.isEmptyString(emailAddress)){
						emailAddress += ",";
					}
					
					emailAddress += token;
				}
			}
		}
		
		
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessageSubject() {
		return messageSubject;
	}
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public boolean isDeliverBySms() {
		return deliverBySms;
	}
	public void setDeliverBySms(boolean deliverBySms) {
		this.deliverBySms = deliverBySms;
	}
	public boolean isDeliverByMail() {
		return deliverByMail;
	}
	public void setDeliverByMail(boolean deliverByMail) {
		this.deliverByMail = deliverByMail;
	}
	public boolean isDeliverBySync() {
		return deliverBySync;
	}
	public void setDeliverBySync(boolean deliverBySync) {
		this.deliverBySync = deliverBySync;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public String getExtraRule() {
		return extraRule;
	}
	public void setExtraRule(String extraRule) {
		this.extraRule = extraRule;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public int getChange() {
		return change;
	}
	public void setChange(int change) {
		this.change = change;
	}
	public boolean isAtCustomerLocation() {
		return atCustomerLocation;
	}
	public void setAtCustomerLocation(boolean atCustomerLocation) {
		this.atCustomerLocation = atCustomerLocation;
	}
	public boolean isJobIsDelayed() {
		return jobIsDelayed;
	}
	public void setJobIsDelayed(boolean jobIsDelayed) {
		this.jobIsDelayed = jobIsDelayed;
	}
	public Long getJobEmpId() {
		return jobEmpId;
	}
	public void setJobEmpId(Long jobEmpId) {
		this.jobEmpId = jobEmpId;
	}
	public Long getVisitTypeId() {
		return visitTypeId;
	}
	public void setVisitTypeId(Long visitTypeId) {
		this.visitTypeId = visitTypeId;
	}
	public Long getVisitStageId() {
		return visitStageId;
	}
	public void setVisitStageId(Long visitStageId) {
		this.visitStageId = visitStageId;
	}
	public Integer getFormChange() {
		return formChange;
	}
	public void setFormChange(Integer formChange) {
		this.formChange = formChange;
	}
	
	public String getPhoneNumbersOpp() {
		return phoneNumbersOpp;
	}

	public void setPhoneNumbersOpp(String phoneNumbersOpp) {
		this.phoneNumbersOpp = phoneNumbersOpp;
	}

	public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getEmailAddressOpp() {
		return emailAddressOpp;
	}

	public void setEmailAddressOpp(String emailAddressOpp) {
		this.emailAddressOpp = emailAddressOpp;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Long getFormSpecId() {
		return formSpecId;
	}

	public void setFormSpecId(Long formSpecId) {
		this.formSpecId = formSpecId;
	}
	
	
	public int getQualityType() {
		return qualityType;
	}

	public void setQualityType(int qualityType) {
		this.qualityType = qualityType;
	}

	public NotificationSpec getNotificationSpec(){
		String deliverToSms = phoneNumbersOpp;
		String deliverToEmail = emailAddressOpp;
		String deliverToSync = employeeIdOpp;
		String deliverToWhatsApp = whatsAppNumbersOpp;
		
		if(!Api.isEmptyString(whatsAppNumbers)){
			if(!Api.isEmptyString(deliverToWhatsApp)){
				deliverToWhatsApp += ",";
			}
			deliverToWhatsApp = deliverToWhatsApp == null ? "" : deliverToWhatsApp;
			deliverToWhatsApp += whatsAppNumbers;
		}
		
		if(!Api.isEmptyString(phoneNumbers)){
			if(!Api.isEmptyString(deliverToSms)){
				deliverToSms += ",";
			}
			deliverToSms = deliverToSms == null ? "" : deliverToSms;
			deliverToSms += phoneNumbers;
		}
		
		if(!Api.isEmptyString(emailAddress)){
			if(!Api.isEmptyString(deliverToEmail)){
				deliverToEmail += ",";
			}
			deliverToEmail = deliverToEmail == null ? "" : deliverToEmail;
			deliverToEmail += emailAddress;
		}
		
		if(!Api.isEmptyString(employeeIds)){
			if(!Api.isEmptyString(deliverToSync)){
				deliverToSync += ",";
			}
			deliverToSync = deliverToSync == null ? "" : deliverToSync;
			deliverToSync += employeeIds;
		}
		
		if(matrixId == null && matrixIdForWorkAction != null){
			matrixId = matrixIdForWorkAction;
		}
		
		
		return new NotificationSpec(id, companyId, empId, type,ruleType, name, messageSubject, messageBody, 
				deliverBySms, deliverByMail, deliverBySync, deliverToSms, deliverToEmail,deliverToSync, rule, extraRule, 
				createdBy,qualityType,pdfAttachment,mediaAttachment,matrixId,notificationSpecRuleJson,
				extraWorkRule,canTriggerTwoMails,notificationSpecFormsExtraRuleJson,formsExtraRule,
				toImmediateManager,toHierarchy,smsToImmediateManager,smsToHierarchy,syncToImmediateManager,syncToHierarchy,
				smsMessageBody,sendTrackingLink,deliverByWhatsApp,deliverToWhatsApp,whatsAppMessageBody,whatsAppToImmediateManager,
				whatsAppToHierarchy,sendEmployeeTrackingLink,expiryDuration,enableSignInRemainderNotification,signInRemainderMaxCount,
				eventBasedBufferTime,enableEventBasedRemainderNotification,eventBasedRemainderMaxCount,workPdfType,sendApprovalStatusNotification,sendSmsApprovalStatusNotification,deliverToWhatsApp,sendWhatsAppApprovalStatusNotification);
	}


 /* public boolean isUnassignedJobs() {
    return unassignedJobs;
  }

  public void setUnassignedJobs(boolean unassignedJobs) {
    this.unassignedJobs = unassignedJobs;
  }*/

      public int getRuleType() {
        return ruleType;
      }
    
      public void setRuleType(int ruleType) {
        this.ruleType = ruleType;
      }
    
    public String getWorkflowCriteria() {
        return workflowCriteria;
    }
    
    public void setWorkflowCriteria(String workflowCriteria) {
        this.workflowCriteria = workflowCriteria;
    }
    
    public String getWorkflowCriteriaWithDelimiterD() {
        if(!Api.isEmptyString(workflowCriteria)) {
        	return	workflowCriteria.replaceAll(",", "D");
        }
       return workflowCriteria;  
    }
    
    public String getWorkflowCriteriaWithDelimiterComma() {
        if(!Api.isEmptyString(workflowCriteria)) {
        	return	workflowCriteria.replaceAll("D", ",");
        }
       return workflowCriteria;  
    }
    
    public String getWorkflowMapedWithDelimiterD() {
    	if(!Api.isEmptyString(workflowMaped)) {
    		return	workflowMaped.replaceAll(",", "D");
        }
       return workflowMaped;  
    }
    
    public String getWorkflowMapedWithDelimiterComma() {
        if(!Api.isEmptyString(workflowMaped)) {
        	return	workflowMaped.replaceAll("D", ",");
        }
       return workflowMaped;  
    }

    public String getWorkflowMaped() {
        return workflowMaped;
    }

    public void setWorkflowMaped(String workflowMaped) {
        this.workflowMaped = workflowMaped;
    }

	public String getRoutePlanCriteria() {
		return routePlanCriteria;
	}

	public void setRoutePlanCriteria(String routePlanCriteria) {
		this.routePlanCriteria = routePlanCriteria;
	}

	public String getPunchInPunchOutCritiria() {
		return punchInPunchOutCritiria;
	}

	public void setPunchInPunchOutCritiria(String punchInPunchOutCritiria) {
		this.punchInPunchOutCritiria = punchInPunchOutCritiria;
	}

	public String getPunchInPunchOutEmpId() {
		return punchInPunchOutEmpId;
	}

	public void setPunchInPunchOutEmpId(String punchInPunchOutEmpId) {
		this.punchInPunchOutEmpId = punchInPunchOutEmpId;
	}

	public Long getWorkSpecId() {
		return workSpecId;
	}

	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}

	public Long getWorkStageId() {
		return workStageId;
	}

	public void setWorkStageId(Long workStageId) {
		this.workStageId = workStageId;
	}

	public String getLeaveCriteria() {
		return leaveCriteria;
	}

	public void setLeaveCriteria(String leaveCriteria) {
		this.leaveCriteria = leaveCriteria;
	}

	public Long getLeaveEmpId() {
		return leaveEmpId;
	}

	public void setLeaveEmpId(Long leaveEmpId) {
		this.leaveEmpId = leaveEmpId;
	}

	public Long getMatrixId() {
		return matrixId;
	}

	public void setMatrixId(Long matrixId) {
		this.matrixId = matrixId;
	}

	public String getExtraWorkRule() {
		return extraWorkRule;
	}

	public void setExtraWorkRule(String extraWorkRule) {
		this.extraWorkRule = extraWorkRule;
	}

	public String getNotificationSpecRuleJson() {
		return notificationSpecRuleJson;
	}

	public void setNotificationSpecRuleJson(String notificationSpecRuleJson) {
		this.notificationSpecRuleJson = notificationSpecRuleJson;
	}

	public Long getEntitySpecId() {
		return entitySpecId;
	}

	public void setEntitySpecId(Long entitySpecId) {
		this.entitySpecId = entitySpecId;
	}

	public String getNotificationSpecListRuleJson() {
		return notificationSpecListRuleJson;
	}

	public void setNotificationSpecListRuleJson(String notificationSpecListRuleJson) {
		this.notificationSpecListRuleJson = notificationSpecListRuleJson;
	}

	public String getExtraListRule() {
		return extraListRule;
	}

	public void setExtraListRule(String extraListRule) {
		this.extraListRule = extraListRule;
	}

	public String getExtraListRuleError() {
		return extraListRuleError;
	}

	public void setExtraListRuleError(String extraListRuleError) {
		this.extraListRuleError = extraListRuleError;
	}

	public String getWorkActionSpecIds() {
		return workActionSpecIds;
	}

	public void setWorkActionSpecIds(String workActionSpecIds) {
		this.workActionSpecIds = workActionSpecIds;
	}

	public String getWorkSpecIdForActionNotification() {
		return workSpecIdForActionNotification;
	}

	public void setWorkSpecIdForActionNotification(String workSpecIdForActionNotification) {
		this.workSpecIdForActionNotification = workSpecIdForActionNotification;
	}

	public String getWorkActionSpecsError() {
		return workActionSpecsError;
	}

	public void setWorkActionSpecsError(String workActionSpecsError) {
		this.workActionSpecsError = workActionSpecsError;
	}

	public String getWorkFlowFormSpecUniqueId() {
		return workFlowFormSpecUniqueId;
	}

	public void setWorkFlowFormSpecUniqueId(String workFlowFormSpecUniqueId) {
		this.workFlowFormSpecUniqueId = workFlowFormSpecUniqueId;
	}

	public boolean isCanTriggerTwoMails() {
		return canTriggerTwoMails;
	}

	public void setCanTriggerTwoMails(boolean canTriggerTwoMails) {
		this.canTriggerTwoMails = canTriggerTwoMails;
	}

	public String getNotificationSpecFormsExtraRuleJson() {
		return notificationSpecFormsExtraRuleJson;
	}

	public void setNotificationSpecFormsExtraRuleJson(String notificationSpecFormsExtraRuleJson) {
		this.notificationSpecFormsExtraRuleJson = notificationSpecFormsExtraRuleJson;
	}

	public String getFormsExtraRule() {
		return formsExtraRule;
	}

	public void setFormsExtraRule(String formsExtraRule) {
		this.formsExtraRule = formsExtraRule;
	}

	public boolean isToImmediateManager() {
		return toImmediateManager;
	}

	public void setToImmediateManager(boolean toImmediateManager) {
		this.toImmediateManager = toImmediateManager;
	}

	public boolean isToHierarchy() {
		return toHierarchy;
	}

	public void setToHierarchy(boolean toHierarchy) {
		this.toHierarchy = toHierarchy;
	}

	public boolean isSmsToImmediateManager() {
		return smsToImmediateManager;
	}

	public void setSmsToImmediateManager(boolean smsToImmediateManager) {
		this.smsToImmediateManager = smsToImmediateManager;
	}

	public boolean isSmsToHierarchy() {
		return smsToHierarchy;
	}

	public void setSmsToHierarchy(boolean smsToHierarchy) {
		this.smsToHierarchy = smsToHierarchy;
	}
	
	public boolean isSyncToImmediateManager() {
		return syncToImmediateManager;
	}

	public void setSyncToImmediateManager(boolean syncToImmediateManager) {
		this.syncToImmediateManager = syncToImmediateManager;
	}

	public boolean isSyncToHierarchy() {
		return syncToHierarchy;
	}

	public void setSyncToHierarchy(boolean syncToHierarchy) {
		this.syncToHierarchy = syncToHierarchy;
	}

	public Long getMatrixIdForWorkAction() {
		return matrixIdForWorkAction;
	}

	public void setMatrixIdForWorkAction(Long matrixIdForWorkAction) {
		this.matrixIdForWorkAction = matrixIdForWorkAction;
	}
	
	public String getLeaveTypeEntityIds() {
		return leaveTypeEntityIds;
	}

	public void setLeaveTypeEntityIds(String leaveTypeEntityIds) {
		this.leaveTypeEntityIds = leaveTypeEntityIds;
	}

	public String getAppliedDuration() {
		
		if(appliedDuration == null) {
			appliedDuration = "";
		}
		return appliedDuration;
	}

	public void setAppliedDuration(String appliedDuration) {
		if(appliedDuration == null) {
			appliedDuration = "";
		}
		this.appliedDuration = appliedDuration;
	}

	public Long getTerritoryEmpId() {
		return territoryEmpId;
	}

	public void setTerritoryEmpId(Long territoryEmpId) {
		this.territoryEmpId = territoryEmpId;
	}

	public int getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(int searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	public int getSearchByTime() {
		return searchByTime;
	}

	public void setSearchByTime(int searchByTime) {
		this.searchByTime = searchByTime;
	}

	public int getSearchByTimeSpin() {
		return searchByTimeSpin;
	}

	public void setSearchByTimeSpin(int searchByTimeSpin) {
		this.searchByTimeSpin = searchByTimeSpin;
	}

	public int getSearchByOther() {
		return searchByOther;
	}

	public void setSearchByOther(int searchByOther) {
		this.searchByOther = searchByOther;
	}

	public int getNotificationTriggeringFeq() {
		return notificationTriggeringFeq;
	}

	public void setNotificationTriggeringFeq(int notificationTriggeringFeq) {
		this.notificationTriggeringFeq = notificationTriggeringFeq;
	}

	public int getTriggerMonthSpin() {
		return triggerMonthSpin;
	}

	public void setTriggerMonthSpin(int triggerMonthSpin) {
		this.triggerMonthSpin = triggerMonthSpin;
	}
	
	public String getBeforeOrAfter() {
		return beforeOrAfter;
	}

	public void setBeforeOrAfter(String beforeOrAfter) {
		this.beforeOrAfter = beforeOrAfter;
	}
	
	public boolean isApproveOrRejectInMail() {
		return approveOrRejectInMail;
	}

	public void setApproveOrRejectInMail(boolean approveOrRejectInMail) {
		this.approveOrRejectInMail = approveOrRejectInMail;
	}
	public int getTriggerDaySpin() {
		return triggerDaySpin;
	}

	public void setTriggerDaySpin(int triggerDaySpin) {
		this.triggerDaySpin = triggerDaySpin;
	}

	public int getTriggerWeekDay() {
		return triggerWeekDay;
	}

	public void setTriggerWeekDay(int triggerWeekDay) {
		this.triggerWeekDay = triggerWeekDay;
	}
	public int getLeaveRemainder() {
		return leaveRemainder;
	}

	public void setLeaveRemainder(int leaveRemainder) {
		this.leaveRemainder = leaveRemainder;
	}

	public String getSmsMessageBody() {
		return smsMessageBody;
	}

	public void setSmsMessageBody(String smsMessageBody) {
		this.smsMessageBody = smsMessageBody;
	}

	public boolean isSendTrackingLink() {
		return sendTrackingLink;
	}

	public void setSendTrackingLink(boolean sendTrackingLink) {
		this.sendTrackingLink = sendTrackingLink;
	}

	public String getWorkInvitationCriteria() {
		return workInvitationCriteria;
	}

	public void setWorkInvitationCriteria(String workInvitationCriteria) {
		this.workInvitationCriteria = workInvitationCriteria;
	}

	public String getWorkInvitationWorkSpecId() {
		return workInvitationWorkSpecId;
	}

	public void setWorkInvitationWorkSpecId(String workInvitationWorkSpecId) {
		this.workInvitationWorkSpecId = workInvitationWorkSpecId;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public boolean isDeliverByWhatsApp() {
		return deliverByWhatsApp;
	}

	public void setDeliverByWhatsApp(boolean deliverByWhatsApp) {
		this.deliverByWhatsApp = deliverByWhatsApp;
	}

	public String getWhatsAppMessageBody() {
		return whatsAppMessageBody;
	}

	public void setWhatsAppMessageBody(String whatsAppMessageBody) {
		this.whatsAppMessageBody = whatsAppMessageBody;
	}

	public boolean isWhatsAppToImmediateManager() {
		return whatsAppToImmediateManager;
	}

	public void setWhatsAppToImmediateManager(boolean whatsAppToImmediateManager) {
		this.whatsAppToImmediateManager = whatsAppToImmediateManager;
	}

	public boolean isWhatsAppToHierarchy() {
		return whatsAppToHierarchy;
	}

	public void setWhatsAppToHierarchy(boolean whatsAppToHierarchy) {
		this.whatsAppToHierarchy = whatsAppToHierarchy;
	}

	public String getWhatsAppNumbersOpp() {
		return whatsAppNumbersOpp;
	}

	public void setWhatsAppNumbersOpp(String whatsAppNumbersOpp) {
		this.whatsAppNumbersOpp = whatsAppNumbersOpp;
	}

	public String getWhatsAppNumbers() {
		return whatsAppNumbers;
	}

	public void setWhatsAppNumbers(String whatsAppNumbers) {
		this.whatsAppNumbers = whatsAppNumbers;
	}
	
	public boolean isSendEmployeeTrackingLink() {
		return sendEmployeeTrackingLink;
	}

	public void setSendEmployeeTrackingLink(boolean sendEmployeeTrackingLink) {
		this.sendEmployeeTrackingLink = sendEmployeeTrackingLink;
	}

	public String getEmpGroupIds() {
		return empGroupIds;
	}

	public void setEmpGroupIds(String empGroupIds) {
		this.empGroupIds = empGroupIds;
	}

	public int getFormCriteria() {
		return formCriteria;
	}

	public void setFormCriteria(int formCriteria) {
		this.formCriteria = formCriteria;
	}

	public String getTimeBasedFormSpecUniqueId() {
		return timeBasedFormSpecUniqueId;
	}

	public void setTimeBasedFormSpecUniqueId(String timeBasedFormSpecUniqueId) {
		this.timeBasedFormSpecUniqueId = timeBasedFormSpecUniqueId;
	}

	public String getTimeBasedFormFieldSpecUniqueId() {
		return timeBasedFormFieldSpecUniqueId;
	}

	public void setTimeBasedFormFieldSpecUniqueId(String timeBasedFormFieldSpecUniqueId) {
		this.timeBasedFormFieldSpecUniqueId = timeBasedFormFieldSpecUniqueId;
	}

	public String getTriggerTime() {
		return triggerTime;
	}

	public void setTriggerTime(String triggerTime) {
		this.triggerTime = triggerTime;
	}

	public String getTimeBasedFormExtraRule() {
		return timeBasedFormExtraRule;
	}

	public void setTimeBasedFormExtraRule(String timeBasedFormExtraRule) {
		this.timeBasedFormExtraRule = timeBasedFormExtraRule;
	}

	public String getTimeBasedFormSpecUniqueIdError() {
		return timeBasedFormSpecUniqueIdError;
	}

	public void setTimeBasedFormSpecUniqueIdError(String timeBasedFormSpecUniqueIdError) {
		this.timeBasedFormSpecUniqueIdError = timeBasedFormSpecUniqueIdError;
	}

	public String getTimeBasedFormFieldSpecIdError() {
		return timeBasedFormFieldSpecIdError;
	}

	public void setTimeBasedFormFieldSpecIdError(String timeBasedFormFieldSpecIdError) {
		this.timeBasedFormFieldSpecIdError = timeBasedFormFieldSpecIdError;
	}

	public String getTriggerTimeError() {
		return triggerTimeError;
	}

	public void setTriggerTimeError(String triggerTimeError) {
		this.triggerTimeError = triggerTimeError;
	}

	public Integer getExpiryDuration() {
		return expiryDuration;
	}

	public void setExpiryDuration(Integer expiryDuration) {
		this.expiryDuration = expiryDuration;
	}
	public boolean isEnableSignInRemainderNotification() {
		return enableSignInRemainderNotification;
	}

	public void setEnableSignInRemainderNotification(boolean enableSignInRemainderNotification) {
		this.enableSignInRemainderNotification = enableSignInRemainderNotification;
	}

	public long getSignInRemainderMaxCount() {
		return signInRemainderMaxCount;
	}

	public void setSignInRemainderMaxCount(long signInRemainderMaxCount) {
		this.signInRemainderMaxCount = signInRemainderMaxCount;
	}
	public String getSectionFieldsTabularFormatList() {
		return sectionFieldsTabularFormatList;
	}

	public void setSectionFieldsTabularFormatList(String sectionFieldsTabularFormatList) {
		this.sectionFieldsTabularFormatList = sectionFieldsTabularFormatList;
	}
	public boolean isEnableEventBasedRemainderNotification() {
		return enableEventBasedRemainderNotification;
	}

	public void setEnableEventBasedRemainderNotification(boolean enableEventBasedRemainderNotification) {
		this.enableEventBasedRemainderNotification = enableEventBasedRemainderNotification;
	}

	public int getWorkPdfType() {
		return workPdfType;
	}

	public void setWorkPdfType(int workPdfType) {
		this.workPdfType = workPdfType;
	}

	public Long getEventBasedBufferTime() {
		return eventBasedBufferTime;
	}

	public void setEventBasedBufferTime(Long eventBasedBufferTime) {
		this.eventBasedBufferTime = eventBasedBufferTime;
	}

	public Long getEventBasedRemainderMaxCount() {
		return eventBasedRemainderMaxCount;
	}

	public void setEventBasedRemainderMaxCount(Long eventBasedRemainderMaxCount) {
		this.eventBasedRemainderMaxCount = eventBasedRemainderMaxCount;
	}

	public String getDayPlanCriteria() {
		return dayPlanCriteria;
	}

	public void setDayPlanCriteria(String dayPlanCriteria) {
		this.dayPlanCriteria = dayPlanCriteria;
	}

	public boolean isSendApprovalStatusNotification() {
		return sendApprovalStatusNotification;
	}

	public void setSendApprovalStatusNotification(boolean sendApprovalStatusNotification) {
		this.sendApprovalStatusNotification = sendApprovalStatusNotification;
	}

	public boolean isSendSmsApprovalStatusNotification() {
		return sendSmsApprovalStatusNotification;
	}

	public void setSendSmsApprovalStatusNotification(boolean sendSmsApprovalStatusNotification) {
		this.sendSmsApprovalStatusNotification = sendSmsApprovalStatusNotification;
	}
	public String getActivityFormUniqueId() {
		return activityFormUniqueId;
	}

	public void setActivityFormUniqueId(String activityFormUniqueId) {
		this.activityFormUniqueId = activityFormUniqueId;
	}
	public String getCheckinCheckOut() {
		return checkinCheckOut;
	}

	public void setCheckinCheckOut(String checkinCheckOut) {
		this.checkinCheckOut = checkinCheckOut;
	}

	public Long getCustomEntitySpecIdForCheckIn() {
		return customEntitySpecIdForCheckIn;
	}

	public void setCustomEntitySpecIdForCheckIn(Long customEntitySpecIdForCheckIn) {
		this.customEntitySpecIdForCheckIn = customEntitySpecIdForCheckIn;
	}

	public String getCustomEntityCheckInCheckOutEvent() {
		return customEntityCheckInCheckOutEvent;
	}

	public void setCustomEntityCheckInCheckOutEvent(String customEntityCheckInCheckOutEvent) {
		this.customEntityCheckInCheckOutEvent = customEntityCheckInCheckOutEvent;
	}

	public String getCustomEntityActivityFormUniqueId() {
		return customEntityActivityFormUniqueId;
	}

	public void setCustomEntityActivityFormUniqueId(String customEntityActivityFormUniqueId) {
		this.customEntityActivityFormUniqueId = customEntityActivityFormUniqueId;
	}

	public String getCustomEntityFormFieldSpecId() {
		return customEntityFormFieldSpecId;
	}

	public void setCustomEntityFormFieldSpecId(String customEntityFormFieldSpecId) {
		this.customEntityFormFieldSpecId = customEntityFormFieldSpecId;
	}

	public Long getCustomEntitySpecIdForTimeBased() {
		return customEntitySpecIdForTimeBased;
	}

	public void setCustomEntitySpecIdForTimeBased(Long customEntitySpecIdForTimeBased) {
		this.customEntitySpecIdForTimeBased = customEntitySpecIdForTimeBased;
	}

	public String getCustomEntityFormSpecUniqueId() {
		return customEntityFormSpecUniqueId;
	}

	public void setCustomEntityFormSpecUniqueId(String customEntityFormSpecUniqueId) {
		this.customEntityFormSpecUniqueId = customEntityFormSpecUniqueId;
	}

	public Integer getTimeBasedTriggerType() {
		return timeBasedTriggerType;
	}

	public void setTimeBasedTriggerType(Integer timeBasedTriggerType) {
		this.timeBasedTriggerType = timeBasedTriggerType;
	}

	public Integer getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}


	public Boolean getTimeBasedRemainderNotification() {
		return timeBasedRemainderNotification;
	}

	public void setTimeBasedRemainderNotification(Boolean timeBasedRemainderNotification) {
		this.timeBasedRemainderNotification = timeBasedRemainderNotification;
	}

	public Integer getTimeBasedRemainderMaxCount() {
		return timeBasedRemainderMaxCount;
	}

	public void setTimeBasedRemainderMaxCount(Integer timeBasedRemainderMaxCount) {
		this.timeBasedRemainderMaxCount = timeBasedRemainderMaxCount;
	}

	public Integer getTimeBasedBufferTime() {
		return timeBasedBufferTime;
	}

	public void setTimeBasedBufferTime(Integer timeBasedBufferTime) {
		this.timeBasedBufferTime = timeBasedBufferTime;
	}

	public String getCustomEntityFormFieldSpecIdError() {
		return customEntityFormFieldSpecIdError;
	}

	public void setCustomEntityFormFieldSpecIdError(String customEntityFormFieldSpecIdError) {
		this.customEntityFormFieldSpecIdError = customEntityFormFieldSpecIdError;
	}

	public String getNumberOfDaysError() {
		return numberOfDaysError;
	}

	public void setNumberOfDaysError(String numberOfDaysError) {
		this.numberOfDaysError = numberOfDaysError;
	}

	public String getTimeBasedRemainderMaxCountError() {
		return timeBasedRemainderMaxCountError;
	}

	public void setTimeBasedRemainderMaxCountError(String timeBasedRemainderMaxCountError) {
		this.timeBasedRemainderMaxCountError = timeBasedRemainderMaxCountError;
	}

	public String getTimeBasedBufferTimeError() {
		return timeBasedBufferTimeError;
	}

	public void setTimeBasedBufferTimeError(String timeBasedBufferTimeError) {
		this.timeBasedBufferTimeError = timeBasedBufferTimeError;
	}
	

	
	
	
	public String getPhoneNumbersForWhatsApp() {
		return phoneNumbersForWhatsApp;
	}

	public void setPhoneNumbersForWhatsApp(String phoneNumbersForWhatsApp) {
		this.phoneNumbersForWhatsApp = phoneNumbersForWhatsApp;
	}

	public String getDeliverToWhatsApp() {
		return deliverToWhatsApp;
	}

	public void setDeliverToWhatsApp(String deliverToWhatsApp) {
		this.deliverToWhatsApp = deliverToWhatsApp;
	}

	public boolean isSendWhatsAppApprovalStatusNotification() {
		return sendWhatsAppApprovalStatusNotification;
	}

	public void setSendWhatsAppApprovalStatusNotification(boolean sendWhatsAppApprovalStatusNotification) {
		this.sendWhatsAppApprovalStatusNotification = sendWhatsAppApprovalStatusNotification;
	}
	
}

