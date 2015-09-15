package com.serverstarted.user.service.impl;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.loukou.auth.resp.dto.AdminDetailRespDto;
import com.loukou.auth.resp.dto.AdminSimpleRespDto;
import com.loukou.auth.resp.dto.RespDto;
import com.loukou.auth.service.impl.AdminServiceImpl;
import com.serverstarted.user.AbstractTestObject;
import com.serverstarted.user.service.util.JsonUtil;

public class AdminServiceImplTest extends AbstractTestObject {

	@Autowired
	private AdminServiceImpl service;
	
	@Test
	public void getSimpleAdmins() throws JsonGenerationException, JsonMappingException, IOException {
		RespDto<List<AdminSimpleRespDto>> resultDto = service.getSimpleAdmins(18017, 1, 10);
		String res = JsonUtil.objToJsonString(resultDto);
		System.out.println("First Page");
		System.out.println(res);
		resultDto = service.getSimpleAdmins(18017, 2, 10);
		res = JsonUtil.objToJsonString(resultDto);
		System.out.println("Second Page");
		System.out.println(res);
	}
	
	@Test
	public void getDetailAdmin() {
		RespDto<AdminDetailRespDto> resDto = service.getDetailAdmin(1);
		String res = JsonUtil.objToJsonString(resDto);
		System.out.println("Detail Info for admin 1");
		System.out.println(res);
		
	}
	
	
}
