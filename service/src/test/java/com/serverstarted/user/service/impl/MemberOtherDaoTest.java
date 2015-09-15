package com.serverstarted.user.service.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.loukou.auth.service.dao.MemberOtherDao;
import com.serverstarted.user.AbstractTestObject;

public class MemberOtherDaoTest extends AbstractTestObject{
	@Autowired
	private MemberOtherDao memberOtherDao;
	@Test
	public void testUpdateAccessToken() {
		int userId = 525702;
		String accessToken = "ssssssssssssssss";
		int ret = memberOtherDao.updateAccessToken(userId, accessToken);
	}

	@Test
	public void testUpdateAccessTokenAndUnionId() {
		int userId = 525702;
		String accessToken = "ssssssssssssssss1";
		String unionId = "xxxxxxxxxxxxxxxxx";
		int ret = memberOtherDao.updateAccessTokenAndUnionId(userId, accessToken, unionId);
	}

}
