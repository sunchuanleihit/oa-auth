package com.loukou.auth.resp.dto.base;

import java.io.Serializable;
import java.util.ArrayList;

public class RespListDto<T> implements Serializable  {



	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1954944898182863963L;
	private int code;
	private String errorMsg;
	private ListDto<T> result;
	
	public RespListDto(int code, String errorMsg) {
		this.code = code;
		this.errorMsg = errorMsg;
		this.result = new ListDto<T>("empty", new ArrayList<T>());
	}
	
	public RespListDto(int code, String errorMsg, ListDto<T> listDto) {
		this.code = code;
		this.errorMsg = errorMsg;
		this.result = listDto;
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
	public ListDto<T> getResult() {
		return result;
	}
	public void setResult(ListDto<T> result) {
		this.result = result;
	}
}
