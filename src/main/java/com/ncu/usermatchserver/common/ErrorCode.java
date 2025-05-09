package com.ncu.usermatchserver.common;

import lombok.Getter;

/**
 * 自定义错误码
 * @author winter
 */

@Getter
public enum ErrorCode {
    SUCCESS(20000, "ok", ""),
    PARAMS_ERROR(40000, "请求参数错误" , ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    SYSTEM_ERROR(50000 , "系统错误" , "");


    private final int code;

    private final String massage;

    private final String description;

    ErrorCode(int code, String massage, String description) {
        this.code = code;
        this.massage = massage;
        this.description = description;
    }

}
