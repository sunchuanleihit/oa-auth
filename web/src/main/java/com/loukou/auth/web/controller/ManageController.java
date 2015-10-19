package com.loukou.auth.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loukou.auth.core.annotation.AuthCheck;
import com.loukou.auth.service.entity.AppEntity;
import com.loukou.auth.service.impl.AppService;
import com.loukou.auth.web.config.ConfigService;
import com.loukou.auth.web.config.ParentTab;


@Controller
@RequestMapping("/")
public class ManageController extends BaseController {
	@Autowired
	private AppService appService;
	
	
	@AuthCheck(privileges={"admin.index"}, isRedirect=true)
	@RequestMapping(value = "")
	public String index(Model model) {
		List<ParentTab> tabs = ConfigService.getConfigTabs();
		List<AppEntity> apps = appService.getAllApps();
		model.addAttribute("apps", apps);
		
        model.addAttribute("tabs",tabs);
		
		return "admin/index";
	}

	

}
