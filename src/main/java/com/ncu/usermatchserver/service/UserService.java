package com.ncu.usermatchserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ncu.usermatchserver.model.VO.UserVO;
import com.ncu.usermatchserver.model.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
* @author winter
* @description 针对表【user】的数据库操作Service
* @createDate 2025-05-05 15:33:21
*/
public interface UserService extends IService<User> {

    Long register(String userAccount, String password, String checkPassword);

    User login(String userAccount, String password , HttpServletRequest request);

    UserVO getSafyUser(User user);
}
