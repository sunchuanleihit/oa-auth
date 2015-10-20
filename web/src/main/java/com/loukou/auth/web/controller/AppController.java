package com.loukou.auth.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loukou.auth.resp.dto.base.RespPureDto;
import com.loukou.auth.service.entity.AppEntity;
import com.loukou.auth.service.impl.AppService;
import com.loukou.auth.service.impl.RoleService;

@Controller
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	private AppService appService;
	
	@RequestMapping(value = "/index")
	public String index(
			
			) {

		
		return "app/index";
	}
	
	@RequestMapping(value = "/info")
	@ResponseBody
	public AppEntity index(
			@RequestParam(value="appId") int appId,
			Model model
			) {
		
		AppEntity app = appService.getApp(appId);
		return app;
	}
	
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public RespPureDto update(
			@RequestParam(value="appId") int appId,
			@RequestParam(value="port") int port,
			@RequestParam(value="domainPrefix") String domainPrefix
			) {
		
		return appService.saveDomainInfoById(appId, domainPrefix, port);
	}
	

}
