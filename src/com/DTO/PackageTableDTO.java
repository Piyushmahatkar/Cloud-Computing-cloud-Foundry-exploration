package com.DTO;

import java.sql.Date;

public class PackageTableDTO {
	int PackageID;
	String PackageName;
	Date CreatedDate;

	public int getPackageID() {
		return PackageID;
	}

	public void setPackageID(int packageID) {
		PackageID = packageID;
	}

	public String getPackageName() {
		return PackageName;
	}

	public void setPackageName(String packageName) {
		PackageName = packageName;
	}

	public Date getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}

	public int getTenantID() {
		return TenantID;
	}

	public void setTenantID(int tenantID) {
		TenantID = tenantID;
	}

	int TenantID;

}
