package com.serverstarted.user.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.loukou.auth.req.dto.ConfigureGetTokenReqDto;
import com.loukou.auth.resp.dto.ConfigureRespDto;
import com.loukou.auth.resp.dto.RespDto;
import com.loukou.auth.service.impl.ConfigureServiceImpl;
import com.serverstarted.user.AbstractTestObject;

public class ConfigureServiceImplTest extends AbstractTestObject{

	@Autowired
	private ConfigureServiceImpl service;
	@Test
	public void testGetConfigure() {
		RespDto<ConfigureRespDto> dto = service.getConfigure();
		System.out.println(dto.getDesc());
	}

	@Test
	public void testGetToken() {
		ConfigureGetTokenReqDto req = new ConfigureGetTokenReqDto();
		req.setOpenId("sssssssssssssssss");
		req.setFromBackground(1);
		req.setRegister(1);
		req.setToken("111111111111111");
		RespDto<String> dto = service.getToken(req);
		System.out.println(dto.getDesc());
	}
}
