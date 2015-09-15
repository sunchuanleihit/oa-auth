package com.loukou.auth.service.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.LkAdminEntity;

public interface LkAdminDao extends CrudRepository<LkAdminEntity, Integer>, JpaSpecificationExecutor<LkAdminEntity> {
	/**
	 *查询所有用户 
	 **/
	@Query("select c from LkAdminEntity c")
	List<LkAdminEntity> getAllUser();

	/*	@Query("SELECT c FROM LkAdminEntity c where c.userName like :uc% and c.userRealname like %:un% and c.userType = ?3")*/
	Page<LkAdminEntity> findByUserCodeLikeAndUserNameLikeAndIsUseAndUserIdIn(String userCode, String userName, int isUse,Iterable<Integer> userIds,Pageable pageRequest);
	
	/*@Query("SELECT c FROM LkAdminEntity c where c.userName like :uc% and c.userRealname like %:un%")*/
	Page<LkAdminEntity> findByUserCodeLikeAndUserNameLikeAndUserIdIn(String userCode, String userName, Iterable<Integer> userIds,Pageable pageRequest);
	
	/*@Query("SELECT c FROM LkAdminEntity c where c.userName like :uc% and c.userRealname like %:un% and c.userType = ?3")*/
	Page<LkAdminEntity> findByUserCodeLikeAndUserNameLikeAndIsUse(String userCode, String userName, int isUse,Pageable pageRequest);
	
	/*@Query("SELECT c FROM LkAdminEntity c where c.userName like :uc% and c.userRealname like %:un%")*/
	Page<LkAdminEntity> findByUserCodeLikeAndUserNameLike(String userCode, String userName,Pageable pageRequest);
	
	
	LkAdminEntity findByUserName(String userName);
	
	LkAdminEntity findByUserId(int userId);
	
	@Modifying
	@Query("update LkAdminEntity set userName = ?2,userRealname= ?3 where userId = ?1 ")
	int updateByUserId(int userId,String userName , String userRealname);
	
	@Modifying
	@Query("update LkAdminEntity set userPwd = ?2 where userId = ?1 ")
	int updateUserPwd(int userId,String userPwd);
	
	@Modifying
	@Query("update LkAdminEntity set isUse = ?2 where userId = ?1 ")
	int closeOrOpenUser(int userId,int userType);
	
	List<LkAdminEntity> findByUserIdIn(List<Integer> ids);
	
	List<LkAdminEntity> findByUserNameLike(String userName);
	
	List<LkAdminEntity> findByUserIdInAndUserNameLike(List<Integer> userId, String userName);
}
