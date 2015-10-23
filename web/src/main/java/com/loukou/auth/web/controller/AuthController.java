package com.loukou.auth.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.loukou.auth.service.UserService;
import com.loukou.auth.service.entity.AppEntity;
import com.loukou.auth.service.impl.AppService;

@Controller
public class AuthController extends BaseController {

	@Autowired
	private UserService userService;

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
	public ModelAndView toLogin(@RequestParam("appid") int appId) {
		
		AppEntity app = appService.getApp(appId);
		
		String title = "后台";
		if (app != null) {
			title = app.getName() + title;
 		}
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("appId", appId);
		mv.addObject("app", title);
		return mv;
	}
	
	/** 
	 * 无权限的跳转页面
	 */
	@RequestMapping(value = "/no_priv", method = RequestMethod.GET)
	public ModelAndView noPriv(@RequestParam("appid") int appId) {
		
		AppEntity app = appService.getApp(appId);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("no_priv");
		mv.addObject("appId", appId);
		return mv;
	}
	
	
	
	@RequestMapping(value = "/repass")
	public ModelAndView rePass(
			@RequestParam("appId") int appId,
			@RequestParam("email") String email
			) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("repass");
		mv.addObject("appId", appId);
		mv.addObject("email", email);
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
			@RequestParam String password, @RequestParam("appid") String appId,
			Model model) {
		if (StringUtils.isNotEmpty(userName)
				&& StringUtils.isNotEmpty(password)
				&& StringUtils.isNotEmpty(appId)
				&& StringUtils.isNumeric(appId)) {
			String token = userService.login(userName, password);
			if (token != null) {
				AppEntity app = appService.getApp(Integer.valueOf(appId));
				if (app != null
						&& StringUtils.isNotEmpty(app.getDomainPrefix())) {

					return getRedirectUrl(app.getDomainPrefix(),app.getPort(), token);
				}
			}
		}
		
		model.addAttribute("appId", appId);

		return "error";
	}

	private String getRedirectUrl(String prefix, int port, String token) {
		StringBuilder sb = new StringBuilder("redirect:http://");
		sb.append(prefix);
		sb.append(domainSuffix);
		sb.append(":");
		sb.append(port);
		sb.append("/dologin?");
		sb.append(PARAMETER_TOKEN);
		sb.append("=");
		try {
			sb.append(URLEncoder.encode(token, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
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
