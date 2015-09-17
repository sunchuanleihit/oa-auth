package com.loukou.auth.resp.dto;

import java.io.Serializable;
import java.util.List;

public class AuthUserDto implements Serializable {

	private static final long serialVersionUID = -7200455382427786068L;

	private int userId;

	private List<String> roles;

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

}
