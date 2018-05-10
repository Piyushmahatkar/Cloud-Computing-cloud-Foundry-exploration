package com.DTO;

import javax.servlet.http.HttpServlet;

public class User_Hier_RoleDTO{
	
	String Role;
	String User;
	String Tenant;
	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	
	public String getTenant() {
		return Tenant;
	}
	public void setTenant(String tenant) {
		Tenant = tenant;
	}

}
