package com.loukou.auth.api;

import com.loukou.auth.resp.dto.AuthUserDto;
import com.loukou.auth.resp.dto.RespDto;

public interface AuthService {

	public RespDto<AuthUserDto> validateToken(int appId, String token);

}
