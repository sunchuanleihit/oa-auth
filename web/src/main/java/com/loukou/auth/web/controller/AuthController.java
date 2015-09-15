package com.loukou.auth.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController extends BaseController{
    
	/**
	 * 展示login页面
     * @param redir 成功登录后跳转页面
	 * @return
	 */
    @RequestMapping(value = "/login",method= RequestMethod.GET)
    public String toLogin(String redir){
        return "login";
    }
    
    /**
     * 提交登录页面
     * 成功时记录cookie
     * 失败时留着本页面
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/login",method= RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password, 
    		@RequestParam String redir){
       
        return "redirect:redir";
    }
    
    /**
     * 退出登录
     * 需要身份验证
     * 默认重定向到登录页
     * @return
     */
    @RequestMapping(value="/logout")
    public String logout(){
        return "redirect:/login";
    }
}
