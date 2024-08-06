package com.spoors.beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class EntitySpec implements Serializable{
	
	private static final long serialVersionUID = 2741284144725394073L;
	
	private long entitySpecId;
	private int companyId;
	private String entityTitle;
	private boolean syncAll;
	private long createdBy;
	@JsonProperty(access = Access.READ_ONLY)
	private boolean isPublic;
	private Long parentId;
	private boolean deleted;
	private int skeletonCompanyId;
	
	private int type;
	
	private boolean customerPresent;
	private int isSystemDefined;
	private Long skeletonEntitySpecId;
	private int purpose;
	private String externalId;
	
	public static int visibility_Active=1;
	public static int visibility_DeActive=0;
	
	public static int ENTITY_EMPLOYEE_SKILLS=1;
	
	private String mobileLayout;
	private boolean removeBlankLines;
	private boolean sendItemsIndependentOnCustomerField;
	private boolean blockedByOps;
	//for entity Configuration in Employee 
	private Boolean visible=false;
	//for dashboardConfigurationItems for Configuration
	public List<EntitySpec> entitySpecItems;
	
	private Integer syncScope;

	private String createdTime;
	private String modifiedTime;
	private String listItemsModifiedTime;
	private String listItemsCount;
	private String createdByName;
	
	private String permissionTypeCsv;
	
	private boolean viewItems;
	private boolean addItems;
	private boolean modifyItems;
	private boolean deleteItems;
	
	private int entityEmpMappingType;
	
	private int addedEntity;
	private int modifiedEntity;
	private int deletedEntity;
	private boolean visibleListNoInEntitySelectionPicker;
	private boolean enableGlobalSearch;
	private boolean exactMatch;
	
	private Long filteredEntitySpecId;
	private Long filteredEntityFieldSpecId;

	public static int BASIC_MAPPING = 1;
	public static int CONFIGURATION_BASED_MAPPING = 2;
	private boolean simpleSearchableListNo;
	private boolean advancedSearchableListNo;
	private int ignoreMappingWhenSyncOffEnabled;
	
	public boolean isViewItems() {
		return viewItems;
	}
	public void setViewItems(boolean viewItems) {
		this.viewItems = viewItems;
	}
	public boolean isAddItems() {
		return addItems;
	}
	public void setAddItems(boolean addItems) {
		this.addItems = addItems;
	}
	public boolean isModifyItems() {
		return modifyItems;
	}
	public void setModifyItems(boolean modifyItems) {
		this.modifyItems = modifyItems;
	}
	public boolean isDeleteItems() {
		return deleteItems;
	}
	public void setDeleteItems(boolean deleteItems) {
		this.deleteItems = deleteItems;
	}
	
	public boolean isSendItemsIndependentOnCustomerField() {
		return sendItemsIndependentOnCustomerField;
	}
	public void setSendItemsIndependentOnCustomerField(boolean sendItemsIndependentOnCustomerField) {
		this.sendItemsIndependentOnCustomerField = sendItemsIndependentOnCustomerField;
	}
	public String getMobileLayout() {
		return mobileLayout;
	}
	public void setMobileLayout(String mobileLayout) {
		this.mobileLayout = mobileLayout;
	}
	
	public boolean isRemoveBlankLines() {
		return removeBlankLines;
	}
	public void setRemoveBlankLines(boolean removeBlankLines) {
		this.removeBlankLines = removeBlankLines;
	}
	public int getPurpose() {
		return purpose;
	}
	public void setPurpose(int purpose) {
		this.purpose = purpose;
	}
	public long getEntitySpecId() {
		return entitySpecId;
	}
	public void setEntitySpecId(long entitySpecId) {
		this.entitySpecId = entitySpecId;
	}
	public String getEntityTitle() {
		return entityTitle;
	}
	public void setEntityTitle(String entityTitle) {
		this.entityTitle = entityTitle;
	}
	public boolean isSyncAll() {
		return syncAll;
	}
	public void setSyncAll(boolean syncAll) {
		this.syncAll = syncAll;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
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
	
	@JsonIgnore
	public int getCompanyId() {
		return companyId;
	}
	@JsonIgnore
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	public boolean isPublic() {
		return isPublic;
	}
	
	public boolean getIsPublic() {
		return isPublic;
	}
	
	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	@JsonIgnore
	public Long getParentId() {
		return parentId;
	}
	@JsonIgnore
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	@JsonIgnore
	public int getType() {
		return type;
	}
	@JsonIgnore
	public void setType(int type) {
		this.type = type;
	}
	
	public boolean isCustomerPresent() {
		return customerPresent;
	}
	
	public void setCustomerPresent(boolean customerPresent) {
		this.customerPresent = customerPresent;
	}
	
	
	
	public int getIsSystemDefined() {
		return isSystemDefined;
	}
	public void setIsSystemDefined(int isSystemDefined) {
		this.isSystemDefined = isSystemDefined;
	}
	public Long getSkeletonEntitySpecId() {
		return skeletonEntitySpecId;
	}
	public void setSkeletonEntitySpecId(Long skeletonEntitySpecId) {
		this.skeletonEntitySpecId = skeletonEntitySpecId;
	}
	public String toCSV() {
		return "[entitySpecId=" + entitySpecId + ", companyId="
				+ companyId + ", entityTitle=" + entityTitle + ", createdBy="
				+ createdBy + ", isPublic=" + isPublic + ", parentId="+parentId+", deleted=" + deleted
				+ ", type=" + type + ", customerPresent=" + customerPresent
				+ "]";
	}
	
	public int getSkeletonCompanyId() {
		return skeletonCompanyId;
	}
	public void setSkeletonCompanyId(int skeletonCompanyId) {
		this.skeletonCompanyId = skeletonCompanyId;
	}
	
	public String getExternalId() {
		return externalId;
	}
	
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public List<EntitySpec> getEntitySpecItems() {
		return entitySpecItems;
	}
	public void setEntitySpecItems(List<EntitySpec> entitySpecItems) {
		this.entitySpecItems = entitySpecItems;
	}
	public boolean isBlockedByOps() {
		return blockedByOps;
	}
	public void setBlockedByOps(boolean blockedByOps) {
		this.blockedByOps = blockedByOps;
	}
	public Integer getSyncScope() {
		return syncScope;
	}
	public void setSyncScope(Integer syncScope) {
		this.syncScope = syncScope;
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
	public String getListItemsModifiedTime() {
		return listItemsModifiedTime;
	}
	public void setListItemsModifiedTime(String listItemsModifiedTime) {
		this.listItemsModifiedTime = listItemsModifiedTime;
	}
	public String getListItemsCount() {
		return listItemsCount;
	}
	public void setListItemsCount(String listItemsCount) {
		this.listItemsCount = listItemsCount;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getPermissionTypeCsv() {
		return permissionTypeCsv;
	}
	public void setPermissionTypeCsv(String permissionTypeCsv) {
		this.permissionTypeCsv = permissionTypeCsv;
	}
	public int getEntityEmpMappingType() {
		return entityEmpMappingType;
	}
	public void setEntityEmpMappingType(int entityEmpMappingType) {
		this.entityEmpMappingType = entityEmpMappingType;
	}
	public int getAddedEntity() {
		return addedEntity;
	}
	public void setAddedEntity(int addedEntity) {
		this.addedEntity = addedEntity;
	}
	public int getModifiedEntity() {
		return modifiedEntity;
	}
	public void setModifiedEntity(int modifiedEntity) {
		this.modifiedEntity = modifiedEntity;
	}
	public int getDeletedEntity() {
		return deletedEntity;
	}
	public void setDeletedEntity(int deletedEntity) {
		this.deletedEntity = deletedEntity;
	}
	public boolean isVisibleListNoInEntitySelectionPicker() {
		return visibleListNoInEntitySelectionPicker;
	}
	public void setVisibleListNoInEntitySelectionPicker(boolean visibleListNoInEntitySelectionPicker) {
		this.visibleListNoInEntitySelectionPicker = visibleListNoInEntitySelectionPicker;
	}
	public boolean isEnableGlobalSearch() {
		return enableGlobalSearch;
	}
	public void setEnableGlobalSearch(boolean enableGlobalSearch) {
		this.enableGlobalSearch = enableGlobalSearch;
	}
	public boolean isExactMatch() {
		return exactMatch;
	}
	public void setExactMatch(boolean exactMatch) {
		this.exactMatch = exactMatch;
	}

	public Long getFilteredEntitySpecId() {
		return filteredEntitySpecId;
	}
	public void setFilteredEntitySpecId(Long filteredEntitySpecId) {
		this.filteredEntitySpecId = filteredEntitySpecId;
	}
	public Long getFilteredEntityFieldSpecId() {
		return filteredEntityFieldSpecId;
	}
	public void setFilteredEntityFieldSpecId(Long filteredEntityFieldSpecId) {
		this.filteredEntityFieldSpecId = filteredEntityFieldSpecId;
	}
	
	
	public boolean isSimpleSearchableListNo() {
		return simpleSearchableListNo;
	}
	public void setSimpleSearchableListNo(boolean simpleSearchableListNo) {
		this.simpleSearchableListNo = simpleSearchableListNo;
	}
	public boolean isAdvancedSearchableListNo() {
		return advancedSearchableListNo;
	}
	public void setAdvancedSearchableListNo(boolean advancedSearchableListNo) {
		this.advancedSearchableListNo = advancedSearchableListNo;
	}
	public int getIgnoreMappingWhenSyncOffEnabled() {
		return ignoreMappingWhenSyncOffEnabled;
	}
	public void setIgnoreMappingWhenSyncOffEnabled(int ignoreMappingWhenSyncOffEnabled) {
		this.ignoreMappingWhenSyncOffEnabled = ignoreMappingWhenSyncOffEnabled;
	}
	

}
