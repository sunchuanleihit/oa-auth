package com.loukou.auth.service;

import java.util.List;

import com.loukou.auth.service.bo.RoleBo;
import com.loukou.auth.service.bo.UserBo;

public interface UserService {

	public String login(String email, String password);

	public UserBo validateToken(int appId, String token);

	public void createUser(UserBo user);

	public void assignRole(int userId, List<RoleBo> roles);

}
