package com.loukou.auth.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loukou.auth.web.config.ConfigService;
import com.loukou.auth.web.config.ParentTab;


@Controller
@RequestMapping("/admin")
public class ManageController extends BaseController {
	
	@RequestMapping(value = "")
	public String index(
			@RequestParam(value="appid",required=false) String appId,
			Model model
	) {
		List<ParentTab> tabs = ConfigService.getConfigTabs();
        model.addAttribute("tabs",tabs);

		model.addAttribute("appid", appId);
		
		return "admin/index";
	}

	

}
