package com.ncu.usermatchserver.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ncu.usermatchserver.common.BaseResponse;
import com.ncu.usermatchserver.common.ErrorCode;
import com.ncu.usermatchserver.common.ResultUtils;
import com.ncu.usermatchserver.exception.BusinessException;
import com.ncu.usermatchserver.exception.GlobalExceptionHandler;
import com.ncu.usermatchserver.model.VO.UserVO;
import com.ncu.usermatchserver.model.domain.User;
import com.ncu.usermatchserver.model.request.user.UserLoginRequest;
import com.ncu.usermatchserver.model.request.user.UserRegisterRequest;
import com.ncu.usermatchserver.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.ncu.usermatchserver.contant.UserConstant.USER_LOGIN_STATUS;

/**
 * 用户controller类
 *
 * @author winter
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private GlobalExceptionHandler globalExceptionHandler;

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册信息
     * @return 用户id
     */

    @PostMapping("register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        Long result = userService.register(userAccount, password, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录信息
     * @param request          请求信息
     * @return 用户
     */

    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        System.out.println(globalExceptionHandler);
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }

        String userAccount = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getPassword();

        User result = userService.login(userAccount, password, request);
        return ResultUtils.success(result);
    }

    /**
     * 查询用户
     *
     * @param username 查询的用户名
     * @param request  请求信息
     * @return 用户集合
     */
    @GetMapping("search")
    public BaseResponse<List<UserVO>> searchUser(String username, HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        if (isNotAdmin(request)) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            userLambdaQueryWrapper.like(User::getUsername, username);
        }

        List<User> list = userService.list(userLambdaQueryWrapper);
        List<UserVO> result = list.stream().map(user -> userService.getSafyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(result);
    }

    @GetMapping("/delete")
    public BaseResponse<Boolean> deleteUSer(Long id, HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (isNotAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }

        boolean result = userService.removeById(id);
        return ResultUtils.success(result);

    }

    /**
     * 判断是否为管理员
     *
     * @param request 请求信息
     * @return true-不是   false-是
     */
    private boolean isNotAdmin(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATUS);
        if (user == null) {
            return true;
        }

        return user.getUserRole() == 0;
    }

    /**
     * 返回当前用户信息
     *
     * @param request 请求信息
     * @return 用户信息
     */
    @GetMapping("/currentUser")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        User result = (User) request.getSession().getAttribute(USER_LOGIN_STATUS);
        return ResultUtils.success(result);
    }

    /**
     * 用户登出
     *
     * @param request 请求信息
     * @return 无返回值
     */
    @PostMapping("outLogin")
    public BaseResponse userOutLogin(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        request.getSession().removeAttribute(USER_LOGIN_STATUS);

        return ResultUtils.success();
    }

}
