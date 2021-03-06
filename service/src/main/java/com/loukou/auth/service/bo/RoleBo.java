package com.loukou.auth.service.bo;

import java.util.List;

public class RoleBo {

	private int id;
	private int appId;
	private String appName = "";
	private String appDesc;
	private String role;
	private String name;
	private List<PrivilegeBo> privileges;
	private String privilege = "";
	
	
	
	
	
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppDesc() {
		return appDesc;
	}
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<PrivilegeBo> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<PrivilegeBo> privileges) {
		this.privileges = privileges;
	}
	
	
}
