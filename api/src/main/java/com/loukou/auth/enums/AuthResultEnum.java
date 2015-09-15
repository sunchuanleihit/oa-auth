package com.loukou.auth.enums;


public enum AuthResultEnum {

	RESULT_UNKNOWN(-1, "unknown"),
	RESULT_AUTH_SUCCESS(0, "success"),
	RESULT_AUTH_FAIL(1, "fail"),
	RESULT_AUTH_USER_NOT_FOUND(2, "user not found"),
	RESULT_AUTH_USER_EXPIRED(3, "user expired"),
	RESULT_AUTH_WRONG_PASSWORD(4, "wrong password");
	
	private int code;
	private String desc;
	
	private AuthResultEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static AuthResultEnum parseCode(int code) {
		for (AuthResultEnum e : AuthResultEnum.values()) {
			if (e.code == code) {
				return e;
			}
		}
		return RESULT_UNKNOWN;
	}
	
}
