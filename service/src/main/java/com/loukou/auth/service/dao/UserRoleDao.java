package com.loukou.auth.service.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.UserRoleEntity;

public interface UserRoleDao extends CrudRepository<UserRoleEntity, Integer> {

	List<UserRoleEntity> findByUserIdAndAppId(int userId, int appId);
}
