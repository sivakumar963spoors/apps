package com.spoors.beans;


public class EntitySectionSpec {
	
	private long sectionSpecId;
	private long entitySpecId;
	private int companyId;
	private String sectionTitle;
	private int minEntrys;
	private int maxEntrys;
	private boolean instanceNumbering;
	private int displayOrder;
	private String expression;
	private boolean deleted;
	private Long initialEntitySectionSpecId;
	private Long skeletonEntitySectionSpecId;
	private String createdTime;
	private String modifiedTime;
	private boolean autoCreateSectionInstance;
	private String autoCreateFieldTypeExtra;
	private String autoCreateFieldTypeExtraError;
	private String groupByCategories;
	private boolean manualSelection;
	
	private Integer pageId = 1;
	
	private int rowId;

	public long getSectionSpecId() {
		return sectionSpecId;
	}

	public void setSectionSpecId(long sectionSpecId) {
		this.sectionSpecId = sectionSpecId;
	}

	public long getEntitySpecId() {
		return entitySpecId;
	}

	public void setEntitySpecId(long entitySpecId) {
		this.entitySpecId = entitySpecId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getSectionTitle() {
		return sectionTitle;
	}

	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}

	public int getMinEntrys() {
		return minEntrys;
	}

	public void setMinEntrys(int minEntrys) {
		this.minEntrys = minEntrys;
	}

	public int getMaxEntrys() {
		return maxEntrys;
	}

	public void setMaxEntrys(int maxEntrys) {
		this.maxEntrys = maxEntrys;
	}

	public boolean isInstanceNumbering() {
		return instanceNumbering;
	}

	public void setInstanceNumbering(boolean instanceNumbering) {
		this.instanceNumbering = instanceNumbering;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Long getInitialEntitySectionSpecId() {
		return initialEntitySectionSpecId;
	}

	public void setInitialEntitySectionSpecId(Long initialEntitySectionSpecId) {
		this.initialEntitySectionSpecId = initialEntitySectionSpecId;
	}

	public Long getSkeletonEntitySectionSpecId() {
		return skeletonEntitySectionSpecId;
	}

	public void setSkeletonEntitySectionSpecId(Long skeletonEntitySectionSpecId) {
		this.skeletonEntitySectionSpecId = skeletonEntitySectionSpecId;
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

	public boolean isAutoCreateSectionInstance() {
		return autoCreateSectionInstance;
	}

	public void setAutoCreateSectionInstance(boolean autoCreateSectionInstance) {
		this.autoCreateSectionInstance = autoCreateSectionInstance;
	}

	public String getAutoCreateFieldTypeExtra() {
		return autoCreateFieldTypeExtra;
	}

	public void setAutoCreateFieldTypeExtra(String autoCreateFieldTypeExtra) {
		this.autoCreateFieldTypeExtra = autoCreateFieldTypeExtra;
	}

	public String getAutoCreateFieldTypeExtraError() {
		return autoCreateFieldTypeExtraError;
	}

	public void setAutoCreateFieldTypeExtraError(String autoCreateFieldTypeExtraError) {
		this.autoCreateFieldTypeExtraError = autoCreateFieldTypeExtraError;
	}

	public String getGroupByCategories() {
		return groupByCategories;
	}

	public void setGroupByCategories(String groupByCategories) {
		this.groupByCategories = groupByCategories;
	}

	public boolean isManualSelection() {
		return manualSelection;
	}

	public void setManualSelection(boolean manualSelection) {
		this.manualSelection = manualSelection;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	
	

}
