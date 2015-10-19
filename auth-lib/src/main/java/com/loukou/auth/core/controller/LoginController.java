package com.loukou.auth.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loukou.auth.api.AuthService;
import com.loukou.auth.core.config.AuthConfigLoader;
import com.loukou.auth.core.entity.AuthInfo;
import com.loukou.auth.enums.AuthResultEnum;
import com.loukou.auth.resp.dto.AuthUserDto;
import com.loukou.auth.resp.dto.base.RespDto;
import com.loukou.auth.resp.dto.base.RespPureDto;

@Controller
public class LoginController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private AuthConfigLoader configLoader;

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/dologin", method = RequestMethod.GET)
	public String login(@RequestParam("castk") String token) {
		
	
		RespDto<AuthUserDto> resp = authService.validateToken(
				configLoader.getAppId(), token);


		if (resp != null
				&& resp.getData() != null
				&& resp.getCode() == AuthResultEnum.RESULT_AUTH_SUCCESS
						.getCode()) {
			AuthInfo authInfo = new AuthInfo();
			authInfo.setUserId(resp.getData().getUserId());
			authInfo.setUserName(resp.getData().getName());
			authInfo.setRoles(resp.getData().getRoles());
			authInfo.setPrivileges(resp.getData().getPrivileges());
			
			request.getSession().setAttribute(configLoader.getSessionKey(),
					authInfo);


			return "redirect:/";
		} else {
			return "redirect:" + configLoader.getLoginUrl();
		}
	}
	
	
	@RequestMapping(value = "/dologout", method = RequestMethod.GET)
	@ResponseBody
	public RespPureDto logout() {

			request.getSession().setAttribute(configLoader.getSessionKey(),
					null);
			return new RespPureDto(200, "logout");

	}
	
	
}