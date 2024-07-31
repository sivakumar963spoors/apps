package com.spoors.beans.workSpecs;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NotificationSpec {
	
  public static final int EMAIL = 1;
  public static final int SMS = 2;
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
  private String deliverToSms;
  private String deliverToEmail;
  private String deliverToSync;
  private String rule;
  private int ruleType;
  private String extraRule;
  private long createdBy;
  private boolean deleted;
  private int qualityType;
  private boolean pdfAttachment;
  private boolean mediaAttachment;
  private Long matrixId;
  private String notificationSpecRuleJson;
  private String extraWorkRule;
  private boolean canTriggerTwoMails;
  
  private String notificationSpecFormsExtraRuleJson;
  private String formsExtraRule;
	
  private boolean toImmediateManager;
  private boolean toHierarchy;
	
  private boolean smsToImmediateManager;
  private boolean smsToHierarchy;
	
  private boolean syncToImmediateManager;
  private boolean syncToHierarchy;
  
  private String extraMailBody;
  
  public static final int NOTIFICATION_TYPE_SIGN_IN = 1;
  public static final int NOTIFICATION_TYPE_SIGN_OUT = 2;
  public static final String NOTIFICATION_BEFORE_OR_AFTER = "1";
  private boolean approveOrRejectInMail;
  
  private String smsMessageBody;
  private boolean sendTrackingLink;

  private boolean deliverByWhatsApp;
  private String deliverToWhatsApp;
  private String whatsAppMessageBody;
  private boolean whatsAppToImmediateManager;
  private boolean whatsAppToHierarchy;
  private boolean sendEmployeeTrackingLink;
  private Long workId;
  private String notificationType;
  private boolean notificationView;
  private Integer expiryDuration;
  private boolean deliveryByPushNotification;
  private String deliverToPushNotification;
  private boolean pushNotificationToImmediateManager;
  private boolean enableSignInRemainderNotification;
  private long signInRemainderMaxCount;
  private Long eventBasedBufferTime;
  private boolean enableEventBasedRemainderNotification;
  private Long eventBasedRemainderMaxCount;
  private int workPdfType;
  private boolean sendApprovalStatusNotification;
  private boolean sendSmsApprovalStatusNotification;
  private boolean sendWhatsAppApprovalStatusNotification;
  private String deliverToWhatsapp;


  public NotificationSpec() {}

  public NotificationSpec
  (long id, long companyId, long empId, int type,int ruleType, String name, 
		  String messageSubject, String messageBody, boolean deliverBySms, boolean deliverByMail, 
		  boolean deliverBySync, String deliverToSms, String deliverToEmail,String deliverToSync, String rule, String extraRule, 
		  long createdBy,int qualityType,boolean pdfAttachment,boolean mediaAttachment,Long matrixId,
		  String notificationSpecRuleJson,String extraWorkRule,boolean canTriggerTwoMails,
		  String notificationSpecFormsExtraRuleJson,String formsExtraRule, 
		  boolean toImmediateManager, boolean toHierarchy , boolean smsToImmediateManager, boolean smsToHierarchy, 
		  boolean syncToImmediateManager, boolean syncToHierarchy,String smsMessageBody,boolean sendTrackingLink,boolean deliverByWhatsApp,
		  String deliverToWhatsApp,String whatsAppMessageBody,boolean whatsAppToImmediateManager,boolean whatsAppToHierarchy,boolean sendEmployeeTrackingLink,
		  Integer expiryDuration,boolean enableSignInRemainderNotification,long signInRemainderMaxCount,Long eventBasedBufferTime,boolean enableEventBasedRemainderNotification,
		  Long eventBasedRemainderMaxCount,int workPdfType,boolean sendApprovalStatusNotification,boolean sendSmsApprovalStatusNotification,String deliverToWhatsapp,boolean sendWhatsAppApprovalStatusNotification) {
    this.id = id;
    this.companyId = companyId;
    this.empId = empId;
    this.type = type;
    this.name = name;
    this.messageSubject = messageSubject;
    this.messageBody = messageBody;
    this.deliverBySms = deliverBySms;
    this.deliverByMail = deliverByMail;
    this.deliverBySync = deliverBySync;
    this.deliverToSms = deliverToSms;
    this.deliverToEmail = deliverToEmail;
    this.deliverToSync = deliverToSync;
    this.rule = rule;
    this.extraRule = extraRule;
    this.createdBy = createdBy;
    this.ruleType=ruleType;
    this.qualityType=qualityType;
    this.pdfAttachment=pdfAttachment;
    this.mediaAttachment=mediaAttachment;
    this.matrixId=matrixId;
    this.notificationSpecRuleJson = notificationSpecRuleJson;
    this.extraWorkRule = extraWorkRule;
    this.canTriggerTwoMails=canTriggerTwoMails;
    this.formsExtraRule =formsExtraRule;
    this.notificationSpecFormsExtraRuleJson=notificationSpecFormsExtraRuleJson;
    this.toImmediateManager=toImmediateManager;
    this.toHierarchy=toHierarchy;
    this.smsToImmediateManager = smsToImmediateManager;
    this.smsToHierarchy = smsToHierarchy;
    this.syncToImmediateManager = syncToImmediateManager;
    this.syncToHierarchy = syncToHierarchy;
    this.smsMessageBody =smsMessageBody;
    this.sendTrackingLink=sendTrackingLink;
    this.deliverByWhatsApp=deliverByWhatsApp;
    this.deliverToWhatsApp=deliverToWhatsApp;
    this.whatsAppMessageBody=whatsAppMessageBody;
    this.whatsAppToImmediateManager=whatsAppToImmediateManager;
    this.whatsAppToHierarchy=whatsAppToHierarchy;
    this.sendEmployeeTrackingLink=sendEmployeeTrackingLink;
    this.expiryDuration = expiryDuration;
    this.enableSignInRemainderNotification = enableSignInRemainderNotification;
    this.signInRemainderMaxCount = signInRemainderMaxCount;
    this.eventBasedBufferTime = eventBasedBufferTime;
    this.enableEventBasedRemainderNotification = enableEventBasedRemainderNotification;
    this.eventBasedRemainderMaxCount = eventBasedRemainderMaxCount;
    this.workPdfType = workPdfType;
    this.sendApprovalStatusNotification = sendApprovalStatusNotification;
    this.sendSmsApprovalStatusNotification = sendSmsApprovalStatusNotification;
    this.sendWhatsAppApprovalStatusNotification = sendWhatsAppApprovalStatusNotification;
    this.deliverToWhatsapp = deliverToWhatsapp;
  }
  
  

	public NotificationSpec(NotificationSpec selectedNotification)
	{
		this.id = selectedNotification.getId();
	    this.companyId = selectedNotification.getCompanyId();
	    this.empId = selectedNotification.getEmpId();
	    this.type = selectedNotification.getType();
	    this.ruleType=selectedNotification.getRuleType();
	    this.name = selectedNotification.getName();
	    this.messageSubject = selectedNotification.getMessageSubject();
	    this.messageBody = selectedNotification.getMessageBody();
	    this.deliverBySms = selectedNotification.isDeliverBySms();
	    this.deliverByMail = selectedNotification.isDeliverByMail();
	    this.deliverBySync = selectedNotification.isDeliverBySync();
	    this.deliverToSms = selectedNotification.getDeliverToSms();
	    this.deliverToEmail = selectedNotification.getDeliverToEmail();
	    this.deliverToSync = selectedNotification.getDeliverToSync();
	    this.rule = selectedNotification.getRule();
	    this.extraRule = selectedNotification.getExtraRule();
	    this.createdBy = selectedNotification.getCreatedBy();
	    this.qualityType=selectedNotification.getQualityType();
	    this.pdfAttachment=selectedNotification.isPdfAttachment();
	    this.mediaAttachment=selectedNotification.isMediaAttachment();
	    this.matrixId=selectedNotification.getMatrixId();
	    this.notificationSpecRuleJson = selectedNotification.getNotificationSpecRuleJson();
	    this.extraWorkRule = selectedNotification.getExtraWorkRule();
	    this.canTriggerTwoMails=selectedNotification.isCanTriggerTwoMails();
	    this.formsExtraRule =selectedNotification.getFormsExtraRule();
	    this.notificationSpecFormsExtraRuleJson=selectedNotification.getNotificationSpecFormsExtraRuleJson();
	    this.toImmediateManager=selectedNotification.isToImmediateManager();
	    this.toHierarchy=selectedNotification.isToHierarchy();
	    this.smsToImmediateManager = selectedNotification.isSmsToImmediateManager();
	    this.smsToHierarchy = selectedNotification.isSmsToHierarchy();
	    this.syncToImmediateManager = selectedNotification.isSmsToImmediateManager();
	    this.syncToHierarchy = selectedNotification.isSmsToHierarchy();
	    this.smsMessageBody = selectedNotification.getSmsMessageBody();
	    this.sendTrackingLink=selectedNotification.isSendTrackingLink();
	    this.deliverByWhatsApp=selectedNotification.isDeliverByWhatsApp();
	    this.deliverToWhatsApp=selectedNotification.getDeliverToWhatsApp();
	    this.whatsAppMessageBody=selectedNotification.getWhatsAppMessageBody();
	    this.whatsAppToImmediateManager=selectedNotification.isWhatsAppToImmediateManager();
	    this.whatsAppToHierarchy=selectedNotification.isWhatsAppToHierarchy();
	    this.sendEmployeeTrackingLink=selectedNotification.isSendEmployeeTrackingLink();
	    this.enableSignInRemainderNotification =selectedNotification.isEnableSignInRemainderNotification();
	    this.signInRemainderMaxCount=selectedNotification.getSignInRemainderMaxCount();
	    this.eventBasedBufferTime = selectedNotification.getEventBasedBufferTime();
	    this.enableEventBasedRemainderNotification = selectedNotification.isEnableEventBasedRemainderNotification();
	    this.eventBasedRemainderMaxCount = selectedNotification.getEventBasedRemainderMaxCount();
	    this.workPdfType = selectedNotification.getWorkPdfType();
	    this.sendApprovalStatusNotification = selectedNotification.isSendApprovalStatusNotification();
	    this.sendSmsApprovalStatusNotification= selectedNotification.isSendSmsApprovalStatusNotification();
	    this.deliverToWhatsapp = selectedNotification.getDeliverToWhatsapp();
	    this.sendWhatsAppApprovalStatusNotification= selectedNotification.isSendWhatsAppApprovalStatusNotification();
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

	public int getQualityType() {
		return qualityType;
	}

	public void setQualityType(int qualityType) {
		this.qualityType = qualityType;
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

  public String getDeliverToSms() {
    return deliverToSms;
  }

  public void setDeliverToSms(String deliverToSms) {
    this.deliverToSms = deliverToSms;
  }

  public String getDeliverToEmail() {
    return deliverToEmail;
  }

  public void setDeliverToEmail(String deliverToEmail) {
    this.deliverToEmail = deliverToEmail;
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

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public int getRuleType() {
    return ruleType;
  }

  public void setRuleType(int ruleType) {
    this.ruleType = ruleType;
  }
@JsonIgnore
public Long getMatrixId() {
	return matrixId;
}
@JsonIgnore
public void setMatrixId(Long matrixId) {
	this.matrixId = matrixId;
}

@JsonIgnore
public String getNotificationSpecRuleJson() {
	return notificationSpecRuleJson;
}

@JsonIgnore
public void setNotificationSpecRuleJson(String notificationSpecRuleJson) {
	this.notificationSpecRuleJson = notificationSpecRuleJson;
}
@JsonIgnore
public String getExtraWorkRule() {
	return extraWorkRule;
}
@JsonIgnore
public void setExtraWorkRule(String extraWorkRule) {
	this.extraWorkRule = extraWorkRule;
}

public boolean isCanTriggerTwoMails() {
	return canTriggerTwoMails;
}

public void setCanTriggerTwoMails(boolean canTriggerTwoMails) {
	this.canTriggerTwoMails = canTriggerTwoMails;
}

@JsonIgnore
public String getNotificationSpecFormsExtraRuleJson() {
	return notificationSpecFormsExtraRuleJson;
}

@JsonIgnore
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

public String getDeliverToSync() {
	return deliverToSync;
}

public void setDeliverToSync(String deliverToSync) {
	this.deliverToSync = deliverToSync;
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

@JsonIgnore
public String getExtraMailBody() {
	if(extraMailBody == null) {
		extraMailBody = "";
	}
	return extraMailBody;
}
@JsonIgnore
public void setExtraMailBody(String extraMailBody) {
	if(extraMailBody == null) {
		extraMailBody = "";
	}
	this.extraMailBody = extraMailBody;
}

public boolean isApproveOrRejectInMail() {
	return approveOrRejectInMail;
}

public void setApproveOrRejectInMail(boolean approveOrRejectInMail) {
	this.approveOrRejectInMail = approveOrRejectInMail;
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

public boolean isDeliverByWhatsApp() {
	return deliverByWhatsApp;
}

public void setDeliverByWhatsApp(boolean deliverByWhatsApp) {
	this.deliverByWhatsApp = deliverByWhatsApp;
}

public String getDeliverToWhatsApp() {
	return deliverToWhatsApp;
}

public void setDeliverToWhatsApp(String deliverToWhatsApp) {
	this.deliverToWhatsApp = deliverToWhatsApp;
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

public boolean isSendEmployeeTrackingLink() {
	return sendEmployeeTrackingLink;
}

public void setSendEmployeeTrackingLink(boolean sendEmployeeTrackingLink) {
	this.sendEmployeeTrackingLink = sendEmployeeTrackingLink;
}

public Long getWorkId() {
	return workId;
}

public void setWorkId(Long workId) {
	this.workId = workId;
}

public String getNotificationType() {
	return notificationType;
}

public void setNotificationType(String notificationType) {
	this.notificationType = notificationType;
}

public boolean isNotificationView() {
	return notificationView;
}

public void setNotificationView(boolean notificationView) {
	this.notificationView = notificationView;
}

public Integer getExpiryDuration() {
	return expiryDuration;
}

public void setExpiryDuration(Integer expiryDuration) {
	this.expiryDuration = expiryDuration;
}

public boolean isDeliveryByPushNotification() {
	return deliveryByPushNotification;
}

public void setDeliveryByPushNotification(boolean deliveryByPushNotification) {
	this.deliveryByPushNotification = deliveryByPushNotification;
}

public String getDeliverToPushNotification() {
	return deliverToPushNotification;
}

public void setDeliverToPushNotification(String deliverToPushNotification) {
	this.deliverToPushNotification = deliverToPushNotification;
}

public boolean isPushNotificationToImmediateManager() {
	return pushNotificationToImmediateManager;
}

public void setPushNotificationToImmediateManager(boolean pushNotificationToImmediateManager) {
	this.pushNotificationToImmediateManager = pushNotificationToImmediateManager;
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

public String getDeliverToWhatsapp() {
	return deliverToWhatsapp;
}

public void setDeliverToWhatsapp(String deliverToWhatsapp) {
	this.deliverToWhatsapp = deliverToWhatsapp;
}

public boolean isSendWhatsAppApprovalStatusNotification() {
	return sendWhatsAppApprovalStatusNotification;
}

public void setSendWhatsAppApprovalStatusNotification(boolean sendWhatsAppApprovalStatusNotification) {
	this.sendWhatsAppApprovalStatusNotification = sendWhatsAppApprovalStatusNotification;
}



}
