package com.afanty.base.test.utils;

import lombok.Data;

/**
 * 服务结果
 */
@Data
public class ResponseResult {
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
    public Object data;

    public ResponseResult() {
        this.success = MsgCode.DEFAULT.getKey();
        this.status = StatusCode.DEFAULT.getKey();
        this.msg = MsgCode.DEFAULT.getDesc();
    }

    /**
     * 适用于增删改
     *
     * @param success
     * @param status
     * @param msg
     */
    public ResponseResult(Boolean success, Integer status, String msg) {
        this.success = success;
        this.status = status;
        this.msg = msg;
    }

    /**
     * 适用于查询
     *
     * @param success
     * @param status
     * @param msg
     * @param data
     */
    public ResponseResult(Boolean success, Integer status, String msg, Object data) {
        this.success = success;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
