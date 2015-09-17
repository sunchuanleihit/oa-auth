package com.loukou.auth.web.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.loukou.auth.api.AuthService;
import com.loukou.auth.service.entity.AppEntity;
import com.loukou.auth.service.impl.AppService;

@Controller
public class AuthController extends BaseController {

	@Autowired
	private AuthService authService;

	@Autowired
	private AppService appService;

	@Resource(name = "domain.suffix")
	private String domainSuffix;

	private static final String PARAMETER_TOKEN = "castk";

	/**
	 * 展示login页面
	 * 
	 * @param redir
	 *            成功登录后跳转页面
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView toLogin(@RequestParam("appid") String appId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("appId", appId);
		return mv;
	}

	/**
	 * 提交登录页面 成功时记录cookie 失败时留着本页面
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String login(@RequestParam("username") String userName,
			@RequestParam String password, @RequestParam("appid") String appId) {
		if (StringUtils.isNotEmpty(userName)
				&& StringUtils.isNotEmpty(password)
				&& StringUtils.isNotEmpty(appId)
				&& StringUtils.isNumeric(appId)) {
			String token = authService.login(userName, password);
			if (token != null) {
				AppEntity app = appService.getApp(Integer.valueOf(appId));
				if (app != null
						&& StringUtils.isNotEmpty(app.getDomainPrefix())) {
					StringBuilder sb = new StringBuilder("redirect:http://");
					sb.append(app.getDomainPrefix());
					sb.append(domainSuffix);
					sb.append("/dologin?");
					sb.append(PARAMETER_TOKEN);
					sb.append("=");
					sb.append(token);
					return sb.toString();
				}
			}
		}

		return "error";
	}

	/**
	 * 退出登录 需要身份验证 默认重定向到登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout() {
		return "redirect:/login";
	}
}
