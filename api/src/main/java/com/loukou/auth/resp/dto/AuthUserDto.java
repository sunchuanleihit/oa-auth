package com.loukou.auth.resp.dto;

import java.io.Serializable;

public class AuthUserDto implements Serializable {

	private static final long serialVersionUID = -7200455382427786068L;

	private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
