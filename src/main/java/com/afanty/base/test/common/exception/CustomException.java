package com.afanty.base.test.common.exception;

/**
 * 自定义异常类
 *
 * @Author yejx
 * @Date 2022/3/17
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 4812674856492234678L;

    /**
     * 返回状态码
     */
    private Integer status;

    /**
     * 返回信息
     */
    private String message;

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
