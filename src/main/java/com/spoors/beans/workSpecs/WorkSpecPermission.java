package com.spoors.beans.workSpecs;

import java.util.List;

public class WorkSpecPermission {

	
	public static int VIEW_TYPE = 1;
	public static int ADD_TYPE = 2;
	public static int MODIFY_TYPE = 3;
	public static int DELETE_TYPE = 4;
	public static int SEND_ONLY_ACTIONABLE_WORKS_IN_SYNC_TYPE = 5;
	
	private Long permissionId;
	private Long workSpecId;
	private Long companyId;
	private Long modifiedBy;
	private String createdTime;
	private String modifiedTime;
	private int permissionType;
	private boolean enable;
	private String groupIds;
	
	private List<WorkSpecPermissionGroupIdsBean> workSpecPermissionGroupIdBean;
	
	public Long getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	public Long getWorkSpecId() {
		return workSpecId;
	}
	public void setWorkSpecId(Long workSpecId) {
		this.workSpecId = workSpecId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
	public List<WorkSpecPermissionGroupIdsBean> getWorkSpecPermissionGroupIdBean() {
		return workSpecPermissionGroupIdBean;
	}
	public void setWorkSpecPermissionGroupIdBean(List<WorkSpecPermissionGroupIdsBean> workSpecPermissionGroupIdBean) {
		this.workSpecPermissionGroupIdBean = workSpecPermissionGroupIdBean;
	}
	public int getPermissionType() {
		return permissionType;
	}
	public void setPermissionType(int permissionType) {
		this.permissionType = permissionType;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	
}
