package com.loukou.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
}
