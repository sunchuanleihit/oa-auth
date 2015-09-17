package com.loukou.auth.service.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, Integer>,
		JpaSpecificationExecutor<UserEntity> {

	UserEntity findByUserNameAndPassword(String userName, String password);

	UserEntity findById(int userId);

}
