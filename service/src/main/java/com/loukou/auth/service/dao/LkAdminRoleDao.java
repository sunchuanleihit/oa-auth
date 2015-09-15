package com.loukou.auth.service.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.loukou.auth.service.entity.LkAdminRoleEntity;

public interface LkAdminRoleDao extends CrudRepository<LkAdminRoleEntity, Integer>{

	List<LkAdminRoleEntity> findByRoleId(int roleId);
	
	List<LkAdminRoleEntity> findByUserId(int userId);

	@Modifying
	@Transactional
	@Query("delete LkAdminRoleEntity where userId = ?1 and roleId = ?2")
	int delUserRole(int userId,int roleId);
}
