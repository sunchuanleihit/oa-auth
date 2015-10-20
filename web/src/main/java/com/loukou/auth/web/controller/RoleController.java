package com.loukou.auth.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loukou.auth.resp.dto.base.RespListDto;
import com.loukou.auth.resp.dto.base.RespPureDto;
import com.loukou.auth.service.bo.RoleBo;
import com.loukou.auth.service.entity.RoleEntity;
import com.loukou.auth.service.impl.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/index")
	public String index(
			@RequestParam(value="appid",required=false) String appId,
			Model model
			) {
		return "role/index";
	}
	
	
	@RequestMapping(value = "/detail")
	public String detail(
			@RequestParam(value="id") int id,
			Model model
			) {
		RoleBo role = roleService.getRoleById(id);
		
		model.addAttribute("role", role);
		return "role/detail";
	}
	

	
	@RequestMapping(value = "/rolesByAppId")
	@ResponseBody
	public List<RoleEntity> getRoles(
			@RequestParam(value="appid",required=false) int appId,
			Model model
			) {
		
		List<RoleEntity> roles = roleService.getRolesByAppId(appId);
		
		return roles;
	}
	
	
	@RequestMapping(value = "/rolesByAppIdAndUserId")
	@ResponseBody
	public RespListDto<RoleEntity> getRoles(
			@RequestParam(value="appId") int appId,
			@RequestParam(value="userId") int userId
			) {
		
		return roleService.getRolesByAppIdAndUserId(appId, userId);
	}
	
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST )
	@ResponseBody
	public RespPureDto  update(
			@RequestParam(value="appId") int appId,
			@RequestParam(value="name") String name,
			@RequestParam(value="key") String key
			) {

		return roleService.create(appId, name, key);
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT )
	@ResponseBody
	public RespPureDto  update(
			@RequestParam(value="id") int id,
			@RequestParam(value="name") String name,
			@RequestParam(value="privKeys[]") List<String> privKeys
			) {
		return roleService.update(id, name, privKeys);
	}
	
	
	
	@RequestMapping(value = "/changeRolesForUser", method = RequestMethod.PUT )
	@ResponseBody
	public RespPureDto  update(
			@RequestParam(value="appId") int appId,
			@RequestParam(value="userId") int userId,
			@RequestParam(value="roleId[]") List<Integer> roleIds
			) {
		return roleService.updateRolesForUser(appId, userId, roleIds);
	}
	
	
	
	
	
	
}
