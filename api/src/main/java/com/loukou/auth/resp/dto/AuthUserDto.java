package com.loukou.auth.resp.dto;

import java.io.Serializable;
import java.util.List;

public class AuthUserDto implements Serializable {

	private static final long serialVersionUID = -7200455382427786068L;

	private int userId;

	private String name;

	private List<String> roles;
	
	private List<String> privileges;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}
	
	

}
