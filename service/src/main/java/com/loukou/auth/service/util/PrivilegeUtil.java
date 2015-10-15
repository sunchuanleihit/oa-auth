package com.loukou.auth.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.loukou.auth.enums.PrivilegesEnum;

public class PrivilegeUtil {
	
	public static Map<String, PrivilegesEnum> privilegeMap = new HashMap<String, PrivilegesEnum>();
	
	static {
		for (PrivilegesEnum e : PrivilegesEnum.values()) {
			privilegeMap.put(e.getPrivKey(), e);
		}
	}

	
	public static List<String> parsePrivKeys(String privilegeStr) {
		List<String> privileges = new ArrayList<String>();
		if (StringUtils.isNotBlank(privilegeStr)) {
			String[] privilegeListTemp = privilegeStr.split("\n");
			if (privilegeListTemp.length > 0) {
				privileges = java.util.Arrays.asList(privilegeListTemp);
			}
		}
		return privileges;
	}
	
	public static Map<String, PrivilegesEnum> getPrivilegeMap() {
		return privilegeMap;
	}
	
	
	

}
