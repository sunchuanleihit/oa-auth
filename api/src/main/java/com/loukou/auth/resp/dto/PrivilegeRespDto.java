package com.loukou.auth.resp.dto;

import java.io.Serializable;

public class PrivilegeRespDto  implements Serializable{
	
	private static final long serialVersionUID = 3367113565333271423L;
	private int id;	
	private String name;	
	private String privKey;
	private String type;
	private String appKey;
	
	
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
	public String getPrivKey() {
		return privKey;
	}
	public void setPrivKey(String privKey) {
		this.privKey = privKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
}
