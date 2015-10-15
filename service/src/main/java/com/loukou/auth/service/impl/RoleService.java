package com.loukou.auth.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.loukou.auth.enums.PrivilegesEnum;
import com.loukou.auth.resp.dto.base.RespDto;
import com.loukou.auth.resp.dto.base.RespPureDto;
import com.loukou.auth.service.bo.PrivilegeBo;
import com.loukou.auth.service.bo.RoleBo;
import com.loukou.auth.service.constants.PrivilegeStatus;
import com.loukou.auth.service.dao.AppDao;
import com.loukou.auth.service.dao.RoleDao;
import com.loukou.auth.service.entity.AppEntity;
import com.loukou.auth.service.entity.RoleEntity;
import com.loukou.auth.service.util.PrivilegeUtil;

@Service
public class RoleService {
	
	@Autowired 
	RoleDao roleDao;
	
	@Autowired 
	AppDao appDao;

	public List<RoleEntity> getRolesByAppId(int appId) {
		
		List<RoleEntity> roles = roleDao.findByAppId(appId);
		
		for (RoleEntity role : roles) {
			List<String> privileges = PrivilegeUtil.parsePrivKeys(role.getPrivilege());
			StringBuilder sb = new StringBuilder();
			for (String privilege : privileges) {
				if (PrivilegeUtil.privilegeMap.containsKey(privilege)) {
					sb.append(PrivilegeUtil.privilegeMap.get(privilege).getDesc());
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
		
		RoleBo roleBo = new RoleBo();
		roleBo.setId(roleEntity.getId());
		roleBo.setAppId(roleEntity.getAppId());
		if (appEntity != null) {
			roleBo.setAppDesc(appEntity.getName());
		}
		
		roleBo.setRole(roleEntity.getRole());
		roleBo.setName(roleEntity.getName());
		
		List<PrivilegeBo> privBos = new ArrayList<PrivilegeBo>();
		List<String> usedPrivKeyList = new ArrayList<String>();
		
		// 角色设置的权限
		for (String privKey : PrivilegeUtil.parsePrivKeys(roleEntity.getPrivilege())) {
			PrivilegeBo privBo = new PrivilegeBo();
			
			if (PrivilegeUtil.getPrivilegeMap().containsKey(privKey)) {// 设置并正确配置的权限
				PrivilegesEnum privEnum = PrivilegeUtil.getPrivilegeMap().get(privKey);
				privBo.setDesc(privEnum.getDesc());
			} else {// 设置并未正确配置的权限
				privBo.setDesc(privKey);
			}
			privBo.setStatus(PrivilegeStatus.INUSE);
			privBo.setPrivKey(privKey);
			privBos.add(privBo);
			
			usedPrivKeyList.add(privKey);
		}
		// 未设置的权限
		for (String privKey: PrivilegeUtil.getPrivilegeMap().keySet()) {
			if (usedPrivKeyList.contains(privKey)) {
				continue;
			}
			
			PrivilegesEnum privEnum = PrivilegeUtil.getPrivilegeMap().get(privKey);
			
			PrivilegeBo privBo = new PrivilegeBo();
			privBo.setDesc(privEnum.getDesc());
			privBo.setStatus(PrivilegeStatus.NOT_INUSE);
			privBo.setPrivKey(privKey);
			privBos.add(privBo);
		}
		
		roleBo.setPrivileges(privBos);
		
		return roleBo;
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
		
		privKeys = new ArrayList<String>(new HashSet<String>(privKeys));
		
		StringBuilder privilege = new StringBuilder();
				
		for (String privKey : privKeys) {
			if (!PrivilegeUtil.getPrivilegeMap().containsKey(privKey)) {
				return new RespPureDto(204, "数据错误！传入权限" + privKey + "不存在于配置文件！");
			}
			
			privilege.append(privKey + "\n");
		}
		
		roleEntity.setName(name);
		roleEntity.setPrivilege(privilege.toString());
		
		roleDao.save(roleEntity);
		
		return new RespPureDto(200, "更新成功！");
		
	}
	
	
	
	
}
