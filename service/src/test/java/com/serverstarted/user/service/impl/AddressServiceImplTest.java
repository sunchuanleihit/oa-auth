package com.serverstarted.user.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.loukou.auth.req.dto.AddressReqDto;
import com.loukou.auth.resp.dto.AddressGetSiteMsgRespDto;
import com.loukou.auth.resp.dto.AddressListRespDto;
import com.loukou.auth.resp.dto.AddressRespDto;
import com.loukou.auth.resp.dto.AddressSuggestionListRespDto;
import com.loukou.auth.resp.dto.RespDto;
import com.loukou.auth.service.impl.AddressServiceImpl;
import com.serverstarted.user.AbstractTestObject;

public class AddressServiceImplTest extends AbstractTestObject{ 

	@Autowired
	private AddressServiceImpl service;
	@Test
	public void testGetAddressList() {
		int userId = 60094;
		RespDto<AddressListRespDto> dto = service.getAddressList(userId);
		System.out.println(dto.getDesc());
	}

	@Test
	public void testAddAddress() {
		
		AddressReqDto addressReqDto = new AddressReqDto();
		addressReqDto.setUserId(60094);
		addressReqDto.setConsignee("杨勇");
		addressReqDto.setMobile("13120532907");
		addressReqDto.setTelphone("13120532907");
		
		addressReqDto.setRegionId(481);
		addressReqDto.setRegionName("新北区");
		addressReqDto.setAddress("新北区漓");
		addressReqDto.setAddressDetail("103室");
		addressReqDto.setCityId(1);
		addressReqDto.setLatitude(31.822425);
		addressReqDto.setLongitude(119.974993);
		RespDto<AddressRespDto> dto = service.addAddress(addressReqDto );
		System.out.println(dto.getDesc());
	}

	@Test
	public void testEditAddress() {
		AddressReqDto addressReqDto = new AddressReqDto();
		addressReqDto.setAddressId(269033);
		addressReqDto.setUserId(60094);
		addressReqDto.setConsignee("杨勇1111");
		addressReqDto.setMobile("13120532907");
		addressReqDto.setTelphone("13120532907");
		addressReqDto.setRegionId(481);
		addressReqDto.setRegionName("新北区");
		addressReqDto.setAddress("新北区漓江路9号");
		addressReqDto.setAddressDetail("101室");
		addressReqDto.setCityId(1);
		addressReqDto.setLatitude(31.846421725063);
		addressReqDto.setLongitude(119.9790239832);
		RespDto<String> dto = service.editAddress(addressReqDto);
		System.out.println(dto.getDesc());
	}

	@Test
	public void testDelAddress() {
		int addressId = 269033;
		int userId = 60094;
		RespDto<String> dto = service.delAddress(addressId, userId);
		System.out.println(dto.getDesc());
	}

	@Test
	public void testGetSiteMsg() {
		RespDto<AddressGetSiteMsgRespDto> dto = service.getSiteMsg(264770, 1032752, 1);
		System.out.println(dto.getDesc()); 
	}

	@Test
	public void testAddressSuggestion() {
		RespDto<AddressSuggestionListRespDto> dto = service.addressSuggestion(1, "漓江路");
		System.out.println(dto.getDesc()); 
	}
	
	@Test
	public void testGetUserAddress(){
		int userId = 1032752;
		int cityId = 1;
		double latitude = 31.824504;
		double longitude = 119.977023;
		RespDto<AddressRespDto> dto = service.getUserAddress(latitude, longitude, userId, cityId);
		System.out.println(dto.getDesc()); 
	}

}
