package com.afanty.base.test.system.codetype.web;

/**
 * 字典分类专用响应编码
 *
 * @Author yejx
 * @Date 2022/1/17
 */
public enum CodeTypeResponseCode {

    CODE_6001(6001, "请求参数不能为空"),
    CODE_6002(6002, "该类型编码已存在"),
    CODE_6003(6003, "该类型名称已存在");

    private final Integer key;
    private final String desc;

    private CodeTypeResponseCode(Integer key, String desc) {
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
