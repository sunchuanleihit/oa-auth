package com.loukou.auth.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loukou.auth.resp.dto.base.RespPageDto;
import com.loukou.auth.resp.dto.base.RespPureDto;
import com.loukou.auth.service.UserService;
import com.loukou.auth.service.bo.UserBo;
import com.loukou.auth.service.entity.AppEntity;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/index")
	public String index(
			) {

		return "user/index";
	}
	
	
	@RequestMapping(value = "/role")
	public String role(
			) {
		return "user/role";
	}
	
	
	
	
	@RequestMapping(value = "/create")
	@ResponseBody
	public RespPureDto create(
			@RequestParam(value="name") String name,
			@RequestParam(value="email") String email,
			@RequestParam(value="department") String department,
			@RequestParam(value="password") String password
			) {
		UserBo userBo = new UserBo();
		userBo.setEmail(email);
		userBo.setDepartment(department);
		userBo.setName(name);
		userBo.setPassword(password);
		
		return userService.createUser(userBo);
	}
	
	
	
	@RequestMapping(value = "/doRepass")
	@ResponseBody
	public RespPureDto doRepass(
			@RequestParam(value="email") String email,
			@RequestParam(value="oldPassword") String oldPassword,
			@RequestParam(value="newPassword") String newPassword
			) {
		
		return userService.resetPassword(email, oldPassword, newPassword);
	}
	
	
	
	
	
	@RequestMapping(value = "/addUserRoleForApp", method = RequestMethod.PUT )
	@ResponseBody
	public RespPureDto create(
			@RequestParam(value="appId") int appId,
			@RequestParam(value="userId[]") List<Integer> userIds,
			@RequestParam(value="roleId[]") List<Integer> roleIds
			) {
		
		return userService.addUserRoleForApp(appId, userIds, roleIds);
	}
	
	
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public RespPageDto<UserBo> list(
			@RequestParam(value="limit") int limit,
			@RequestParam(value="offset") int offset
			) {
		
		int pageNum = 0;
		int pageSize = 10;
		
		if (limit > 0 && offset >= 0) {
			pageSize = limit;
			pageNum = offset / limit;
		}
		return userService.getUsers(pageNum + 1, pageSize);
	}
	
	
	@RequestMapping(value = "/listNotInApp")
	@ResponseBody
	public RespPageDto<UserBo> listNotInApp(
			@RequestParam(value="appId") int appId,
			@RequestParam(value="limit") int limit,
			@RequestParam(value="offset") int offset
			) {
		
		int pageNum = 0;
		int pageSize = 10;
		
		if (limit > 0 && offset >= 0) {
			pageSize = limit;
			pageNum = offset / limit;
		}
		
		return userService.getUsersNotInApp(appId, pageNum + 1, pageSize);
	}
	

	@RequestMapping(value = "/listWithRole")
	@ResponseBody
	public RespPageDto<UserBo> listWithRole(
			@RequestParam(value="appId") int appId,
			@RequestParam(value="limit") int limit,
			@RequestParam(value="offset") int offset
			) {
		
		int pageNum = 0;
		int pageSize = 10;
		
		if (limit > 0 && offset >= 0) {
			pageSize = limit;
			pageNum = offset / limit;
		}
		
		return userService.getUsersWithRole(appId, pageNum + 1, pageSize);
	}

}
