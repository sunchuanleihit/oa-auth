package com.loukou.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loukou.auth.api.AuthService;
import com.loukou.auth.enums.AuthResultEnum;
import com.loukou.auth.resp.dto.AuthUserDto;
import com.loukou.auth.resp.dto.RespDto;
import com.loukou.auth.service.UserService;
import com.loukou.auth.service.dao.UserDao;

@Service("oa-auth-service")
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	public RespDto<AuthUserDto> validateToken(int appId, String token) {
		RespDto<AuthUserDto> result = new RespDto<AuthUserDto>();
		result.setResult(AuthResultEnum.RESULT_AUTH_FAIL);

		return result;
	}
}
