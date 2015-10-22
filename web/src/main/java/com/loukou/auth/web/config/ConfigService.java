package com.loukou.auth.web.config;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

public class ConfigService {
        private static List<ParentTab> tabs = new ArrayList<ParentTab>();
        static {
        	tabs.add(new ParentTab("module.product","用户管理",Lists.newArrayList(
        			new SubTab("所有用户", "/user/index"),
            		new SubTab("<span id='globel_system_name'></span>用户", "/user/role")
                          )));
            tabs.add(new ParentTab("module.product","角色管理",Lists.newArrayList(
            		new SubTab("角色列表", "/role/index")
                          )));
            tabs.add(new ParentTab("module.privilege","权限管理",Lists.newArrayList(
            		new SubTab("权限列表", "/privilege/index")
                          )));
           
            tabs.add(new ParentTab("module.product","系统",Lists.newArrayList(
            		new SubTab("配置", "/app/index")
                          )));
        }
        public static List<ParentTab> getConfigTabs(){
            return tabs;
        }
}
