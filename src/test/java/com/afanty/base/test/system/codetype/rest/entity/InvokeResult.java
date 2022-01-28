package com.afanty.base.test.system.codetype.rest.entity;

import java.io.Serializable;

/**
 * @Author yejx
 * @Date 2022/1/28
 */
public class InvokeResult implements Serializable {

    private static final long serialVersionUID = -3165649641476228759L;

    private Boolean isSuccess;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return "调用成功";
    }
}
