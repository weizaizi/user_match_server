package com.ncu.usermatchserver.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserServiceTest {

    @Resource
    public UserService userService;

    @Test
    public void registerTest() {
        String userAccount;
        String password = "123456";
        String checkPassword = "123456";

        // 非空
        userAccount = "";
        Assertions.assertEquals(-1, userService.register(userAccount, password, checkPassword));

        // 账号长度
        userAccount = "wi";
        Assertions.assertEquals(-1, userService.register(userAccount, password, checkPassword));

        //特殊字符
        userAccount = "  winter";
        Assertions.assertEquals(-1, userService.register(userAccount, password, checkPassword));

        //密码长度
        userAccount = "winter";
        password = "123";
        checkPassword = "123";
        Assertions.assertEquals(-1, userService.register(userAccount, password, checkPassword));

        //密码和验证密码不一样
        password = "123456";
        checkPassword = "12345678";
        Assertions.assertEquals(-1, userService.register(userAccount, password, checkPassword));

        //插入成功
        checkPassword = "123456";
        Assertions.assertTrue(userService.register(userAccount, password, checkPassword) > 0);

        //重复插入
        Assertions.assertEquals(-1, userService.register(userAccount, password, checkPassword));
    }

}
