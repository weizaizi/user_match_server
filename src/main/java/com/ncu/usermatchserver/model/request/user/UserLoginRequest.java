package com.ncu.usermatchserver.model.request.user;


import lombok.Data;

/**
 * 用户登录信息
 * @author winter
 */
@Data
public class UserLoginRequest {
    String userAccount;
    String password;
}
