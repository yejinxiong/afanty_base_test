package com.afanty.base.test.business.es.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品
 * </p>
 *
 * @author yejx
 * @date 2021/5/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {

    private static final long serialVersionUID = 1438346286833736944L;

    /**
     * 商品id
     */
    @Id
    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "商品id")
    private String goodsId;

    /**
     * 商品名称
     */
    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "商品名称")
    private String title;

    /**
     * 商品图片
     */
    @Field(type = FieldType.Keyword, analyzer = "ik_smart")
    @ApiModelProperty(value = "商品图片")
    private String img;

    /**
     * 价格
     */
    @Field(type = FieldType.Double)
    @ApiModelProperty(value = "价格")
    private Double price;

    /**
     * 状态：0-在售 1-下架 2-售罄
     */
    @Field(type = FieldType.Integer)
    @ApiModelProperty(value = "状态：0-在售 1-下架 2-售罄", example = "0")
    private Integer state = 0;

    /**
     * 是否删除：0-否 1-是
     */
    @Field(type = FieldType.Integer)
    @ApiModelProperty(value = "是否删除：0-否 1-是", example = "0")
    private Integer isDelete = 0;

    /**
     * 租户ID
     */
    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    /**
     * 分公司ID
     */
    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "分公司ID")
    private String branchId;

    /**
     * 部门ID
     */
    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "部门ID")
    private String orgId;

    /**
     * 创建人账号
     */
    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "创建人账号")
    private String createUser;

    /**
     * 创建人姓名
     */
    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "创建人姓名")
    private String createName;

    /**
     * 创建时间
     * 如果有LocalDateFormatConfig，则不用加@JsonFormat和@DateTimeFormat
     */
    @Field(type = FieldType.Date, format = DateFormat.date_time)
    @ApiModelProperty(value = "创建时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // 返回格式
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 入库格式
//    private Date createTime;
    private LocalDateTime createTime = LocalDateTime.now();

    /**
     * 修改人账号
     */
    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "修改人账号")
    private String updateUser;

    /**
     * 修改人姓名
     */
    @Field(type = FieldType.Keyword)
    @ApiModelProperty(value = "修改人姓名")
    private String updateName;

    /**
     * 修改时间
     * 如果有LocalDateFormatConfig，则不用加@JsonFormat和@DateTimeFormat
     */
    @Field(type = FieldType.Date, format = DateFormat.date_time)
    @ApiModelProperty(value = "修改时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // 返回格式
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 入库格式
//    private Date updateTime;
    private LocalDateTime updateTime = LocalDateTime.now();

}
