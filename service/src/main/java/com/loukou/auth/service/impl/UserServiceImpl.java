package com.loukou.auth.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.loukou.auth.resp.dto.base.RespPageDto;
import com.loukou.auth.resp.dto.base.RespPureDto;
import com.loukou.auth.service.UserService;
import com.loukou.auth.service.bo.PrivilegeBo;
import com.loukou.auth.service.bo.RoleBo;
import com.loukou.auth.service.bo.UserBo;
import com.loukou.auth.service.dao.RoleDao;
import com.loukou.auth.service.dao.UserDao;
import com.loukou.auth.service.dao.UserRoleDao;
import com.loukou.auth.service.entity.RoleEntity;
import com.loukou.auth.service.entity.UserEntity;
import com.loukou.auth.service.entity.UserRoleEntity;
import com.loukou.auth.service.util.AuthServiceUtil;
import com.loukou.auth.service.util.PrivilegeUtil;

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
			
			// 获取用户角色
			List<UserRoleEntity> userRoles = userRoleDao.findByUserId(userId);
			
			if (userRoles != null && userRoles.size() > 0) {
				List<Integer> roleIds = new ArrayList<Integer>(userRoles.size());
				for (int i = 0; i < userRoles.size(); ++i) {
					roleIds.add(userRoles.get(i).getRoleId());
				}

				List<RoleEntity> roles = roleDao.findById(roleIds);
				List<RoleBo> roleBos = new ArrayList<RoleBo>(roles.size());
				
				//遍历用户所属
				for (int i = 0; i < roles.size(); ++i) {
					if (roles.get(i).getAppId() == appId) {
						RoleBo role = new RoleBo();
						role.setId(roles.get(i).getId());
						role.setRole(roles.get(i).getRole());
						
						String privilegeStr = roles.get(i).getPrivilege();
						List<String> privKeys = PrivilegeUtil.parsePrivKeys(privilegeStr);
						
						List<PrivilegeBo> privBos = new ArrayList<PrivilegeBo>();
						for (String privKey : privKeys) {
							PrivilegeBo privBo = new PrivilegeBo();
							privBo.setPrivKey(privKey);
							privBos.add(privBo);
						}
						
						role.setPrivileges(privBos);
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

	@Transactional(value="transactionManager")
	@Override
	public RespPureDto createUser(UserBo user) {
		UserEntity entity = new UserEntity();
		entity.setEmail(user.getEmail());
		entity.setRealName(user.getName());
		entity.setDepartment(user.getDepartment());
		entity.setPassword(DigestUtils.md5Hex(user.getEmail()));
		entity.setCreateTime(new Date());
		userDao.save(entity);
		
		return new RespPureDto(200, "用户创建成功！");
	}

	@Override
	public void assignRole(int userId, List<RoleBo> roles) {

	}

	@Override
	public RespPageDto<UserBo> getUsers(int pageNum, int pageSize) {
		
		List<UserBo> userBos = new ArrayList<UserBo>();
			
		if (pageNum < 1 || pageSize < 1) {
			return new RespPageDto<UserBo>(0, userBos);
		}
		
		Pageable page = new PageRequest(pageNum - 1, pageSize);
		Page<UserEntity> usersPage = userDao.findAll(page);
		
		List<UserEntity> users = usersPage.getContent();
		
		if (!CollectionUtils.isEmpty(users)) {
			for (UserEntity user : users) {
				UserBo userBo = new UserBo();
				userBo.setId(user.getId());
				userBo.setEmail(user.getEmail());
				userBo.setDepartment(user.getDepartment());
				userBo.setName(user.getRealName());
				userBos.add(userBo);
			} 
		} 
	
		return new RespPageDto<UserBo>(usersPage.getTotalElements(), userBos);
	}

	@Override
	public RespPageDto<UserBo> getUsersWithRole(int appId, int pageNum, int pageSize) {
		
		List<UserBo> userBos = new ArrayList<UserBo>();
		
		List<RoleEntity> roleEntitys = roleDao.findByAppId(appId);
		
		if (CollectionUtils.isEmpty(roleEntitys)) {
			return new RespPageDto<UserBo>(0, userBos);
		}
		
		List<Integer> roleIds = new ArrayList<Integer>();
		Map<Integer, RoleEntity> roleMap = new HashMap<Integer, RoleEntity>();
		
		for (RoleEntity roleEntity : roleEntitys) {
			roleIds.add(roleEntity.getId());
			roleMap.put(roleEntity.getId(), roleEntity);
		}
		
		List<UserRoleEntity> userRoles = userRoleDao.findByRoleIds(roleIds);
		if (CollectionUtils.isEmpty(userRoles)) {
			return new RespPageDto<UserBo>(0, userBos);
		}
		
		List<Integer> userIds = new ArrayList<Integer>();
		Map<Integer, List<RoleEntity>> userRoleMap = new HashMap<Integer, List<RoleEntity>>();
		
		for (UserRoleEntity userRole : userRoles) {
			int userId = userRole.getUserId();
			if (!userIds.contains(userId)) {
				userIds.add(userId);
			}
			if (!userRoleMap.containsKey(userId)) {
				userRoleMap.put(userId, new ArrayList<RoleEntity>());
			}
			userRoleMap.get(userId).add(roleMap.get(userRole.getRoleId()));
		}
		
		
		Pageable page = new PageRequest(pageNum - 1, pageSize);
		Page<UserEntity> userEntitiesPage = userDao.findByIds(userIds, page);
		
		List<UserEntity> users = userEntitiesPage.getContent();
		
		for (UserEntity user : users) {
			UserBo userBo = new UserBo();
			userBo.setId(user.getId());
			userBo.setEmail(user.getEmail());
			userBo.setDepartment(user.getDepartment());
			userBo.setName(user.getRealName());
			
			List<RoleEntity> roles = userRoleMap.get(user.getId());
			List<RoleBo> roleBos = new ArrayList<RoleBo>();
			
			if (!CollectionUtils.isEmpty(roles)) {
				for (RoleEntity role : roles) {
					RoleBo roleBo = new RoleBo();
					roleBo.setName(role.getName());
					roleBos.add(roleBo);
				}
			}
			
			userBo.setRoles(roleBos);
			userBos.add(userBo);
		}
		
		return new RespPageDto<UserBo>(userEntitiesPage.getTotalElements(), userBos);
	}

	@Override
	public RespPageDto<UserBo> getUsersNotInApp(int appId, int pageNum, int pageSize) {
		
		
		List<UserBo> userBos = new ArrayList<UserBo>();
		
		List<RoleEntity> roleEntitys = roleDao.findByAppId(appId);
		
		List<Integer> roleIds = new ArrayList<Integer>();
		
		Map<Integer, RoleEntity> roleMap = new HashMap<Integer, RoleEntity>();
		if (!CollectionUtils.isEmpty(roleEntitys)) {
			for (RoleEntity roleEntity : roleEntitys) {
				roleIds.add(roleEntity.getId());
				roleMap.put(roleEntity.getId(), roleEntity);
			}
		}
		
		List<UserRoleEntity> userRoles = userRoleDao.findByRoleIds(roleIds);
		Map<Integer, List<RoleEntity>> userRoleMap = new HashMap<Integer, List<RoleEntity>>();
		List<Integer> userIds = new ArrayList<Integer>();
		
		if (!CollectionUtils.isEmpty(userRoles)) {
			for (UserRoleEntity userRole : userRoles) {
				int userId = userRole.getUserId();
				if (!userIds.contains(userId)) {
					userIds.add(userId);
				}
				if (!userRoleMap.containsKey(userId)) {
					userRoleMap.put(userId, new ArrayList<RoleEntity>());
				}
				userRoleMap.get(userId).add(roleMap.get(userRole.getRoleId()));
			}
		}
		
		
		Pageable page = new PageRequest(pageNum - 1, pageSize);
		
		Page<UserEntity> userEntitiesPage;
		
		if (CollectionUtils.isEmpty(userIds)) {
			userEntitiesPage = userDao.findAll(page);
		} else {
			userEntitiesPage = userDao.findByNotInIds(userIds, page);
		}
		
		List<UserEntity> users = userEntitiesPage.getContent();
		
		for (UserEntity user : users) {
			UserBo userBo = new UserBo();
			userBo.setId(user.getId());
			userBo.setEmail(user.getEmail());
			userBo.setDepartment(user.getDepartment());
			userBo.setName(user.getRealName());
			userBos.add(userBo);
		}		
		
		return new RespPageDto<UserBo>(userEntitiesPage.getTotalElements(), userBos);
	}

	@Transactional(value="transactionManager")
	@Override
	public RespPureDto addUserRoleForApp(int appId, List<Integer> userIds, List<Integer> roleIds) {
		
		if (CollectionUtils.isEmpty(userIds)) {
			return new RespPureDto(204, "userId为空！");
		}
		if (CollectionUtils.isEmpty(roleIds)) {
			return new RespPureDto(204, "roleId为空！");
		}
		
		List<Integer> appIds = roleDao.findAppIdById(roleIds);
		Set<Integer> appIdsSet = new HashSet<Integer>(appIds);
		
		if (CollectionUtils.isEmpty(appIdsSet)) {
			return new RespPureDto(204, "roleIds对应app为空！");
		}
		if (appIdsSet.size() > 1) {
			return new RespPureDto(204, "roleIds对应多个app！");
		}
		if (appIds.get(0) != appId) {
			return new RespPureDto(204, "传入角色对应的app与当前app不一致！");
		}
		
		for (Integer userId : userIds) {
			for (Integer roleId : roleIds) {
				UserRoleEntity userRole = new UserRoleEntity();
				userRole.setRoleId(roleId);
				userRole.setUserId(userId);
				userRoleDao.save(userRole);
			}
		}

		return new RespPureDto(200, "添加成功！");
	}
}
