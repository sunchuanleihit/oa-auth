package com.loukou.auth.service.bo;

public class PrivilegeBo {
	
	private String app;
	private String privKey;
	private String desc;
	private int status = 0; // 0 not in use, 1 in use 
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getPrivKey() {
		return privKey;
	}
	public void setPrivKey(String privKey) {
		this.privKey = privKey;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
