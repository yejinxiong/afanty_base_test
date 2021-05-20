package com.afanty.base.test.lamda.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 服务补救工单主表:保存活动的服务补救单相关信息，预设5个备用字段，自动归档结束后超过7天的数据将删除，到时可以在历史表查
 * </p>
 *
 * @author yanjia
 * @since 2018-08-13
 */
@Data
public class RemedySheet implements Serializable {

    /**
     * 主键
     */
    private String remedyId;

    /**
     * 服务补救产品主键
     */
    private String productId;

    /**
     * 补救单流水号
     */
    private String remedyCode;

    /**
     * 处理人
     */
    private String handleUser;

    public RemedySheet(String remedyId, String productId, String remedyCode) {
        this.remedyId = remedyId;
        this.productId = productId;
        this.remedyCode = remedyCode;
    }
}
