package com.loukou.auth.service.bo;

import java.util.List;

public class UserBo {

	private int id;

	private String name;

	private String email;

	private String department;

	private int status;

	private List<RoleBo> roles;
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<RoleBo> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBo> roles) {
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
