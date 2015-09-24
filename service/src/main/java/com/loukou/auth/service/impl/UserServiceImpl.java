package com.loukou.auth.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loukou.auth.service.UserService;
import com.loukou.auth.service.bo.RoleBo;
import com.loukou.auth.service.bo.UserBo;
import com.loukou.auth.service.dao.RoleDao;
import com.loukou.auth.service.dao.UserDao;
import com.loukou.auth.service.dao.UserRoleDao;
import com.loukou.auth.service.entity.RoleEntity;
import com.loukou.auth.service.entity.UserEntity;
import com.loukou.auth.service.entity.UserRoleEntity;
import com.loukou.auth.service.util.AuthServiceUtil;

@Service
public class UserServiceImpl implements UserService {

	private static final String EMAIL_SUFFIX = "@loukou.com";

	private static final char SEPARATOR = '|';

	private static final int TIME_DIFF = 1000 * 60 * 5;

	@Resource(name = "des.key")
	private String desKey;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;

	public UserBo validateToken(int appId, String token) {
		if (StringUtils.isNotEmpty(token)) {
			String formatedStr = AuthServiceUtil.decrypt(token, this.desKey);
			String[] splitted = StringUtils.split(formatedStr, SEPARATOR);
			if (splitted != null && splitted.length == 2
					&& StringUtils.isNumeric(splitted[0])
					&& StringUtils.isNumeric(splitted[1])) {
				int userId = Integer.valueOf(splitted[1]);
				long timestamp = Long.valueOf(splitted[0]);
				if (System.currentTimeMillis() - timestamp <= TIME_DIFF) {
					return getUser(userId, appId);
				}
			}
		}

		return null;
	}

	private UserBo getUser(int userId, int appId) {
		UserEntity user = userDao.findById(userId);
		if (user != null) {
			UserBo userBo = new UserBo();
			userBo.setEmail(user.getEmail());
			userBo.setId(user.getId());
			userBo.setName(user.getRealName());
			userBo.setStatus(user.getStatus());

			List<UserRoleEntity> userRoles = userRoleDao.findByUserId(userId);
			if (userRoles != null && userRoles.size() > 0) {
				List<Integer> roleIds = new ArrayList<Integer>(userRoles.size());
				for (int i = 0; i < userRoles.size(); ++i) {
					roleIds.add(userRoles.get(i).getId());
				}

				List<RoleEntity> roles = roleDao.findById(roleIds);
				List<RoleBo> roleBos = new ArrayList<RoleBo>(roles.size());
				for (int i = 0; i < roles.size(); ++i) {
					if (roles.get(i).getAppId() == appId) {
						RoleBo role = new RoleBo();
						role.setId(roles.get(i).getId());
						role.setRole(roles.get(i).getRole());
						roleBos.add(role);
					}
				}

				userBo.setRoles(roleBos);
			}

			return userBo;
		} else
			return null;
	}

	@Override
	public String login(String email, String password) {
		if (StringUtils.isNotEmpty(email)
				&& StringUtils.endsWith(email, EMAIL_SUFFIX)
				&& StringUtils.isNotEmpty(password)) {
			String md5 = DigestUtils.md5Hex(password);
			UserEntity user = userDao.findByEmailAndPassword(email, md5);
			if (user != null) {
				return generateToken(user.getId());
			}
		}
		return null;
	}

	private String generateToken(int userId) {
		StringBuilder sb = new StringBuilder();
		sb.append(System.currentTimeMillis());
		sb.append(SEPARATOR);
		sb.append(userId);
		return AuthServiceUtil.encrypt(sb.toString(), this.desKey);
	}

	@Override
	public void createUser(UserBo user) {
		UserEntity entity = new UserEntity();
		entity.setEmail(user.getEmail());
		entity.setRealName(user.getName());
		entity.setCreateTime(new Date());
	}

	@Override
	public void assignRole(int userId, List<RoleBo> roles) {

	}
}
