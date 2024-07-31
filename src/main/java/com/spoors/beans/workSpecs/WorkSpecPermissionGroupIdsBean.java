package com.spoors.beans.workSpecs;

public class WorkSpecPermissionGroupIdsBean {

	private int permissionType;
	private String groupIds;
	private boolean enable;
	
	
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public int getPermissionType() {
		return permissionType;
	}
	public void setPermissionType(int permissionType) {
		this.permissionType = permissionType;
	}
	
}
