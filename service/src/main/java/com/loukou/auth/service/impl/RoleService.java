package com.loukou.auth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.loukou.auth.resp.dto.base.ListDto;
import com.loukou.auth.resp.dto.base.RespListDto;
import com.loukou.auth.resp.dto.base.RespPureDto;
import com.loukou.auth.service.bo.PrivilegeBo;
import com.loukou.auth.service.bo.RoleBo;
import com.loukou.auth.service.constants.PrivilegeStatus;
import com.loukou.auth.service.dao.AppDao;
import com.loukou.auth.service.dao.PrivilegeDao;
import com.loukou.auth.service.dao.RoleDao;
import com.loukou.auth.service.dao.UserRoleDao;
import com.loukou.auth.service.entity.AppEntity;
import com.loukou.auth.service.entity.PrivilegeEntity;
import com.loukou.auth.service.entity.RoleEntity;
import com.loukou.auth.service.entity.UserRoleEntity;
import com.loukou.auth.service.util.PrivilegeUtil;

@Service
public class RoleService {
	
	@Autowired 
	RoleDao roleDao;
	
	@Autowired 
	AppDao appDao;
	
	@Autowired 
	PrivilegeDao privilegeDao;
	
	@Autowired 
	UserRoleDao userRoleDao;

	public List<RoleEntity> getRolesByAppId(int appId) {
		
		Map<String, PrivilegeEntity> privMap = this.getPrivMapByAppId(appId);
		
		if (privMap == null) {
			return null;
		}
		
		List<RoleEntity> roles = roleDao.findByAppId(appId);
		
		for (RoleEntity role : roles) {
			List<String> privileges = PrivilegeUtil.parsePrivKeys(role.getPrivilege());
			StringBuilder sb = new StringBuilder();
			for (String privilege : privileges) {
				if (privMap.containsKey(privilege)) {
					sb.append(privMap.get(privilege).getName());
				} else {
					sb.append(privilege);
				}
				sb.append("|");
			}
			role.setPrivilege(sb.toString());
		}
		
		return roles;
		
	}
	
	
	
	public List<RoleBo> getAllRoles() {
		
		Map<String, PrivilegeEntity> privMap = this.getAllPrivMap();
		
		if (privMap == null) {
			return null;
		}
		
		List<AppEntity> appList = appDao.findAll();
		Map<Integer, AppEntity> appMap = new HashMap<Integer, AppEntity>();
		for (AppEntity app : appList) {
			appMap.put(app.getId(), app);
		}
		
		List<RoleEntity> roles = roleDao.findAll();
		
		List<RoleBo> roleBos = new ArrayList<RoleBo>();
		
		for (RoleEntity role : roles) {
			RoleBo roleBo = new RoleBo();
			roleBo.setId(role.getId());
			if (appMap.containsKey(role.getAppId())) {
				roleBo.setAppName(appMap.get(role.getAppId()).getName());
			}
			roleBo.setAppId(role.getAppId());
			roleBo.setName(role.getName());
			
			
			
			List<String> privileges = PrivilegeUtil.parsePrivKeys(role.getPrivilege());
			StringBuilder sb = new StringBuilder();
			for (String privilege : privileges) {
				if (privMap.containsKey(privilege)) {
					sb.append(privMap.get(privilege).getName());
				} else {
					sb.append(privilege);
				}
				sb.append("|");
			}
			role.setPrivilege(sb.toString());
			roleBo.setPrivilege(sb.toString());
			roleBos.add(roleBo);
		}
		
		return roleBos;
		
	}
	
	
	
	public RespListDto<RoleEntity> getRolesByAppIdAndUserId(int appId, int userId) {
		List<RoleEntity> rolesOfUser = new ArrayList<RoleEntity>();

		List<RoleEntity> roles = roleDao.findByAppId(appId);
		
		if (CollectionUtils.isEmpty(roles)) {
			return new RespListDto<RoleEntity>(204, "系统中没有角色！");
		}

		List<UserRoleEntity> userRoles = userRoleDao.findByUserId(userId);
		if (CollectionUtils.isEmpty(userRoles)) {
			return new RespListDto<RoleEntity>(204, "用户未关联任何角色！");
		}
		
		List<Integer> roleIds = new ArrayList<Integer>();
		for (UserRoleEntity userRole : userRoles) {
			roleIds.add(userRole.getRoleId());
		}
		
		for (RoleEntity role : roles) {
			if (roleIds.contains(role.getId())) {
				rolesOfUser.add(role);
			}
		}
		
		ListDto<RoleEntity> listDto = new ListDto<RoleEntity>("ok", rolesOfUser);

		return new RespListDto<RoleEntity>(200, "ok", listDto);
		
	}
	
	
	
	
	public RespListDto<RoleEntity> getRolesByUserId(int userId) {
		List<RoleEntity> rolesOfUser = new ArrayList<RoleEntity>();

		List<RoleEntity> roles = roleDao.findAll();
		
		if (CollectionUtils.isEmpty(roles)) {
			return new RespListDto<RoleEntity>(204, "系统中没有角色！");
		}

		List<UserRoleEntity> userRoles = userRoleDao.findByUserId(userId);
		if (CollectionUtils.isEmpty(userRoles)) {
			return new RespListDto<RoleEntity>(204, "用户未关联任何角色！");
		}
		
		List<Integer> roleIds = new ArrayList<Integer>();
		for (UserRoleEntity userRole : userRoles) {
			roleIds.add(userRole.getRoleId());
		}
		
		for (RoleEntity role : roles) {
			if (roleIds.contains(role.getId())) {
				rolesOfUser.add(role);
			}
		}
		
		ListDto<RoleEntity> listDto = new ListDto<RoleEntity>("ok", rolesOfUser);

		return new RespListDto<RoleEntity>(200, "ok", listDto);
		
	}
	
	
	public RoleBo getRoleById(int roleId) {
		
		RoleEntity roleEntity = roleDao.findById(roleId);
		
		if (roleEntity == null) {
			return null;
		}
		
		AppEntity appEntity = appDao.findById(roleEntity.getAppId());

		if (appEntity == null) {
			return null;
		}

		Map<String, PrivilegeEntity> privMap = this.getPrivMapByAppId(appEntity.getId());
		
		if (privMap == null) {
			return null;
		}
		
		
		RoleBo roleBo = new RoleBo();
		roleBo.setId(roleEntity.getId());
		roleBo.setAppId(roleEntity.getAppId());
		if (appEntity != null) {
			roleBo.setAppDesc(appEntity.getName());
		}
		
		roleBo.setRole(roleEntity.getRoleKey());
		roleBo.setName(roleEntity.getName());
		
		List<PrivilegeBo> privBos = new ArrayList<PrivilegeBo>();
		List<String> usedPrivKeyList = new ArrayList<String>();
		
		// 角色设置的权限
		for (String privKey : PrivilegeUtil.parsePrivKeys(roleEntity.getPrivilege())) {
			PrivilegeBo privBo = new PrivilegeBo();
			
			if (privMap.containsKey(privKey)) {// 设置并正确配置的权限
				privBo.setName(privMap.get(privKey).getName());
			} else {// 设置并未正确配置的权限
				privBo.setName(privKey);
			}
			privBo.setStatus(PrivilegeStatus.INUSE);
			privBo.setPrivKey(privKey);
			privBos.add(privBo);
			
			usedPrivKeyList.add(privKey);
		}
		// 未设置的权限
		for (String privKey: privMap.keySet()) {
			if (usedPrivKeyList.contains(privKey)) {
				continue;
			}
			
			PrivilegeEntity priv = privMap.get(privKey);
			
			PrivilegeBo privBo = new PrivilegeBo();
			privBo.setName(priv.getName());
			privBo.setStatus(PrivilegeStatus.NOT_INUSE);
			privBo.setPrivKey(privKey);
			privBos.add(privBo);
		}
		
		roleBo.setPrivileges(privBos);
		
		return roleBo;
	}
	
	
	@Transactional(value="transactionManager")
	public RespPureDto create(int appId, String name, String key) {
		
		if (StringUtils.isEmpty(name)) {
			return new RespPureDto(204, "角色名不得为空！");
		}
		if (StringUtils.isEmpty(key)) {
			return new RespPureDto(204, "角色标识不得为空！");
		}
		
		AppEntity app = appDao.findById(appId);
		
		if (app == null) {
			return new RespPureDto(400, "相应app不存在！");
		}
		
		RoleEntity role = roleDao.findByRoleKey(key);
		
		if (role != null) {
			return new RespPureDto(400, "角色标识已经存在！");
		}
		
		role = new RoleEntity();
		role.setAppId(appId);
		role.setName(name);
		role.setRoleKey(key);
		role.setPrivilege("");
		roleDao.save(role);
		
		return new RespPureDto(200, "角色创建成功！");
	}
	
	
	
	
	@Transactional(value="transactionManager")
	public RespPureDto update(int id, String name, List<String> privKeys) {
		RoleEntity roleEntity = roleDao.findById(id);
		
		if (roleEntity == null) {
			return new RespPureDto(204, "no role by id");
		}
		
		if (StringUtils.isEmpty(name)) {
			return new RespPureDto(204, "角色描述不得为空！");
		}
		
		if (privKeys == null) {
			return new RespPureDto(204, "系统错误，权限为null！");
		}

		Map<String, PrivilegeEntity> privMap = this.getPrivMapByAppId(roleEntity.getAppId());

		if (privMap == null) {
			return new RespPureDto(204, "系统数据错误，角色对应的app或appKey不存在！");
		}
		
		privKeys = new ArrayList<String>(new HashSet<String>(privKeys));
		
		StringBuilder privilege = new StringBuilder();
				
		for (String privKey : privKeys) {
			if (!privMap.containsKey(privKey)) {
				return new RespPureDto(204, "数据错误！传入权限" + privKey + "不存在于配置文件！");
			}
			
			privilege.append(privKey + "\n");
		}
		
		roleEntity.setName(name);
		roleEntity.setPrivilege(privilege.toString());
		
		roleDao.save(roleEntity);
		
		return new RespPureDto(200, "更新成功！");
		
	}
	
	
	public RespPureDto updateRolesForUser(int appId, int userId, List<Integer> roleIds) {
		
		if (roleIds == null) {
			return new RespPureDto(204, "参数错误，roleIds为null！");
		}

		List<Integer> appRoleIds = roleDao.findIdsByAppId(appId);
		
		if (CollectionUtils.isEmpty(appRoleIds)) {
			return new RespPureDto(204, "系统中没有角色！");
		}
		
		for (Integer roleId : roleIds) {
			if (!appRoleIds.contains(roleId)) {
				return new RespPureDto(204, "数据错误，要新建的用户角色不存在于此app!");
			}
		}

		List<UserRoleEntity> userRoles = userRoleDao.findByUserId(userId);
		if (CollectionUtils.isEmpty(userRoles)) {
			return new RespPureDto(204, "用户未关联任何角色！");
		}
		
		List<Integer> roleIdExist = new ArrayList<Integer>();
		
		for (UserRoleEntity  userRole : userRoles) {
			roleIdExist.add(userRole.getRoleId());
		}
		
		
		List<Integer> roleIdDeleting = new ArrayList<Integer>();
		List<Integer> roleIdAdding = new ArrayList<Integer>();	
		
		roleIdAdding.addAll(roleIds);
		roleIdAdding.removeAll(roleIdExist);
		roleIdDeleting.addAll(roleIdExist);
		roleIdDeleting.removeAll(roleIds);
		
		for (Integer roleId : roleIdAdding) {
			UserRoleEntity userRole = new UserRoleEntity();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			userRoleDao.save(userRole);
		}
		
		if (roleIdDeleting.size() > 0) {
			userRoles = userRoleDao.findByRoleIdsAndUserId(roleIdDeleting, userId);
			if (!CollectionUtils.isEmpty(userRoles)) {
				for (UserRoleEntity userRole : userRoles) {
					userRoleDao.delete(userRole);
				}
			}
		}
		
		return new RespPureDto(200, "用户角色更新成功！");
		
	}	
	
	
public RespPureDto updateRolesForUser(int userId, List<Integer> roleIds) {
		
		if (roleIds == null) {
			return new RespPureDto(204, "参数错误，roleIds为null！");
		}

		List<Integer> appRoleIds = roleDao.findIdsByAll();
		
		if (CollectionUtils.isEmpty(appRoleIds)) {
			return new RespPureDto(204, "系统中没有角色！");
		}
		
		for (Integer roleId : roleIds) {
			if (!appRoleIds.contains(roleId)) {
				return new RespPureDto(204, "数据错误，要新建的用户角色不存在于此app!");
			}
		}

		List<UserRoleEntity> userRoles = userRoleDao.findByUserId(userId);
		List<Integer> roleIdExist = new ArrayList<Integer>();
		
		if (!CollectionUtils.isEmpty(userRoles)) {
			for (UserRoleEntity  userRole : userRoles) {
				roleIdExist.add(userRole.getRoleId());
			}			
		}
		
		
		

		
		
		List<Integer> roleIdDeleting = new ArrayList<Integer>();
		List<Integer> roleIdAdding = new ArrayList<Integer>();	
		
		roleIdAdding.addAll(roleIds);
		roleIdAdding.removeAll(roleIdExist);
		roleIdDeleting.addAll(roleIdExist);
		roleIdDeleting.removeAll(roleIds);
		
		for (Integer roleId : roleIdAdding) {
			UserRoleEntity userRole = new UserRoleEntity();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			userRoleDao.save(userRole);
		}
		
		if (roleIdDeleting.size() > 0) {
			userRoles = userRoleDao.findByRoleIdsAndUserId(roleIdDeleting, userId);
			if (!CollectionUtils.isEmpty(userRoles)) {
				for (UserRoleEntity userRole : userRoles) {
					userRoleDao.delete(userRole);
				}
			}
		}
		
		return new RespPureDto(200, "用户角色更新成功！");
		
	}	
	
	
	
	
	private Map<String, PrivilegeEntity> getPrivMapByAppId(int appId) {
		AppEntity app = appDao.findById(appId);
		if (app == null) {
			return null;
		}
		
		String appKey = app.getAppKey();
		
		if (StringUtils.isEmpty(appKey)) {
			return null;
		}
		
		List<PrivilegeEntity> privs = privilegeDao.findByAppKey(appKey);
		
		Map<String, PrivilegeEntity> privMap = new HashMap<String, PrivilegeEntity>();
		
		if (!CollectionUtils.isEmpty(privs)) {
			for (PrivilegeEntity priv : privs) {
				privMap.put(priv.getPrivKey(), priv);
			}
		}
		
		return privMap;
	}
	
	
	private  Map<String, PrivilegeEntity> getAllPrivMap() {
		
		List<PrivilegeEntity> privs = privilegeDao.findAll();
		
		Map<String, PrivilegeEntity> privMap = new HashMap<String, PrivilegeEntity>();
		
		if (!CollectionUtils.isEmpty(privs)) {
			for (PrivilegeEntity priv : privs) {
				privMap.put(priv.getPrivKey(), priv);
			}
		}
		
		return privMap;
		
		
	}

	
	
	
	
}
