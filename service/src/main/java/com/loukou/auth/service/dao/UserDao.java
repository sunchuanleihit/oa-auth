package com.loukou.auth.service.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, Integer>,
		JpaSpecificationExecutor<UserEntity> {

	UserEntity findByEmailAndPassword(String email, String password);

	UserEntity findById(int userId);
	
	@Query("select u from UserEntity u where id in (?1) ")
	Page<UserEntity> findByIds(List<Integer> ids, Pageable page);
	
	@Query("select u from UserEntity u where id not in (?1) ")
	Page<UserEntity> findByNotInIds(List<Integer> ids, Pageable page);
	
	Page<UserEntity> findAll(Pageable page);

}
