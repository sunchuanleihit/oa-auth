package com.loukou.auth.service.impl;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loukou.auth.api.AuthService;
import com.loukou.auth.enums.AuthResultEnum;
import com.loukou.auth.exception.AuthRuntimeException;
import com.loukou.auth.resp.dto.AuthUserDto;
import com.loukou.auth.resp.dto.RespDto;
import com.loukou.auth.service.dao.UserDao;
import com.loukou.auth.service.entity.UserEntity;
import com.loukou.auth.service.util.AuthServiceUtil;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserDao userDao;

	private static final int TIME_DIFF = 1000 * 60 * 5;

	private static final String EMAIL_SUFFIX = "@loukou.com";
	private static final char SEPARATOR = '|';

	@Resource(name = "des.key")
	private String desKey;

	@Override
	public String login(String email, String password) {
		if (StringUtils.isNotEmpty(email)
				&& StringUtils.endsWith(email, EMAIL_SUFFIX)
				&& StringUtils.isNotEmpty(password)) {
			String md5 = DigestUtils.md5Hex(password);
			UserEntity user = userDao.findByEmailAndPassword(email, md5);
			if (user != null) {
				return generateToken(user.getId());
			}
		}
		return null;
	}

	private String generateToken(int userId) {
		StringBuilder sb = new StringBuilder();
		sb.append(System.currentTimeMillis());
		sb.append(SEPARATOR);
		sb.append(userId);
		return AuthServiceUtil.encrypt(sb.toString(), this.desKey);
	}

	public RespDto<AuthUserDto> validateToken(int appId, String token) {
		RespDto<AuthUserDto> result = new RespDto<AuthUserDto>();
		try {
			if (StringUtils.isNotEmpty(token)) {
				String formatedStr = AuthServiceUtil
						.decrypt(token, this.desKey);
				String[] splitted = StringUtils.split(formatedStr, SEPARATOR);
				if (splitted != null && splitted.length == 2
						&& StringUtils.isNumeric(splitted[0])
						&& StringUtils.isNumeric(splitted[1])) {
					int userId = Integer.valueOf(splitted[1]);
					long timestamp = Long.valueOf(splitted[0]);
					if (System.currentTimeMillis() - timestamp <= TIME_DIFF) {
						AuthUserDto dto = new AuthUserDto();
						dto.setUserId(userId);
						result.setData(dto);
						result.setResult(AuthResultEnum.RESULT_AUTH_SUCCESS);
					} else {
						result.setResult(AuthResultEnum.RESULT_AUTH_TOKEN_EXPIRED);
					}
				}
			}
		} catch (AuthRuntimeException e) {
			result.setResult(AuthResultEnum.RESULT_AUTH_FAIL);
		}

		return result;
	}
}
