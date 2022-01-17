package com.afanty.base.test.system.dict.web;

/**
 * 字典专用响应编码
 *
 * @Author yejx
 * @Date 2022/1/17
 */
public enum  DictResponseCode {

    CODE_5001(5001, "请求参数不能为空"),
    CODE_5002(5002, "字典类型不能为空"),
    CODE_5003(5003, "字典名称不能为空"),
    CODE_5004(5004, "字典值不能为空"),
    CODE_5005(5005, "排序号最大为1000"),
    CODE_5006(5006, "该字典分类不存在"),
    CODE_5007(5007, "该类型下已存在该名称的字典"),
    CODE_5008(5008, "该类型下已存在该字典值");

    private final Integer key;
    private final String desc;

    private DictResponseCode(Integer key, String desc) {
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
