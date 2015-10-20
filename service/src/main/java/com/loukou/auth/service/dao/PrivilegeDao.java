package com.loukou.auth.service.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.loukou.auth.service.entity.PrivilegeEntity;

public interface PrivilegeDao extends CrudRepository<PrivilegeEntity, Integer> {
	
	PrivilegeEntity findById(int id);
	
	PrivilegeEntity findByPrivKey(String privKey);
	
	List<PrivilegeEntity> findByAppKey(String appKey);
	
	@Query("select p from PrivilegeEntity p where p.appKey = ?1")
	Page<PrivilegeEntity> findPageByAppKey(String appKey, Pageable page);

}
