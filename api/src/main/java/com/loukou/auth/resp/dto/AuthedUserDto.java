package com.loukou.auth.resp.dto;

import java.io.Serializable;
import java.util.List;

public class AuthedUserDto implements Serializable {
	
	private static final long serialVersionUID = -5382783638162047206L;
	
	private List<Integer> roleIds;
	private int userId;
	
	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
