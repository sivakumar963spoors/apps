package com.spoors.beans;

public class PromotionAndDisCountFormSpecMappingDetails{
	
	public static final String mobileTableName = "promotions_and_discounts_formSpec_mapping_details";

	private Long id;
	private Long promotionAndDisCountFormSpecDetailId;
	private String promotionAndDisCountFormFieldSpecId;
	private Integer promotionAndDisCountFormFieldSpecType;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPromotionAndDisCountFormSpecDetailId() {
		return promotionAndDisCountFormSpecDetailId;
	}
	public void setPromotionAndDisCountFormSpecDetailId(Long promotionAndDisCountFormSpecDetailId) {
		this.promotionAndDisCountFormSpecDetailId = promotionAndDisCountFormSpecDetailId;
	}
	public String getPromotionAndDisCountFormFieldSpecId() {
		return promotionAndDisCountFormFieldSpecId;
	}
	public void setPromotionAndDisCountFormFieldSpecId(String promotionAndDisCountFormFieldSpecId) {
		this.promotionAndDisCountFormFieldSpecId = promotionAndDisCountFormFieldSpecId;
	}
	public Integer getPromotionAndDisCountFormFieldSpecType() {
		return promotionAndDisCountFormFieldSpecType;
	}
	public void setPromotionAndDisCountFormFieldSpecType(Integer promotionAndDisCountFormFieldSpecType) {
		this.promotionAndDisCountFormFieldSpecType = promotionAndDisCountFormFieldSpecType;
	}
	@Override
	public String toString() {
		return "PromotionAndDisCountFormSpecMappingDetails [id=" + id + ", promotionAndDisCountFormSpecDetailId="
				+ promotionAndDisCountFormSpecDetailId + ", promotionAndDisCountFormFieldSpecId="
				+ promotionAndDisCountFormFieldSpecId + ", promotionAndDisCountFormFieldSpecType="
				+ promotionAndDisCountFormFieldSpecType + "]";
	}
	
	
	
	
}
