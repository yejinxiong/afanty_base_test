package com.afanty.base.test.common.tree;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树节点
 *
 * @Author yejx
 * @Date 2022/3/13
 */
@Data
@ApiModel(value = "TreeNode对象", description = "树节点")
public class TreeNode implements Serializable {

    private static final long serialVersionUID = 5682471339359603543L;

    @ApiModelProperty("树节点ID")
    private String key;

    @ApiModelProperty("树节点名称")
    private String label;

    @ApiModelProperty("子节点集合")
    private List<TreeNode> children = new ArrayList<>();

}
