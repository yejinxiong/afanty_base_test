package com.afanty.base.test.business.items.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 评分项表存的个省自定义的评分项数据，	读写，	数据量：< 10000条
 * </p>
 *
 * @author yejx
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_qm_items")
@ApiModel(value = "Items对象", description = "评分项表存的个省自定义的评分项数据，	读写，	数据量：< 10000条")
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评分项id
     */
    @ApiModelProperty(value = "评分项id")
    @TableId(value = "items_id", type = IdType.ASSIGN_UUID)
    private String itemsId;

    /**
     * 评分模板id
     */
    @ApiModelProperty(value = "评分模板id")
    private String standardId;

    /**
     * 评分项名称
     */
    @ApiModelProperty(value = "评分项名称")
    private String itemsName;

    /**
     * 评分项分值
     */
    @ApiModelProperty(value = "评分项分值", example = "5.5")
    private BigDecimal itemsScore;

    /**
     * 评分类型：0-加分项  1-减分项
     */
    @ApiModelProperty(value = "评分类型：0-加分项  1-减分项", example = "0")
    private Integer itemsType;

    /**
     * 是否已被用作评分：0-未使用 1-已使用
     */
    @ApiModelProperty(value = "是否已被用作评分：0-未使用 1-已使用", example = "0")
    private Integer isUse;

    /**
     * 是否删除：0-否 1-是
     */
    @ApiModelProperty(value = "是否删除：0-否 1-是", example = "0")
    @TableLogic
    private Integer isDelete;

    /**
     * 备用字段1
     */
    @ApiModelProperty(value = "备用字段1")
    private String field1;

    /**
     * 备用字段2
     */
    @ApiModelProperty(value = "备用字段2")
    private String field2;

    /**
     * 备用字段3
     */
    @ApiModelProperty(value = "备用字段3")
    private String field3;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    /**
     * 省份ID
     */
    @ApiModelProperty(value = "省份ID")
    private String proId;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private String orgId;

    /**
     * 创建人账号
     */
    @ApiModelProperty(value = "创建人账号")
    private String createUser;

    /**
     * 创建人姓名
     */
    @ApiModelProperty(value = "创建人姓名")
    private String createName;

    /**
     * 创建时间
     * 如果有LocalDateFormatConfig，则不用加@JsonFormat和@DateTimeFormat
     */
    @ApiModelProperty(value = "创建时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8") // 返回格式
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 入库格式
//    private Date createTime;
    private LocalDateTime createTime;

    /**
     * 修改人账号
     */
    @ApiModelProperty(value = "修改人账号")
    private String updateUser;

    /**
     * 修改人姓名
     */
    @ApiModelProperty(value = "修改人姓名")
    private String updateName;

    /**
     * 修改时间
     * 如果有LocalDateFormatConfig，则不用加@JsonFormat和@DateTimeFormat
     */
    @ApiModelProperty(value = "修改时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8") // 返回格式
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 入库格式
//    private Date updateTime;
    private LocalDateTime updateTime;

}
