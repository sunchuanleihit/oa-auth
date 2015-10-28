package com.loukou.auth.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.RoleEntity;

public interface RoleDao extends CrudRepository<RoleEntity, Integer> {

	@Query("select r from RoleEntity r where r.id in (?1) ")
	List<RoleEntity> findByIds(List<Integer> ids);
	
	List<RoleEntity> findAll();
	
	RoleEntity findById(int id);
	
	RoleEntity findByRoleKey(String roleKey);
	
	List<RoleEntity> findByAppId(int appId);
	
	@Query("select r.id from RoleEntity r where r.appId in (?1) ")
	List<Integer> findIdsByAppId(int appId);
	
	@Query("select r.id from RoleEntity r ")
	List<Integer> findIdsByAll();
	
	@Query("select r.appId from RoleEntity r where r.id in (?1) ")
	List<Integer> findAppIdById(List<Integer> ids);
	
	
}
