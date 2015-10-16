package com.loukou.auth.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loukou.auth.resp.dto.base.RespPageDto;

@Controller
@RequestMapping("/privilege")
public class PrivilegeController {

	
	@RequestMapping(value = "/index")
	public String index(
			@RequestParam(value="appid",required=false) Integer appId,
			Model model
			) {
		return "privilege/index";
	}
	
	
	
	@RequestMapping(value = "/listByApp")
	@ResponseBody
	public RespPageDto listByApp(
			@RequestParam(value="appid") int appId
			) {
		
		
		return null;
	}
	
	
}
