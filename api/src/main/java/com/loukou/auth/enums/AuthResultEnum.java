package com.loukou.auth.enums;

public enum AuthResultEnum {

	RESULT_AUTH_SUCCESS(0, "success"), RESULT_AUTH_FAIL(1, "fail"), RESULT_AUTH_TOKEN_EXPIRED(
			2, "Token expired");

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
		return RESULT_AUTH_FAIL;
	}

}
