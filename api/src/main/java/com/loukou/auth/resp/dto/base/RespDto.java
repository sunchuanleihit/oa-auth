package com.loukou.auth.resp.dto.base;

import java.io.Serializable;

import com.loukou.auth.enums.AuthResultEnum;

public class RespDto<T> implements Serializable {

	private static final long serialVersionUID = -7975430049347227845L;
	private int code = 0;
	private String desc = "";
	private T data;

	public RespDto() {
		this.code = AuthResultEnum.RESULT_AUTH_FAIL.getCode();
		this.desc = AuthResultEnum.RESULT_AUTH_FAIL.getDesc();
	}

	public RespDto(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public RespDto(int code, String desc, T data) {
		this.code = code;
		this.desc = desc;
		this.data = data;
	}

	public void setResult(AuthResultEnum result) {
		this.code = result.getCode();
		this.desc = result.getDesc();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
