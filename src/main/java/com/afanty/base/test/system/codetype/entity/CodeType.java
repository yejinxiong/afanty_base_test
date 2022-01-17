package com.afanty.base.test.system.codetype.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 字典/树类型
 * </p>
 *
 * @author yejx
 * @since 2022-01-17 00:36:04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_sys_code_type")
@ApiModel(value = "CodeType对象", description = "字典/树类型")
public class CodeType extends Model<CodeType> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("类型编码")
    @TableField("type_code")
    private String typeCode;

    @ApiModelProperty("类型名称")
    @TableField("type_name")
    private String typeName;

    @ApiModelProperty("类型描述")
    @TableField("type_desc")
    private String typeDesc;

    @ApiModelProperty("字典编码还是树形编码：1-字典，2-树形")
    @TableField("dict_or_tree")
    private Integer dictOrTree;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("是否删除：1-是，2-否")
    @TableField("delete_flag")
    @TableLogic
    private Integer deleteFlag;

    @ApiModelProperty("创建人账号")
    @TableField("create_user")
    private String createUser;

    @ApiModelProperty("创建人姓名")
    @TableField("create_name")
    private String createName;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("修改人账号")
    @TableField("update_user")
    private String updateUser;

    @ApiModelProperty("修改人姓名")
    @TableField("update_name")
    private String updateName;

    @ApiModelProperty("修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
