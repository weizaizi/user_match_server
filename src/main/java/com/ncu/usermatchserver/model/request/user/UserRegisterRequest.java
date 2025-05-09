package com.ncu.usermatchserver.model.request.user;

import lombok.Data;

/**
 * 用户注册信息
 * @author winter
 */
@Data
public class UserRegisterRequest {
    String userAccount;
    String password;
    String checkPassword;
}
