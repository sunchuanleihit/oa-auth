package com.loukou.auth.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oa_app")
public class AppEntity {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "domain_prefix")
	private String domainPrefix;
	
	@Column(name = "port")
	private Integer port;
	
	
	@Column(name = "app_key")
	private String appKey;
	
	@Column(name = "status")
	private int status;

	

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomainPrefix() {
		return domainPrefix;
	}

	public void setDomainPrefix(String domainPrefix) {
		this.domainPrefix = domainPrefix;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	

}
