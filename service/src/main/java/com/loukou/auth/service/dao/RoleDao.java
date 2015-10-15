package com.loukou.auth.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.RoleEntity;

public interface RoleDao extends CrudRepository<RoleEntity, Integer> {

	List<RoleEntity> findById(List<Integer> ids);
	
	RoleEntity findById(int id);
	
	List<RoleEntity> findByAppId(int appId);
	
	@Query("select r.appId from RoleEntity r where r.id in (?1) ")
	List<Integer> findAppIdById(List<Integer> ids);
	
	
}
