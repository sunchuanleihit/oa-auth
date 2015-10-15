package com.loukou.auth.service;

import java.util.List;

import com.loukou.auth.resp.dto.base.RespPageDto;
import com.loukou.auth.resp.dto.base.RespPureDto;
import com.loukou.auth.service.bo.RoleBo;
import com.loukou.auth.service.bo.UserBo;

public interface UserService {

	public String login(String email, String password);

	public UserBo validateToken(int appId, String token);

	public RespPureDto createUser(UserBo user);
	
	public RespPureDto addUserRoleForApp(int appId, List<Integer> userIds, List<Integer> roleIds);

	public void assignRole(int userId, List<RoleBo> roles);
	
	public RespPageDto<UserBo> getUsers(int pageNum, int pageSize);
	
	public RespPageDto<UserBo> getUsersWithRole(int appId, int pageNum, int pageSize);
	
	public RespPageDto<UserBo> getUsersNotInApp(int appId, int pageNum, int pageSize);

}
