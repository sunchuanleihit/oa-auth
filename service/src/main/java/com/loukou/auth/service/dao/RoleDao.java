package com.loukou.auth.service.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.RoleEntity;

public interface RoleDao extends CrudRepository<RoleEntity, Integer> {

	List<RoleEntity> findByIds(List<Integer> ids);
}
