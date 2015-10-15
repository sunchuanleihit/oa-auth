package com.loukou.auth.enums;

public enum PrivilegesEnum {
	
	OPERATION_MAIN_INDEX("operation", "a", "main.index", "查看运营后台首页"),
	OPERATION_ATTR_ADD("operation", "a","attr.add", "新建属性"),
	OPERATION_ATTR_DEL("operation", "a","attr.del", "删除属性"),
	OPERATION_BRAND_CREATE("operation", "a","brand.create", "新建品牌"),
	OPERATION_BRAND_UPDATE("operation", "a","brand.update", "更新品牌"),
	OPERATION_BRAND_DEL("operation", "a","brand.del", "删除品牌"),
	OPERATION_CATEGORY_CREATE("operation", "a","category.create", "新建类目"),
	OPERATION_CATEGORY_UPDATE("operation", "a","category.update", "更新类目"),
	OPERATION_CATEGORY_MIGRATE("operation", "a","category.migrate", "迁移类目"),
	OPERATION_CATEGORY_DEL("operation", "a","category.del", "删除类目"),
	OPERATION_SITESKU_CREATE("operation", "a","sitesku.create", "新建商品"),
	OPERATION_SITESKU_CHANGE_STATUS("operation",  "a","sitesku.changeStatus", "上架/下架商品"),
	OPERATION_SIETSKU_CHANGE_DRAFT_STATUS("operation", "a", "sitesku.changeDraftStatus", "审核商品"),
	OPERATION_SITESKU_CHANGE_PRICE("operation", "a","sitesku.changePrice", "商品改价"),
	OPERATION_SITESKU_DEL("operation", "a","sitesku.del", "删除商品"),
	OPERATION_SPU_CREATE("operation", "a","spu.create", "新建产品"),
	OPERATION_SPU_UPDATE("operation", "a","spu.update", "更新产品"),
	OPERATION_SPU_DEL("operation", "a","spu.del", "删除产品"),
	OPERATION_SPU_PASS("operation", "a", "spu.pass", "产品审核通过"),
	OPERATION_SPU_REJECT("operation", "a", "spu.reject", "产品审核驳回");
	
	private String app;
	private String privKey;
	private String desc;
	private String type;
	
	
	
	private PrivilegesEnum(String app, String type, String privKey, String desc) {
		this.app = app;
		this.privKey = privKey;
		this.desc = desc;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}


	public String getPrivKey() {
		return privKey;
	}


	public void setPrivKey(String privKey) {
		this.privKey = privKey;
	}
	

}
