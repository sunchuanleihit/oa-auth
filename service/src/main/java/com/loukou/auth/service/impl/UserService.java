package com.loukou.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loukou.auth.service.bo.RoleBo;
import com.loukou.auth.service.bo.UserBo;
import com.loukou.auth.service.dao.RoleDao;
import com.loukou.auth.service.dao.UserDao;
import com.loukou.auth.service.dao.UserRoleDao;
import com.loukou.auth.service.entity.RoleEntity;
import com.loukou.auth.service.entity.UserEntity;
import com.loukou.auth.service.entity.UserRoleEntity;

@Service
public class UserService {

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	public UserBo getUser(int userId, int appId) {
		UserEntity user = userDao.findById(userId);
		if (user != null) {
			UserBo userBo = new UserBo();
			userBo.setEmail(user.getEmail());
			userBo.setId(user.getId());
			userBo.setName(user.getRealName());
			userBo.setStatus(user.getStatus());

			List<UserRoleEntity> userRoles = userRoleDao.findByUserIdAndAppId(
					userId, appId);
			if (userRoles != null && userRoles.size() > 0) {
				List<Integer> roleIds = new ArrayList<Integer>(userRoles.size());
				for (int i = 0; i < userRoles.size(); ++i) {
					roleIds.add(userRoles.get(i).getId());
				}

				List<RoleEntity> roles = roleDao.findByIds(roleIds);
				List<RoleBo> roleBos = new ArrayList<RoleBo>(roles.size());
				for (int i = 0; i < roles.size(); ++i) {
					RoleBo role = new RoleBo();
					role.setId(roles.get(i).getId());
					role.setRole(roles.get(i).getRole());
					roleBos.add(role);
				}

				userBo.setRoles(roleBos);
			}

			return userBo;
		} else
			return null;
	}

}
