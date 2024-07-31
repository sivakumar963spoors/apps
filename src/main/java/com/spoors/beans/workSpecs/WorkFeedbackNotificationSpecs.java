package com.spoors.beans.workSpecs;

public class WorkFeedbackNotificationSpecs {

	 public static final int EMAIL = 1;
	  public static final int SMS = 2;
	  private long id;
	  private long companyId;
	  private long workSpecId;
	  private long empId;
	  private int type;
	
	private String name;
	  private String messageSubject;
	  private String messageBody;
	  private String smsMessageBody;
	  private boolean deliverBySms;
	  private boolean deliverByMail;
	  private String deliverToSms;
	  private String deliverToEmail;
	  private long createdBy;
	  private long modifiedBy;
	  private boolean deleted;
	  private String createdDate;
	  private String modifiedDate;
	  private boolean pdfAttachment;
	  private boolean mediaAttachment;
	  private Integer remainder;
	  private Integer remainderFrequency;
	  private Integer remainderMaxCount;
	  private Integer remainderFrequencyType;
	  private String phoneNumbersOpp;
	  private String phoneNumbers;
	  private String emailAddressOpp;
	  private String emailAddress;
	  private long workId;
	  
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
	public long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(long workSpecId) {
		this.workSpecId = workSpecId;
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
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
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
	public Integer getRemainder() {
		return remainder;
	}
	public void setRemainder(Integer remainder) {
		this.remainder = remainder;
	}

	public Integer getRemainderMaxCount() {
		return remainderMaxCount;
	}
	public void setRemainderMaxCount(Integer remainderMaxCount) {
		this.remainderMaxCount = remainderMaxCount;
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
	public String getSmsMessageBody() {
		return smsMessageBody;
	}
	public void setSmsMessageBody(String smsMessageBody) {
		this.smsMessageBody = smsMessageBody;
	}
	public long getWorkId() {
		return workId;
	}
	public void setWorkId(long workId) {
		this.workId = workId;
	}
	  public Integer getRemainderFrequency() {
			return remainderFrequency;
		}
		public void setRemainderFrequency(Integer remainderFrequency) {
			this.remainderFrequency = remainderFrequency;
		}
		public Integer getRemainderFrequencyType() {
			return remainderFrequencyType;
		}
		public void setRemainderFrequencyType(Integer remainderFrequencyType) {
			this.remainderFrequencyType = remainderFrequencyType;
		}
	
}
