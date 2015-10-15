package com.loukou.auth.resp.dto.base;

import java.io.Serializable;

public class RespPureDto implements Serializable  {

	private static final long serialVersionUID = 1059993698513913204L;
	
	private int code = 0;
	private String errorMsg = "";
	
	
	public RespPureDto(int code, String errorMsg) {
		this.code = code;
		this.errorMsg = errorMsg;
	}
	
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
