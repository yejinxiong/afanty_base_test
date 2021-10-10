package com.afanty.base.test.business.items.rest;


import com.afanty.base.test.business.items.entity.Items;
import com.afanty.base.test.business.items.service.ItemsServiceImpl;
import com.afanty.base.test.common.web.MsgCode;
import com.afanty.base.test.common.web.PageResult;
import com.afanty.base.test.common.web.ResponseResult;
import com.afanty.base.test.common.web.StatusCode;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 评分项表存的个省自定义的评分项数据，	读写，	数据量：< 10000条 前端控制器
 * </p>
 *
 * @author yejx
 * @since 2021-04-21
 */
@RestController
@RequestMapping("/items")
@Api(tags = "评分项表接口", value = "items", description = "评分项表接口")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求已完成"),
        @ApiResponse(code = 201, message = "资源成功被创建"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")})
public class ItemsRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemsRest.class);

    @Resource(name = "itemsServiceImpl")
    private ItemsServiceImpl itemsService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemsName", value = "标签名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "itemsType", value = "标签类型", dataType = "Integer", paramType = "query")})
    @ApiOperation(value = "条件查询评分项表", notes = "条件查询评分项表", response = ResponseResult.class)
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ResponseResult getlist(@RequestParam(required = false) Map<String, Object> param) {
        LOGGER.info("条件查询评分项表, 参数：{}", JSONObject.toJSONString(param));
        ResponseResult rr = new ResponseResult();
        try {
            rr.setData(itemsService.baseListQuery(param));
        } catch (Exception e) {
            LOGGER.error("条件查询评分项表错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
    }

    @ApiOperation(value = "分页查询评分项表", notes = "分页查询评分项表", response = ResponseResult.class)
    @RequestMapping(value = "/getpagelist", method = RequestMethod.GET)
    public ResponseResult getpagelist(@RequestParam Map<String, Object> param) {
        LOGGER.info("条件查询评分项表, 参数：{}", JSONObject.toJSONString(param));
        ResponseResult rr = new ResponseResult();
        Page<Items> page = new Page<>();
        try {
            page.setCurrent(Optional.ofNullable(param.get("page")).map(value -> Integer.valueOf(value.toString().trim())).orElse(1));
            page.setSize(Optional.ofNullable(param.get("rows")).map(value -> Integer.valueOf(value.toString().trim())).orElse(10));
            param.put("sort", Optional.ofNullable(param.get("sort")).map(value -> value.toString().trim()).orElse(""));
            param.put("limit", (page.getCurrent() - 1) * page.getSize() + "," + page.getSize());
            page.setTotal(itemsService.baseCountQuery(param));
            page.setRecords(itemsService.baseListQuery(param));
            rr.setData(new PageResult<>(page));
        } catch (Exception e) {
            LOGGER.error("分页查询评分项表错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
    }

    @ApiOperation(value = "根据id批量查询", notes = "将所有id放到一个字符串", response = ResponseResult.class)
    @RequestMapping(value = "/getlistbatchbyids", method = RequestMethod.GET)
    public ResponseResult getlistbatchbyids(@RequestParam Map<String, Object> param) {
        LOGGER.info("根据id批量查询, 参数：{}", JSONObject.toJSONString(param));
        ResponseResult rr = new ResponseResult();
        String itemsIds = MapUtils.getString(param, "itemsIds");
        if (StringUtils.isEmpty(itemsIds)) {
            rr.setStatus(StatusCode.CODE_1000.getKey());
            rr.setMsg(StatusCode.CODE_1000.getDesc());
            return rr;
        }
        try {
            String[] split = itemsIds.split(",");
            List<Items> itemsList = itemsService.listByIds(Arrays.asList(split));
            rr.setData(itemsList);
        } catch (Exception e) {
            LOGGER.error("根据id批量查询错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
    }

    @ApiOperation(value = "新建", notes = "param：items", response = ResponseResult.class)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(Items items) {
        LOGGER.info("新建, 参数：{}", JSONObject.toJSONString(items));
        ResponseResult rr = new ResponseResult();
        if (items == null) {
            rr.setStatus(StatusCode.CODE_1000.getKey());
            rr.setMsg(StatusCode.CODE_1000.getDesc());
            return rr;
        }
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            this.setCreateInfo(items, currentDateTime);
            this.setUpdateInfo(items, currentDateTime);
            itemsService.save(items);
        } catch (Exception e) {
            LOGGER.error("新建错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
    }

    @ApiOperation(value = "批量新建", notes = "param：items", response = ResponseResult.class)
    @RequestMapping(value = "/savebatch", method = RequestMethod.POST)
    public ResponseResult savebatch(@RequestBody List<Items> list) {
        LOGGER.info("批量新建, 参数：{}", JSONObject.toJSONString(list));
        ResponseResult rr = new ResponseResult();
        if (list == null || list.size() <= 0) {
            rr.setStatus(StatusCode.CODE_1000.getKey());
            rr.setMsg(StatusCode.CODE_1000.getDesc());
            return rr;
        }
        try {
            itemsService.saveBatch(list);
        } catch (Exception e) {
            LOGGER.error("新建错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
    }

    @ApiOperation(value = "修改", notes = "param：items", response = ResponseResult.class)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult update(Items items) {
        LOGGER.info("修改, 参数：{}", JSONObject.toJSONString(items));
        ResponseResult rr = new ResponseResult();
        if (items == null || StringUtils.isEmpty(items.getItemsId())) {
            rr.setStatus(StatusCode.CODE_1000.getKey());
            rr.setMsg(StatusCode.CODE_1000.getDesc());
            return rr;
        }
        try {
            itemsService.updateById(items);
        } catch (Exception e) {
            LOGGER.error("修改错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
    }

    @ApiOperation(value = "根据id批量修改使用状态", notes = "param：itemsIds,isUse", response = ResponseResult.class)
    @RequestMapping(value = "/updateusebatchbyid", method = RequestMethod.POST)
    public ResponseResult updateUseBatchById(@RequestParam Map<String, Object> param) {
        LOGGER.info("根据id批量修改使用状态, 参数：{}", JSONObject.toJSONString(param));
        ResponseResult rr = new ResponseResult();
        String itemsIds = MapUtils.getString(param, "itemsIds");
        Integer isUse = MapUtils.getInteger(param, "isUse");
        if (StringUtils.isEmpty(itemsIds) || isUse == null) {
            rr.setStatus(StatusCode.CODE_1000.getKey());
            rr.setMsg(StatusCode.CODE_1000.getDesc());
            return rr;
        }
        try {
            String[] split = itemsIds.split(",");
            List<Items> itemsList = new ArrayList<>();
            Arrays.asList(split).forEach(id -> {
                Items items = new Items();
                items.setItemsId(id);
                items.setIsUse(MapUtils.getInteger(param, "isUse"));
                itemsList.add(items);
            });
            itemsService.updateBatchById(itemsList);
        } catch (Exception e) {
            LOGGER.error("根据id批量修改使用状态错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
    }

    @ApiOperation(value = "根据id查询评分项", notes = "根据id查询评分项", response = ResponseResult.class)
    @RequestMapping(value = "/{itemsId}", method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable String itemsId) {
        LOGGER.info("根据id查询评分项, 参数：{}", itemsId);
        ResponseResult rr = new ResponseResult();
        if (StringUtils.isEmpty(itemsId)) {
            rr.setStatus(StatusCode.CODE_1000.getKey());
            rr.setMsg(StatusCode.CODE_1000.getDesc());
            return rr;
        }
        try {
            rr.setData(itemsService.getById(itemsId));
        } catch (Exception e) {
            LOGGER.error("根据id查询评分项错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
    }

    @ApiOperation(value = "根据id删除评分项", notes = "根据id删除评分项", response = ResponseResult.class)
    @RequestMapping(value = "/{itemsId}", method = RequestMethod.DELETE)
    public ResponseResult getlist(@PathVariable String itemsId) {
        LOGGER.info("根据id删除评分项, 参数：{}", itemsId);
        ResponseResult rr = new ResponseResult();
        if (StringUtils.isEmpty(itemsId)) {
            rr.setStatus(StatusCode.CODE_1000.getKey());
            rr.setMsg(StatusCode.CODE_1000.getDesc());
            return rr;
        }
        try {
            itemsService.removeById(itemsId);
        } catch (Exception e) {
            LOGGER.error("根据id删除评分项错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
    }

    /*-------------------------------------------------- 公共处理方法 --------------------------------------------------*/

    /**
     * 设置创建信息
     *
     * @param items
     */
    private void setCreateInfo(Items items, LocalDateTime currentDateTime) {
        items.setTenantId("AFANTY");
        items.setProId("xlm");
        items.setOrgId("development");
        items.setCreateUser("yejx");
        items.setCreateName("叶金雄");
        items.setCreateTime(currentDateTime);
    }

    /**
     * 设置修改信息
     *
     * @param items
     */
    private void setUpdateInfo(Items items, LocalDateTime currentDateTime) {
        items.setTenantId("AFANTY");
        items.setProId("xlm");
        items.setOrgId("development");
        items.setUpdateUser("yejx");
        items.setUpdateName("叶金雄");
        items.setUpdateTime(currentDateTime);
    }
}