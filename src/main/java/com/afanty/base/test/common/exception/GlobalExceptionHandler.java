package com.afanty.base.test.common.exception;

import com.afanty.base.test.common.web.domain.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @Author yejx
 * @Date 2022/3/17
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常处理
     * @param e 自定义异常
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResponseResult customException(CustomException e) {
        LOGGER.error(e.getMessage());
        if (null == e.getStatus()) {
            return ResponseResult.error(e.getMessage());
        }
        return ResponseResult.error(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseResult handleArithmeticException(ArithmeticException e) {
        LOGGER.error(e.getMessage());
        return ResponseResult.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        LOGGER.error(e.getMessage());
        return ResponseResult.error(e.getMessage());
    }
}
