package com.loukou.auth.service;

import com.loukou.auth.resp.dto.PrivilegeRespDto;
import com.loukou.auth.resp.dto.base.RespPageDto;
import com.loukou.auth.resp.dto.base.RespPureDto;

public interface PrivilegeService {
	
	public RespPageDto<PrivilegeRespDto> getPrivilegesByAppId(int appId, int pageNum, int pageSize);
	
	public RespPureDto create(String privName, String privKey, String privType, int appId);
	
	public RespPureDto delete(int privId);

}
