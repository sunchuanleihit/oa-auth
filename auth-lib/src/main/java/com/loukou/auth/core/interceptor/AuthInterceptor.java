package com.loukou.auth.core.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.loukou.auth.core.annotation.AuthCheck;
import com.loukou.auth.core.config.AuthConfigLoader;
import com.loukou.auth.core.entity.AuthInfo;
import com.loukou.auth.core.util.JsonUtil;
import com.loukou.auth.resp.dto.RespPureDto;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AuthConfigLoader loader;
	

	

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;

			AuthCheck authCheck = handlerMethod
					.getMethodAnnotation(AuthCheck.class);

			if (authCheck == null) {
				authCheck = handlerMethod.getBean().getClass()
						.getAnnotation(AuthCheck.class);
			}

			if (authCheck == null) {
				return true;
			} else {
				AuthInfo info = getInfo(request);
				if (info != null) {
					String[] privileges = authCheck.privileges();
	
					// 按照权限验证权限
					if (privileges != null) {
						for (int i = 0; i < privileges.length; i++) {
							if (info.getPrivileges().contains(privileges[i])) {
								return true;
							}
						}
					}
					
					// 已登陆，但是没有权限
					if (authCheck.isRedirect()) {
						response.getWriter().write("No Permission");
						response.flushBuffer();
					} else {
						response.getWriter().write(JsonUtil.objToJsonString(new RespPureDto(402, "No Permission")));
						response.flushBuffer();
					}
					
				} else {// 未登陆
					
					if (authCheck.isRedirect()) {
						response.sendRedirect(loader.getLoginUrl());
					} else {
						response.getWriter().write(JsonUtil.objToJsonString(new RespPureDto(401, "No Login")));
						response.flushBuffer();
					}
				}

				return false;
			}
		}

		return true;

	}

	private AuthInfo getInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			AuthInfo info = (AuthInfo) session.getAttribute(loader
					.getSessionKey());
			return info;
		}

		return null;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
}