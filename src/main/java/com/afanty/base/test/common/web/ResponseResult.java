package com.afanty.base.test.common.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 服务结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 2096725332851056995L;

    /**
     * 是否成功
     */
    public Boolean success;

    /**
     * 返回状态码
     */
    public Integer status;

    /**
     * 返回信息
     */
    public String msg;

    /**
     * 返回数据
     */
    public T data;

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> ResponseResult<T> success() {
        return ResponseResult.success("操作成功");
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static <T> ResponseResult<T> success(String msg) {
        return ResponseResult.success(msg, null);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> ResponseResult<T> success(String msg, T data) {
        return new ResponseResult<>(MsgCode.DEFAULT.getKey(), StatusCode.DEFAULT.getKey(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static <T> ResponseResult<T> error() {
        return ResponseResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> error(String msg) {
        return ResponseResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param data 返回内容
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> error(T data) {
        return ResponseResult.error(StatusCode.CODE_3000.getDesc(), data);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> error(String msg, T data) {
        return new ResponseResult<>(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param status 状态码
     * @param msg    返回内容
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> error(Integer status, String msg) {
        return new ResponseResult<>(MsgCode.FAILURE.getKey(), status, msg, null);
    }

    /**
     * 返回消息
     *
     * @param num 影响行数
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> auto(int num) {
        return num > 0 ? ResponseResult.success() : ResponseResult.error();
    }

    /**
     * 返回消息
     *
     * @param num  影响行数
     * @param data 结果数据
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> auto(int num, T data) {
        return num > 0 ? ResponseResult.success(data) : ResponseResult.error(data);
    }

    /**
     * 返回消息
     *
     * @param flag 是否成功
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> auto(boolean flag) {
        return flag ? ResponseResult.success() : ResponseResult.error();
    }

    /**
     * 返回消息
     *
     * @param flag 是否成功
     * @param data 结果数据
     * @return ResponseResult
     */
    public static <T> ResponseResult<T> auto(boolean flag, T data) {
        return flag ? ResponseResult.success(data) : ResponseResult.error(data);
    }
}
