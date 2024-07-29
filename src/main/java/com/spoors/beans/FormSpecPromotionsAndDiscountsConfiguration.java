package com.spoors.beans;

import java.io.Serializable;

public class FormSpecPromotionsAndDiscountsConfiguration implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	public static final String mobileTableName = "formSpec_promotions_and_discounts_configuration";
	
	private Long formSpecPromotionsAndDiscountConfigurationId;
	private String formSpecUniqueId;
	private boolean enableDiscount;
	private Integer discountModuleType =0;
	private String discountMaster;
	private boolean enableSupplementary;
	private Integer supplementaryModuleType =0;
	private String supplementaryMaster;
	private boolean enableUnitConversion;
	private Integer unitConversionModuleType =0;
	private Long unitConversionMaster;
	private boolean enableStockMaster;
	private Integer stockMasterModuleType = 0;
	private Long stockMaster;
	private boolean enableProductPrice;
	private Integer productPriceModuleType = 0;
	private String productPrice;
	private boolean enableProduct;
	private Integer productModuleType=0;
	private Long product;
	private boolean enablePromotion;
	private Integer promotionModuleType = 0;
	private Long promotion;
	private Long companyId;
	private Long createdBy;
	private Long modifiedBy;
	private String createdTime;
	private String modifiedTime;
	
	public Long getFormSpecPromotionsAndDiscountConfigurationId() {
		return formSpecPromotionsAndDiscountConfigurationId;
	}
	public void setFormSpecPromotionsAndDiscountConfigurationId(Long formSpecPromotionsAndDiscountConfigurationId) {
		this.formSpecPromotionsAndDiscountConfigurationId = formSpecPromotionsAndDiscountConfigurationId;
	}
	public String getFormSpecUniqueId() {
		return formSpecUniqueId;
	}
	public void setFormSpecUniqueId(String formSpecUniqueId) {
		this.formSpecUniqueId = formSpecUniqueId;
	}
	public boolean isEnableDiscount() {
		return enableDiscount;
	}
	public void setEnableDiscount(boolean enableDiscount) {
		this.enableDiscount = enableDiscount;
	}
	public Integer getDiscountModuleType() {
		return discountModuleType;
	}
	public void setDiscountModuleType(Integer discountModuleType) {
		this.discountModuleType = discountModuleType;
	}
	public String getDiscountMaster() {
		return discountMaster;
	}
	public void setDiscountMaster(String discountMaster) {
		this.discountMaster = discountMaster;
	}
	public boolean isEnableSupplementary() {
		return enableSupplementary;
	}
	public void setEnableSupplementary(boolean enableSupplementary) {
		this.enableSupplementary = enableSupplementary;
	}
	public Integer getSupplementaryModuleType() {
		return supplementaryModuleType;
	}
	public void setSupplementaryModuleType(Integer supplementaryModuleType) {
		this.supplementaryModuleType = supplementaryModuleType;
	}
	public String getSupplementaryMaster() {
		return supplementaryMaster;
	}
	public void setSupplementaryMaster(String supplementaryMaster) {
		this.supplementaryMaster = supplementaryMaster;
	}
	public boolean isEnableUnitConversion() {
		return enableUnitConversion;
	}
	public void setEnableUnitConversion(boolean enableUnitConversion) {
		this.enableUnitConversion = enableUnitConversion;
	}
	public Integer getUnitConversionModuleType() {
		return unitConversionModuleType;
	}
	public void setUnitConversionModuleType(Integer unitConversionModuleType) {
		this.unitConversionModuleType = unitConversionModuleType;
	}
	public Long getUnitConversionMaster() {
		return unitConversionMaster;
	}
	public void setUnitConversionMaster(Long unitConversionMaster) {
		this.unitConversionMaster = unitConversionMaster;
	}
	public boolean isEnableStockMaster() {
		return enableStockMaster;
	}
	public void setEnableStockMaster(boolean enableStockMaster) {
		this.enableStockMaster = enableStockMaster;
	}
	public Integer getStockMasterModuleType() {
		return stockMasterModuleType;
	}
	public void setStockMasterModuleType(Integer stockMasterModuleType) {
		this.stockMasterModuleType = stockMasterModuleType;
	}
	public Long getStockMaster() {
		return stockMaster;
	}
	public void setStockMaster(Long stockMaster) {
		this.stockMaster = stockMaster;
	}
	public boolean isEnableProductPrice() {
		return enableProductPrice;
	}
	public void setEnableProductPrice(boolean enableProductPrice) {
		this.enableProductPrice = enableProductPrice;
	}
	public Integer getProductPriceModuleType() {
		return productPriceModuleType;
	}
	public void setProductPriceModuleType(Integer productPriceModuleType) {
		this.productPriceModuleType = productPriceModuleType;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public boolean isEnableProduct() {
		return enableProduct;
	}
	public void setEnableProduct(boolean enableProduct) {
		this.enableProduct = enableProduct;
	}
	public Integer getProductModuleType() {
		return productModuleType;
	}
	public void setProductModuleType(Integer productModuleType) {
		this.productModuleType = productModuleType;
	}
	public Long getProduct() {
		return product;
	}
	public void setProduct(Long product) {
		this.product = product;
	}
	public boolean isEnablePromotion() {
		return enablePromotion;
	}
	public void setEnablePromotion(boolean enablePromotion) {
		this.enablePromotion = enablePromotion;
	}
	public Integer getPromotionModuleType() {
		return promotionModuleType;
	}
	public void setPromotionModuleType(Integer promotionModuleType) {
		this.promotionModuleType = promotionModuleType;
	}
	public Long getPromotion() {
		return promotion;
	}
	public void setPromotion(Long promotion) {
		this.promotion = promotion;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getMobiletablename() {
		return mobileTableName;
	}
	
	
}
