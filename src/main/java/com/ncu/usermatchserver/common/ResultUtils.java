package com.ncu.usermatchserver.common;

/**
 * 返回工具类
 * @author winter
 */
public class ResultUtils {
    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(20000, null, "ok", "成功");
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(20000, data, "ok", "成功");
    }

    public static <T> BaseResponse<T> fail(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode.getCode(), null, errorCode.getMassage(), errorCode.getDescription());
    }


    public static <T> BaseResponse<T> fail(int code , String massage , String description) {
        return new BaseResponse<>(code , null , massage , description);
    }


}
