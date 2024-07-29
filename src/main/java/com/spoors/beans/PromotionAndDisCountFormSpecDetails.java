package com.spoors.beans;

import java.io.Serializable;
import java.util.List;

public class PromotionAndDisCountFormSpecDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final String mobileTableName = "promotions_and_discounts_formSpec_details";

	private Long promotionAndDisCountFormSpecDetailId;
	private Long companyId;
	private String title;
	private String promotionAndDisCountFormSpecUniqueId;
	private Long promotionAndDisCountFormSpecType;
	private Long createdBy;
	private Long modifiedBy;
	private String createdTime;
	private String modifiedTime;
	private Boolean deleted;
	private String formSpecTitle;
	


	private List<PromotionAndDisCountFormSpecMappingDetails> promotionAndDisCountFormSpecMappingDetails;

	

	public Long getPromotionAndDisCountFormSpecDetailId() {
		return promotionAndDisCountFormSpecDetailId;
	}



	public void setPromotionAndDisCountFormSpecDetailId(Long promotionAndDisCountFormSpecDetailId) {
		this.promotionAndDisCountFormSpecDetailId = promotionAndDisCountFormSpecDetailId;
	}



	public Long getCompanyId() {
		return companyId;
	}



	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getPromotionAndDisCountFormSpecUniqueId() {
		return promotionAndDisCountFormSpecUniqueId;
	}



	public void setPromotionAndDisCountFormSpecUniqueId(String promotionAndDisCountFormSpecUniqueId) {
		this.promotionAndDisCountFormSpecUniqueId = promotionAndDisCountFormSpecUniqueId;
	}



	public Long getPromotionAndDisCountFormSpecType() {
		return promotionAndDisCountFormSpecType;
	}



	public void setPromotionAndDisCountFormSpecType(Long promotionAndDisCountFormSpecType) {
		this.promotionAndDisCountFormSpecType = promotionAndDisCountFormSpecType;
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



	public String getModifiedTime() {
		return modifiedTime;
	}



	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}



	public Boolean getDeleted() {
		return deleted;
	}



	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}



	public List<PromotionAndDisCountFormSpecMappingDetails> getPromotionAndDisCountFormSpecMappingDetails() {
		return promotionAndDisCountFormSpecMappingDetails;
	}



	public void setPromotionAndDisCountFormSpecMappingDetails(
			List<PromotionAndDisCountFormSpecMappingDetails> promotionAndDisCountFormSpecMappingDetails) {
		this.promotionAndDisCountFormSpecMappingDetails = promotionAndDisCountFormSpecMappingDetails;
	}
   
	public String getFormSpecTitle() {
		return formSpecTitle;
	}



	public void setFormSpecTitle(String formSpecTitle) {
		this.formSpecTitle = formSpecTitle;
	}



	@Override
	public String toString() {
		return "PromotionAndDisCountFormSpecDetails [promotionAndDisCountFormSpecDetailId="
				+ promotionAndDisCountFormSpecDetailId + ", companyId=" + companyId + ", title=" + title
				+ ", deleted=" + deleted + ", promotionAndDisCountFormSpecUniqueId="
				+ promotionAndDisCountFormSpecUniqueId + ", promotionAndDisCountFormSpecType="
				+ promotionAndDisCountFormSpecType + ", promotionAndDisCountFormSpecMappingDetails=" + promotionAndDisCountFormSpecMappingDetails
				+ "]";
	}
	
	
}
