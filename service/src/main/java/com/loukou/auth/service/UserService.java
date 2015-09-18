package com.loukou.auth.service;

import com.loukou.auth.service.bo.UserBo;

public interface UserService {

	public String login(String email, String password);
	
	public UserBo validateToken(int appId, String token);
}
