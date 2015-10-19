package com.loukou.auth.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loukou.auth.core.annotation.AuthCheck;
import com.loukou.auth.resp.dto.PrivilegeRespDto;
import com.loukou.auth.resp.dto.base.RespPageDto;
import com.loukou.auth.resp.dto.base.RespPureDto;
import com.loukou.auth.service.PrivilegeService;

@Controller
@RequestMapping("/privilege")
public class PrivilegeController {
	
	@Autowired
	PrivilegeService privilegeService;

	
	@RequestMapping(value = "/index")
	public String index(
			@RequestParam(value="appid",required=false) Integer appId,
			Model model
			) {
		return "privilege/index";
	}
	
	
	
	@RequestMapping(value = "/listByApp")
	@ResponseBody
	public RespPageDto<PrivilegeRespDto> listByApp(
			@RequestParam(value="appid") int appId,
			@RequestParam(value="offset") int offset, 
			@RequestParam(value="limit") int limit
			) {
		
		int pageNum = 0;
		int pageSize = 10;
		
		if (limit > 0 && offset >= 0) {
			pageSize = limit;
			pageNum = offset / limit;
		}
		pageNum += 1;
		
		return privilegeService.getPrivilegesByAppId(appId, pageNum, pageSize);
	}
	
	@AuthCheck(privileges={"priv.create"})
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public RespPureDto listByApp(
			@RequestParam(value="appId") int appId,
			@RequestParam(value="privName") String privName, 
			@RequestParam(value="privKey") String privKey,
			@RequestParam(value="privType") String privType
			) {

		return privilegeService.create(privName, privKey, privType, appId);
	}
}










