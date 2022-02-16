package com.afanty.base.test.system.codetype.entity;

import com.afanty.base.test.common.web.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 字典类型
 * </p>
 *
 * @author yejx
 * @since 2022-01-17 00:36:04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_sys_code_type")
@ApiModel(value = "CodeType对象", description = "字典类型")
public class CodeType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 5696227868393529662L;

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

    @ApiModelProperty("是否删除：1-是，0-否（默认）")
    @TableField("delete_flag")
    @TableLogic
    private Integer deleteFlag;

    @ApiModelProperty("是否启用：1-是（默认），0-否")
    @TableField("enable_flag")
    private Integer enableFlag;

}
