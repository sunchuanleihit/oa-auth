package com.loukou.auth.core.interceptor;

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
				if (info != null && info.getRoles() != null) {
					String[] roles = authCheck.roles();
					if (roles != null) {
						for (int i = 0; i < roles.length; ++i) {
							if (info.getRoles().contains(roles[i]))
								return true;
						}
					}
				}

				// login required
				response.sendRedirect(loader.getLoginUrl());

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