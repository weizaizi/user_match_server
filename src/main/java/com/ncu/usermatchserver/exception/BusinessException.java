package com.ncu.usermatchserver.exception;

import com.ncu.usermatchserver.common.ErrorCode;
import lombok.Getter;

/**
 * 业务异常类
 * @author winter
 */
@Getter
public class BusinessException extends RuntimeException {
    private final int code;
    private final String massage;
    private final String description;

    public BusinessException(String message, int code, String massage, String description) {
        super(message);
        this.code = code;
        this.massage = massage;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMassage());
        this.code = errorCode.getCode();
        this.massage = errorCode.getMassage();
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMassage());
        this.code = errorCode.getCode();
        this.massage = errorCode.getMassage();
        this.description = "";
    }

}
