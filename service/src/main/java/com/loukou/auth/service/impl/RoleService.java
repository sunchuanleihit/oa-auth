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

import com.loukou.auth.resp.dto.base.RespPureDto;
import com.loukou.auth.service.bo.PrivilegeBo;
import com.loukou.auth.service.bo.RoleBo;
import com.loukou.auth.service.constants.PrivilegeStatus;
import com.loukou.auth.service.dao.AppDao;
import com.loukou.auth.service.dao.PrivilegeDao;
import com.loukou.auth.service.dao.RoleDao;
import com.loukou.auth.service.entity.AppEntity;
import com.loukou.auth.service.entity.PrivilegeEntity;
import com.loukou.auth.service.entity.RoleEntity;
import com.loukou.auth.service.util.PrivilegeUtil;

@Service
public class RoleService {
	
	@Autowired 
	RoleDao roleDao;
	
	@Autowired 
	AppDao appDao;
	
	@Autowired 
	PrivilegeDao privilegeDao;

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
	
	
	
	
}
