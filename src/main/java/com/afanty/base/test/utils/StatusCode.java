package com.afanty.base.test.utils;

/**
 * <p>
 * 结果状态编码
 * </p>
 *
 * @Author yejx
 * @Date 2019/5/18
 */
public enum StatusCode {

    /**
     * 数据处理正确
     */
    DEFAULT(9999, "数据处理正确"),

    /**
     * 请求参数错误
     */
    CODE_1000(1000, "请求参数错误"),

    /**
     * 服务降级错误
     */
    CODE_2000(2000, "服务降级错误"),

    /**
     * 系统错误
     */
    CODE_3000(3000, "系统错误"),

    /**
     * 网络连接错误
     */
    CODE_3001(3001, "网络连接错误"),

    /**
     * 网络连接超时
     */
    CODE_3002(3002, "网络连接超时"),

    /**
     * 权限异常
     */
    CODE_4000(4000, "权限异常");

    private final Integer key;
    private final String desc;

    private StatusCode(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }

}
