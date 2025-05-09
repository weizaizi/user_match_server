package com.ncu.usermatchserver.exception;

import com.ncu.usermatchserver.common.BaseResponse;
import com.ncu.usermatchserver.common.ErrorCode;
import com.ncu.usermatchserver.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常捕获类
 * @author winter
 *
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e) {
        log.error("BusException:", e);
        return ResultUtils.fail(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(RuntimeException r) {
        log.error("RuntimeException:", r);
        return ResultUtils.fail(ErrorCode.SYSTEM_ERROR);
    }
}
