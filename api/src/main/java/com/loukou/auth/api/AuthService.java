package com.loukou.auth.api;

import com.loukou.auth.resp.dto.AuthUserDto;
import com.loukou.auth.resp.dto.UserRespDto;
import com.loukou.auth.resp.dto.base.RespDto;
import com.loukou.auth.resp.dto.base.RespListDto;

public interface AuthService {

	public RespDto<AuthUserDto> validateToken(int appId, String token);
	
	public RespListDto<UserRespDto> getUsersByAppId(int appId);

}
