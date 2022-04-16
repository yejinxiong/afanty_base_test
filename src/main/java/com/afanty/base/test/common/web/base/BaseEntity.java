package com.afanty.base.test.common.web.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 基础实体类
 *
 * @Author yejx
 * @Date 2022/2/16
 */
@Data
public class BaseEntity {

    @ApiModelProperty(value = "备注")
    @TableField(value = "remark")
    private String remark;

    @ApiModelProperty(value = "创建人账号")
    // 结合EntityInterceptor使用，自动为该字段填充值
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    @ApiModelProperty(value = "创建人姓名")
    // 结合EntityInterceptor使用，自动为该字段填充值
    @TableField(value = "create_name", fill = FieldFill.INSERT)
    private String createName;

    /**
     * 如果有LocalDateFormatConfig，则不用加@JsonFormat和@DateTimeFormat
     */
    @ApiModelProperty(value = "创建时间")
    // 结合EntityInterceptor使用，自动为该字段填充值
    @TableField(value = "create_time", fill = FieldFill.INSERT)
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8") // 返回格式
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 入库格式
//    private Date createTime;
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人账号")
    // 结合EntityInterceptor使用，自动为该字段填充值
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private String updateUser;

    @ApiModelProperty(value = "修改人姓名")
    // 结合EntityInterceptor使用，自动为该字段填充值
    @TableField(value = "update_name", fill = FieldFill.UPDATE)
    private String updateName;

    /**
     * 如果有LocalDateFormatConfig，则不用加@JsonFormat和@DateTimeFormat
     */
    @ApiModelProperty(value = "修改时间")
    // 结合EntityInterceptor使用，自动为该字段填充值
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8") // 返回格式
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 入库格式
//    private Date updateTime;
    private LocalDateTime updateTime;
}
