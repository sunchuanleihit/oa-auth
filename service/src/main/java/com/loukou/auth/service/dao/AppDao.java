package com.loukou.auth.service.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.AppEntity;

public interface AppDao extends CrudRepository<AppEntity, Integer> {
	
	List<AppEntity> findAll();

	AppEntity findById(int appId);
	
	AppEntity findByIdAndStatus(int id, int status);
	
}
