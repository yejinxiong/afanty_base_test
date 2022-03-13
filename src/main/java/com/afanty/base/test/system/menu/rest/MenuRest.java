package com.afanty.base.test.system.menu.rest;


import com.afanty.base.test.common.tree.TreeNode;
import com.afanty.base.test.common.tree.TreeUtil;
import com.afanty.base.test.common.web.domain.ResponseResult;
import com.afanty.base.test.common.web.domain.StatusCode;
import com.afanty.base.test.system.menu.entity.Menu;
import com.afanty.base.test.system.menu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author yejx
 * @since 2022-03-13 17:30:01
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单接口", value = "Menu")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求已完成"),
        @ApiResponse(code = 201, message = "资源成功被创建"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")
})
public class MenuRest {

    @Resource(name = "menuServiceImp")
    private MenuService menuService;

    @PostMapping(value = "/getMenuTree")
    @ApiOperation(value = "获取菜单树")
    public ResponseResult<List<TreeNode>> getMenuTree(@RequestBody Menu menu) {
        try {
            List<Menu> menuList = menuService.baseListQuery(menu);
            List<TreeNode> treeNodeList = TreeUtil.buildTree(menuList, "menuId", "parentId", "menuName");
            return ResponseResult.success(treeNodeList);
        } catch (Exception e) {
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), e.getMessage());
        }
    }

}
