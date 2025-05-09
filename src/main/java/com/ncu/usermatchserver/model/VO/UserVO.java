package com.ncu.usermatchserver.model.VO;


import lombok.Data;

import java.util.Date;

/**
 * 用户vo类
 * @author winter
 */
@Data
public class UserVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String username;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户权限 0-普通用户，1-管理员
     */
    private Integer userRole;

    /**
     * 标签列表
     */
    private String tags;

    /**
     * 用户状态 0-正常，1-异常
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private Date createTime;
}
