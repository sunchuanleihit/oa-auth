package com.loukou.auth.service.dao;

import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.AppEntity;

public interface AppDao extends CrudRepository<AppEntity, Integer> {

	AppEntity findById(int appId);
	
}
