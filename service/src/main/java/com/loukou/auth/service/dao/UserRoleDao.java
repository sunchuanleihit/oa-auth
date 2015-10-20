package com.loukou.auth.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.UserRoleEntity;

public interface UserRoleDao extends CrudRepository<UserRoleEntity, Integer> {

	List<UserRoleEntity> findByUserId(int userId);
	
	List<UserRoleEntity> findByRoleId(int roleId);
	
	@Query("select ur from UserRoleEntity ur where roleId in (?1)")
	List<UserRoleEntity> findByRoleIds(List<Integer> roleIds);
	
	@Query("select ur from UserRoleEntity ur where roleId in (?1) and userId = (?2)")
	List<UserRoleEntity> findByRoleIdsAndUserId(List<Integer> roleIds, int userId);
	
	
	
}
