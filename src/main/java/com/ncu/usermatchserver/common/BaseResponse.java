package com.ncu.usermatchserver.common;

import lombok.Getter;

import java.io.Serializable;

/**
 * 基础返回类
 * @param <T> 返回的数据类型
 * @author winter
 */
@Getter
public class BaseResponse<T> implements Serializable {
    private final int code;

    private final T data;

    private final String massage;

    private final String description;

    public BaseResponse(int code, T data, String massage, String description) {
        this.code = code;
        this.data = data;
        this.massage = massage;
        this.description = description;
    }

    public BaseResponse(int code, T data, String massage) {
        this.code = code;
        this.data = data;
        this.massage = massage;
        this.description = "";
    }

    public BaseResponse(int code, T data) {
        this.code = code;
        this.data = data;
        this.massage = "";
        this.description = "";
    }


}
