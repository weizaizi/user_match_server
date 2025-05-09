package com.ncu.usermatchserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ncu.usermatchserver.model.VO.UserVO;
import com.ncu.usermatchserver.model.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author winter
 * @description 针对表【user】的数据库操作Service
 * @createDate 2025-05-05 15:33:21
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param password      用户密码
     * @param checkPassword 用户校验密码
     * @return 用户id
     */
    Long register(String userAccount, String password, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount 用户账号
     * @param password    用户密码
     * @param request     请求信息
     * @return 用户信息
     */
    UserVO login(String userAccount, String password, HttpServletRequest request);

    /**
     * 返回安全的用户信息
     *
     * @param user 用户信息
     * @return 脱敏的用户信息
     */
    UserVO getSafeUser(User user);

    /**
     * 通过标签名称搜索对应的用户
     * @param tags 标签名称
     * @return 脱敏用户信息
     */
    List<UserVO> searchTeamUserByTags(List<String> tags);
}
