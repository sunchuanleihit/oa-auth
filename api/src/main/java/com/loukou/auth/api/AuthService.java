package com.loukou.auth.api;

import com.loukou.auth.resp.dto.AuthedUserDto;
import com.loukou.auth.resp.dto.RespDto;


public interface AuthService {
   
   /**
    * 验证用户名密码
    * */
   public RespDto<AuthedUserDto> getUserByNameAndPassword(String userName,String password);
   
}
