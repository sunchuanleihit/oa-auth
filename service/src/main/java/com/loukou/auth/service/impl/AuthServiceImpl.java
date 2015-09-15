package com.loukou.auth.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.jetty.util.security.Credential.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.loukou.auth.api.AuthService;
import com.loukou.auth.enums.AuthResultEnum;
import com.loukou.auth.resp.dto.AuthedUserDto;
import com.loukou.auth.resp.dto.RespDto;
import com.loukou.auth.service.dao.LkAdminDao;
import com.loukou.auth.service.dao.LkAdminRoleDao;
import com.loukou.auth.service.entity.LkAdminEntity;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private LkAdminDao adminDao;
	
	@Autowired
	private LkAdminRoleDao adminRoleDao;
	

	@Override
	public RespDto<AuthedUserDto> getUserByNameAndPassword(String userName, String password) {
		RespDto<AuthedUserDto> resp = new RespDto<AuthedUserDto>();
				
		LkAdminEntity user = adminDao.findByUserName(userName);
		if(user == null){
			resp.setResult(AuthResultEnum.RESULT_AUTH_USER_NOT_FOUND);
			return resp;
		}
		if(user.getIsUse() == 1){
			//该用户已停用
			resp.setResult(AuthResultEnum.RESULT_AUTH_USER_EXPIRED);
			return resp;
		}
		String md5Caled =  DigestUtils.md5Hex(password);
		if(!StringUtils.equals(user.getUserPassword(), md5Caled)){
			resp.setResult(AuthResultEnum.RESULT_AUTH_WRONG_PASSWORD);
			return resp;
		}
		AuthedUserDto authUser = new AuthedUserDto();
		authUser.setUserId(user.getUserId());
		resp.setData(authUser);
		
		return resp;
	}

}
