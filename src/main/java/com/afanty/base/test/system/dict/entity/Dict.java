package com.afanty.base.test.system.dict.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author yejx
 * @since 2022-01-17 22:57:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tbl_sys_dict")
@ApiModel(value = "Dict对象", description = "字典表")
public class Dict extends Model<Dict> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("字典名称")
    @TableField("dict_name")
    private String dictName;

    @ApiModelProperty("字典值")
    @TableField("dict_value")
    private String dictValue;

    @ApiModelProperty("类型编码")
    @TableField("type_code")
    private String typeCode;

    @ApiModelProperty("关键词")
    @TableField("dict_keyword")
    private String dictKeyword;

    @ApiModelProperty("拼音码")
    @TableField("pinyin_code")
    private String pinyinCode;

    @ApiModelProperty("排序")
    @TableField("sort_no")
    private Integer sortNo;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("是否删除：1-是，0-否")
    @TableField("delete_flag")
    @TableLogic
    private Integer deleteFlag;

    @ApiModelProperty("是否启用：1-是（默认），0-否")
    @TableField("enable_flag")
    private Integer enableFlag;

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
