package com.afanty.base.test.system.menu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author yejx
 * @since 2022-03-13 17:30:01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_sys_menu")
@ApiModel(value = "Menu对象", description = "菜单权限表")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单ID")
    private Long menuId;

    @ApiModelProperty("数据唯一uuid")
    @TableField("d_key")
    private String dKey;

    @ApiModelProperty("菜单名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty("父菜单ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty("显示顺序")
    @TableField("order_num")
    private Integer orderNum;

    @ApiModelProperty("路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty("组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty("路由参数")
    @TableField("query")
    private String query;

    @ApiModelProperty("是否为外链（0是 1否）")
    @TableField("frame_flag")
    private Integer frameFlag;

    @ApiModelProperty("是否缓存（0缓存 1不缓存）")
    @TableField("cache_flag")
    private Integer cacheFlag;

    @ApiModelProperty("菜单类型（M目录 C菜单 F按钮）")
    @TableField("menu_type")
    private String menuType;

    @ApiModelProperty("是否隐藏（0显示 1隐藏）")
    @TableField("visible_flag")
    private Integer visibleFlag;

    @ApiModelProperty("菜单状态（0正常 1停用 2删除）")
    @TableField("state")
    @TableLogic
    private Integer state;

    @ApiModelProperty("权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty("菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("创建者")
    @TableField("create_by")
    private Long createBy;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新者")
    @TableField("update_by")
    private Long updateBy;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 子菜单
     */
    private List<Menu> children = new ArrayList<>();


    @Override
    public Serializable pkVal() {
        return this.menuId;
    }

}
