package com.serverstarted.user.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;






import com.loukou.auth.api.DeliveryService;
import com.loukou.auth.req.dto.DeliveryReqDto;
import com.loukou.auth.resp.dto.RespDto;
import com.serverstarted.user.AbstractTestObject;
import com.serverstarted.user.service.util.JsonUtil;

public class DeliveryServiceImplTest extends AbstractTestObject {
	
	@Autowired
	private DeliveryService deliveryService;

	
	public void deleteDelivery() {
		DeliveryReqDto ddto = new DeliveryReqDto();
		ddto.setDid(17);
		ddto.setRole(0);
		ddto.setStoreId(59);
		deliveryService.deleteDelivery(ddto);
	}
	
	@Test
	public void resetDeliveryPassword() {
		DeliveryReqDto ddto = new DeliveryReqDto();
		ddto.setDid(17);
		ddto.setRole(0);
		//ddto.setStoreId(59);
		//RespDto resDto = deliveryService.resetDeliveryPassword(ddto);
		
		//String res = JsonUtil.objToJsonString(resDto);
		//System.out.println(res);
		
	}
	
	
}
