package com.serverstarted.user.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.loukou.auth.resp.dto.InternalAuthAccountRespDto;
import com.loukou.auth.resp.dto.RespDto;
import com.loukou.auth.service.impl.MApiServiceImpl;
import com.serverstarted.user.AbstractTestObject;

public class MApiServiceImplTest extends AbstractTestObject{

	@Autowired
	private MApiServiceImpl mApiServiceImpl;
	
	@Test
	public void testFindByAppId() {
		int appId = 12;
		RespDto<InternalAuthAccountRespDto> dto = mApiServiceImpl.findByAppId(appId);
		System.out.println(dto.getDesc());
	}

}
