package com.loukou.auth.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loukou.auth.resp.dto.base.RespPureDto;
import com.loukou.auth.service.dao.AppDao;
import com.loukou.auth.service.entity.AppEntity;

@Service
public class AppService {

	@Autowired
	private AppDao appDao;

	public AppEntity getApp(int appId) {
		return appDao.findById(appId);
	}
	
	public List<AppEntity> getAllApps() {
		return (List<AppEntity>) appDao.findAll();
	}
	
	public RespPureDto saveDomainInfoById(int appId, String domainPrefix, int port) {
		AppEntity app = appDao.findById(appId);
		
		if (app == null) {
			return new RespPureDto(204, "id对应的app系统不存在！");
		}
		
		if (StringUtils.isBlank(domainPrefix)) {
			return new RespPureDto(204, "域名前缀不得为空！");
		}
		
		if (port < 0) {
			return new RespPureDto(204, "跳转端口不得为负数！");
		}
		
		app.setDomainPrefix(domainPrefix);
		app.setPort(port);
		appDao.save(app);
		
		return new RespPureDto(200, "系统设置更新成功！");
	}
	
	
}
