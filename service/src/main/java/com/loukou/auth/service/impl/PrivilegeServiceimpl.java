package com.loukou.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.loukou.auth.resp.dto.PrivilegeRespDto;
import com.loukou.auth.resp.dto.base.ListDto;
import com.loukou.auth.resp.dto.base.RespListDto;
import com.loukou.auth.resp.dto.base.RespPageDto;
import com.loukou.auth.resp.dto.base.RespPureDto;
import com.loukou.auth.service.PrivilegeService;
import com.loukou.auth.service.constants.AppStatus;
import com.loukou.auth.service.dao.AppDao;
import com.loukou.auth.service.dao.PrivilegeDao;
import com.loukou.auth.service.entity.AppEntity;
import com.loukou.auth.service.entity.PrivilegeEntity;


@Service
public class PrivilegeServiceimpl implements PrivilegeService  {
	
	@Autowired
	PrivilegeDao privilegeDao;
	
	@Autowired
	AppDao appDao;

	@Override
	public RespPageDto<PrivilegeRespDto> getPrivilegesByAppId(int appId, int pageNum, int pageSize) {
		
		AppEntity app = appDao.findByIdAndStatus(appId, AppStatus.NORMAL);
		List<PrivilegeRespDto> privRespDtos = new ArrayList<PrivilegeRespDto>();
		
		if (app == null) {
			return new RespPageDto<PrivilegeRespDto>(0, privRespDtos);
		}
		
		if (pageNum < 1 || pageSize < 1) {
			return new RespPageDto<PrivilegeRespDto>(0, privRespDtos);
		}
		
		Pageable page = new PageRequest(pageNum - 1, pageSize);
		
		Page<PrivilegeEntity> privilegesPage = privilegeDao.findPageByAppKey(app.getAppKey(), page);
		
		List<PrivilegeEntity> privilegesList = privilegesPage.getContent();
		
		if (CollectionUtils.isEmpty(privilegesList)) {
			return new RespPageDto<PrivilegeRespDto>(0, privRespDtos);
		}
		
		for (PrivilegeEntity priv : privilegesList) {
			PrivilegeRespDto privDto = new PrivilegeRespDto();
			BeanUtils.copyProperties(priv, privDto);
			privRespDtos.add(privDto);
		}

		return new RespPageDto<PrivilegeRespDto>(privilegesPage.getTotalElements(), privRespDtos);
	}

	@Override
	public RespPureDto create(String privName, String privKey, String privType, int appId) {
		
		if (StringUtils.isEmpty(privName) || StringUtils.isEmpty(privKey)) {
			return new RespPureDto(204, "传入权限名或标识为空！");
		}
		
		AppEntity app = appDao.findByIdAndStatus(appId, AppStatus.NORMAL);
		if (app == null) {
			return new RespPureDto(204, "权限所属app为空！");
		}
		
		PrivilegeEntity priv = privilegeDao.findByPrivKeyAndAppKey(privKey, app.getAppKey());
		
		if (priv != null) {
			return new RespPureDto(204, String.format("权限标识‘%s’已存在， 无法创建！", privKey));
		}
		
		priv = new PrivilegeEntity();
		priv.setName(privName);
		priv.setType(privType);
		priv.setPrivKey(privKey);
		priv.setAppKey(app.getAppKey());
		privilegeDao.save(priv);
		
		return new RespPureDto(200, "权限创建成功！");
	}

	@Override
	public RespPureDto delete(int privId) {
		PrivilegeEntity priv = privilegeDao.findById(privId);
		
		if (priv == null) {
			return new RespPureDto(204, "id对应的权限不存在！");
		}
		
		privilegeDao.delete(priv);
		
		return new RespPureDto(200, "权限删除成功！");
	}
	
	
}
