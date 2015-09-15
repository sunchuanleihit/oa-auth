package com.serverstarted.user.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.loukou.auth.service.TokenService;
import com.serverstarted.user.AbstractTestObject;

public class TokenServiceTest extends AbstractTestObject{

	@Autowired
	private TokenService tokenService;
	
	
	@Test
	public void generateToken() {
		String name = "chqlb";
		String token = tokenService.generateToken(name);
		
		System.out.println(token);
	}
}
