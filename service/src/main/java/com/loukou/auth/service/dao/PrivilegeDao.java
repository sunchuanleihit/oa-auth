package com.loukou.auth.service.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.PrivilegeEntity;

public interface PrivilegeDao extends CrudRepository<PrivilegeEntity, Integer> {
	
	List<PrivilegeEntity> findByAppKey(String appKey);

}
