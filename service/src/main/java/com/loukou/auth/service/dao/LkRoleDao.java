package com.loukou.auth.service.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.LkRoleEntity;

public interface LkRoleDao extends CrudRepository<LkRoleEntity, Integer>, JpaSpecificationExecutor<LkRoleEntity>  {

	List<LkRoleEntity> findByIsUseOrderByCreateTimeDesc(int isUse);
	
	LkRoleEntity findByRoleNameAndIsUse(String roleName,int isUse);
	
	LkRoleEntity findByRoleName(String roleName);

	@Query("update RoleEntity set isUse=?2 where roleId=?1")
	@Modifying
	int closeRoleByRoleId(int roleId,int isUse);

	List<LkRoleEntity> findByRoleIdInAndIsUse(List<Integer> roleIds,int isUse);

	Page<LkRoleEntity> findByRoleIdNotInAndIsUse(List<Integer> roleIds,int isUse,Pageable pageRequest);

}
