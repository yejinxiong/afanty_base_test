package com.afanty.base.test.common.web;

/**
 * <p>
 * 结果信息编码
 * </p>
 *
 * @Author yejx
 * @Date 2020/5/5
 */
public enum MsgCode {
    /**
     * 处理成功
     */
    DEFAULT(true, "处理成功"),

    /**
     * 处理失败
     */
    FAILURE(false, "处理失败");

    private final Boolean key;
    private final String desc;

    MsgCode(Boolean key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Boolean getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
