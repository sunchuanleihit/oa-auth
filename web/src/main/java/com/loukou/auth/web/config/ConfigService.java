package com.loukou.auth.web.config;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class ConfigService {
        private static List<ParentTab> tabs = new ArrayList<ParentTab>();
        static {
            tabs.add(new ParentTab("module.product","类目管理",Lists.newArrayList(
            		new SubTab("后台类目", "/api/v1/category/index")
                          )));
            tabs.add(new ParentTab("module.product","品牌管理",Lists.newArrayList(
            		new SubTab("品牌管理", "/api/v1/brand/index")
                          )));

            tabs.add(new ParentTab("module.product","产品管理",Lists.newArrayList(
            		new SubTab("产品列表", "/api/v1/product/index"),
            		new SubTab("产品添加", "/api/v1/product/spu_category_add")
                          )));


            tabs.add(new ParentTab("module.sku","商品库",Lists.newArrayList(

            		new SubTab("商品列表", "/api/v1/sku/index"),
            		new SubTab("添加商品", "/api/v1/sku/add_city")

                          )));
            tabs.add(new ParentTab("module.sku","权限管理",Lists.newArrayList(

            		new SubTab("权限查看", "/desk/index")
                          )));
            tabs.add(new ParentTab("module.address","地址库管理",Lists.newArrayList(
            		new SubTab("地址库查询", "/api/v1/address/poi/index"),
            		new SubTab("新增地址", "/api/v1/address/poi/add")
                          )));
            tabs.add(new ParentTab("module.operation","运营管理",Lists.newArrayList(
            		new SubTab("首页运营查询", "/api/v1/operation/index/index"),
            		new SubTab("新增首页运营模块", "/api/v1/operation/index/add")
                          )));
            tabs.add(new ParentTab("module.offPriceGoods","特价管理",Lists.newArrayList(
            		new SubTab("特价商品查询", "/api/v1/off/price/goods/index"),
            		new SubTab("新增特价商品", "/api/v1/off/price/goods/add")
                          )));
            tabs.add(new ParentTab("module.coupon","优惠券管理",Lists.newArrayList(
            		new SubTab("类别列表", "/api/v1/coupon/typeList"),
            		new SubTab("规则列表", "/api/v1/coupon/ruleList"),
            		new SubTab("券码列表", "/api/v1/coupon/coupList")
                    )));
        }
        public static List<ParentTab> getConfigTabs(){
            return tabs;
        }
}
