package com.loukou.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loukou.auth.api.AuthService;
import com.loukou.auth.enums.AuthResultEnum;
import com.loukou.auth.resp.dto.AuthUserDto;
import com.loukou.auth.resp.dto.UserRespDto;
import com.loukou.auth.resp.dto.base.RespDto;
import com.loukou.auth.resp.dto.base.RespListDto;
import com.loukou.auth.resp.dto.base.RespPageDto;
import com.loukou.auth.service.UserService;
import com.loukou.auth.service.bo.PrivilegeBo;
import com.loukou.auth.service.bo.UserBo;

@Service("oa-auth-service")
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserService userService;

	public RespDto<AuthUserDto> validateToken(int appId, String token) {
		RespDto<AuthUserDto> result = new RespDto<AuthUserDto>();
		result.setResult(AuthResultEnum.RESULT_AUTH_FAIL);
		UserBo user = userService.validateToken(appId, token);
		if (user != null) {
			result.setResult(AuthResultEnum.RESULT_AUTH_SUCCESS);
			AuthUserDto dto = new AuthUserDto();
			dto.setName(user.getName());
			dto.setUserId(user.getId());
			
			List<String> roles = new ArrayList<String>();
			List<String> privileges = new ArrayList<String>();
			
			if (user.getRoles() != null) {
				
				
				for (int i = 0; i < user.getRoles().size(); ++i) {
					roles.add(user.getRoles().get(i).getRole());
					for (PrivilegeBo privBo : user.getRoles().get(i).getPrivileges()) {
						privileges.add(privBo.getPrivKey());
					}
				}
				
				
			}
			
			
			dto.setRoles(roles);
			dto.setPrivileges(privileges);
			
			result.setData(dto);
		}

		return result;
	}

	@Override
	public RespListDto<UserRespDto> getUsersByAppId(int appId) {
		RespPageDto<UserBo> userPage = userService.getUsersWithRole(appId, 1, 1000);
		List<UserRespDto> userList = new ArrayList<UserRespDto>();
		List<UserBo> userBoList = userPage.getRows();
		for(UserBo userBo: userBoList){
			UserRespDto authUser = new UserRespDto();
			BeanUtils.copyProperties(userBo, authUser);
			userList.add(authUser);
		}
		RespListDto<UserRespDto> respListDto = new RespListDto<UserRespDto>(200, "");
		respListDto.getResult().setList(userList);
		return respListDto;
	}
}
