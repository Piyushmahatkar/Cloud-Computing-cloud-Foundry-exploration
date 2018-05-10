package com.DTO;

import java.util.ArrayList;
import java.util.List;

public class Package_Role_PermissionDTO {
	String PackagePermissionID1;

	public String getPackagePermissionID1() {
		return PackagePermissionID1;
	}

	public void setPackagePermissionID1(String packagePermissionID1) {
		PackagePermissionID1 = packagePermissionID1;
	}
	List<String> PackagePermissionID = new ArrayList<String>();
	
	String PackageRoleID;
	public void setPackagePermissionID(List<String> packagePermissionID) {
		PackagePermissionID = packagePermissionID;
	}
	
	public List<String> getPackagePermissionID() {
		return PackagePermissionID;
	}

	public String getPackageRoleID() {
		return PackageRoleID;
	}
	public void setPackageRoleID(String packageRoleID) {
		PackageRoleID = packageRoleID;
	}
}
