package com.serverstarted.user.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.loukou.auth.req.dto.UpdatePasswordReqDto;
import com.loukou.auth.resp.dto.RespDto;
import com.loukou.auth.resp.dto.UserAccountRespDto;
import com.loukou.auth.resp.dto.UserInfoRespDto;
import com.loukou.auth.service.dao.MemberOtherDao;
import com.loukou.auth.service.impl.AuthServiceImpl;
import com.serverstarted.user.AbstractTestObject;

public class UserServiceTest extends AbstractTestObject {

	@Autowired
	private AuthServiceImpl userServiceImpl;
	
	@Autowired
    private MemberOtherDao memberOtherDao;
	@Test
	public void testOmPassword() {
		int userId = 1032752;
		String oldPassword = "123456a";
		String newPassword = "123456b";
		UpdatePasswordReqDto updatePasswordReqDto = new UpdatePasswordReqDto();
		updatePasswordReqDto.setUserId(userId);
		updatePasswordReqDto.setNewpassword(newPassword);
		updatePasswordReqDto.setOldpassword(oldPassword);
		RespDto<String> dto = userServiceImpl.omPassword(updatePasswordReqDto);
		System.out.println(dto.getDesc());
	}

	@Test
	public void testGetUserInfoByUserId() {
		int userId = 60094;
		 UserInfoRespDto dto = userServiceImpl.getUserInfoByUserId(userId);
		 System.out.println(dto.getEncryptUserID());
	}

	@Test
	public void testGetUserAccountByUserId() {
		int userId = 60094;
		UserAccountRespDto dto = userServiceImpl.getUserAccountByUserId(userId);
		System.out.println(dto.getVcount());
	}

	@Test
	public void testThirdPartyLogin() {
	}
}
