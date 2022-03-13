package com.afanty.base.test.common.tree;


import com.google.common.collect.Lists;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 树工具
 *
 * @Author yejx
 * @Date 2022/3/13
 */
public class TreeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreeUtil.class);


    /**
     * 构建树
     *
     * @param dataList      原始数据集
     * @param keyName       节点取ID值所对应的字段名
     * @param parentKeyName 父节点取值对所应的字段名
     * @param labelName     节点名称取值所对应的字段名
     * @param <T>           树类型
     * @return 树
     */
    public static <T> List<TreeNode> buildTree(List<T> dataList, String keyName, String parentKeyName, String labelName) {
        Assert.notNull(dataList, "源数据不能为空！");
        Assert.notNull(keyName, "节点取ID值所对应的字段名不能为空！");
        Assert.notNull(parentKeyName, "父节点取值对所应的字段名不能为空！");
        Assert.notNull(labelName, "节点名称取值所对应的字段名不能为空！");
        List<TreeNode> treeNodeList = new ArrayList<>();
        // 获取顶级节点
        List<T> roots = getRoots(dataList, keyName, parentKeyName);
        try {
            recursionFn(dataList, roots, treeNodeList, keyName, parentKeyName, labelName);
        } catch (Exception e) {
            LOGGER.error("构建树异常：{}", e.getMessage());
            return null;
        }
        return treeNodeList;
    }

    /**
     * 获取顶级节点
     *
     * @param dataList      原始数据集
     * @param keyName       节点取ID值所对应的字段名
     * @param parentKeyName 父节点取值对所应的字段名
     * @param <T>           树类型
     * @return
     */
    public static <T> List<T> getRoots(List<T> dataList, String keyName, String parentKeyName) {
        // 获取所有的节点ID
        List<String> allKeyValues = dataList.stream().map(data -> {
            try {
                return String.valueOf(BeanUtils.getProperty(data, keyName));
            } catch (Exception e) {
                LOGGER.error("获取{}的值异常：{}", keyName, e.getMessage());
                return null;
            }
        }).collect(Collectors.toList());
        // 获取顶级节点
        List<T> roots = dataList.stream().filter(data -> {
            try {
                return !allKeyValues.contains(BeanUtils.getProperty(data, parentKeyName));
            } catch (Exception e) {
                LOGGER.error("获取{}的值异常：{}", keyName, e.getMessage());
                return false;
            }
        }).collect(Collectors.toList());
        if (null == roots) {
            roots = new ArrayList<>();
        }
        return roots;
    }

    /**
     * 递归
     *
     * @param dataList      原始数据集
     * @param children      子节点数据集
     * @param treeNodeList  最终结果集
     * @param keyName       节点取ID值所对应的字段名
     * @param parentKeyName 父节点取值对所应的字段名
     * @param labelName     节点名称取值所对应的字段名
     * @param <T>           树类型
     */
    public static <T> void recursionFn(List<T> dataList, List<T> children, List<TreeNode> treeNodeList, String keyName, String parentKeyName, String labelName) {
        try {
            for (T t : children) {
                TreeNode childnode = new TreeNode();
                // 获取当前节点的ID，作为子节点的父节点
                String parentValue = BeanUtils.getProperty(t, keyName);
                childnode.setKey(parentValue);
                childnode.setLabel(BeanUtils.getProperty(t, labelName));
                // 获取当前节点的子节点集合
                List<T> childlist = getChildren(dataList, parentKeyName, parentValue);
                // 设置子节点
                if (null != childlist && childlist.size() > 0) {
                    childnode.setChildren(setChildren(dataList, childlist, keyName, parentKeyName, labelName));
                }
                treeNodeList.add(childnode);
            }
        } catch (Exception e) {
            LOGGER.error("树递归异常：{}", e.getMessage());
        }
    }

    /**
     * 获取子节点结合
     *
     * @param dataList      原始数据集
     * @param parentKeyName 父节点取值对所应的字段名
     * @param parentValue   父节点值
     * @param <T>           树类型
     * @return
     */
    public static <T> List<T> getChildren(List<T> dataList, String parentKeyName, String parentValue) {
        List<T> children = dataList.stream().filter(data -> {
            try {
                return parentValue.equals(BeanUtils.getProperty(data, parentKeyName));
            } catch (Exception e) {
                LOGGER.error("获取{}的值异常：{}", parentKeyName, e.getMessage());
                return false;
            }
        }).collect(Collectors.toList());
        if (null == children) {
            children = new ArrayList<>();
        }
        return children;
    }


    /**
     * 设置子节点
     *
     * @param dataList      原始数据集
     * @param children      子节点数据集
     * @param keyName       节点取ID值所对应的字段名
     * @param parentKeyName 父节点取值对所应的字段名
     * @param labelName     节点名称取值所对应的字段名
     * @param <T>           树类型
     * @return 子节点树
     */
    public static <T> List<TreeNode> setChildren(List<T> dataList, List<T> children, String keyName, String parentKeyName, String labelName) {
        List<TreeNode> treeNodeList = Lists.newArrayList();
        recursionFn(dataList, children, treeNodeList, keyName, parentKeyName, labelName);
        return treeNodeList;
    }
}
