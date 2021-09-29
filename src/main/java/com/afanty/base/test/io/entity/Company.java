package com.afanty.base.test.io.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 公司信息
 * </p>
 *
 * @author yejx
 * @date 2021/6/13
 */
@Data
public class Company implements Serializable {

    /**
     * 序列化版本好ID：保证序列化的类和反序列化的类是同一个类
     */
    private static final long serialVersionUID = -2866370533170629504L;

    /**
     * 主键id
     */
    private String comId;

    /**
     * 统一社会心用代码
     */
    private String uniformSocialCreditCode;

    /**
     * 企业名称
     */
    private String comName;

    /**
     * 法定代表人
     */
    private String legalRepresentative;

    public Company(String comId, String uniformSocialCreditCode, String comName, String legalRepresentative) {
        this.comId = comId;
        this.uniformSocialCreditCode = uniformSocialCreditCode;
        this.comName = comName;
        this.legalRepresentative = legalRepresentative;
    }
}
