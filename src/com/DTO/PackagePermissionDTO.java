package com.DTO;

public class PackagePermissionDTO {
	String PackagePermissionID;
	String Description;
	String PackageID;
	int RoleRights;
	String TenantID;
	public String getPackagePermissionID() {
		return PackagePermissionID;
	}
	public void setPackagePermissionID(String packagePermissionID) {
		PackagePermissionID = packagePermissionID;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPackageID() {
		return PackageID;
	}
	public void setPackageID(String packageID) {
		PackageID = packageID;
	}
	public int getRoleRights() {
		return RoleRights;
	}
	public void setRoleRights(int roleRights) {
		RoleRights = roleRights;
	}
	public String getTenantID() {
		return TenantID;
	}
	public void setTenantID(String tenantID) {
		TenantID = tenantID;
	}

	
}
