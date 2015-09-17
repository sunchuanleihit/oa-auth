package com.loukou.auth.service.impl;

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
}
