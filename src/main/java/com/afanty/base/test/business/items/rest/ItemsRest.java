package com.afanty.base.test.business.items.rest;


import com.afanty.base.test.business.items.entity.Items;
import com.afanty.base.test.business.items.service.ItemsServiceImpl;
import com.afanty.base.test.common.annotation.NotNull;
import com.afanty.base.test.common.utils.OkHttpUtil;
import com.afanty.base.test.common.web.domain.PageResult;
import com.afanty.base.test.common.web.domain.ResponseResult;
import com.afanty.base.test.common.web.domain.StatusCode;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @ApiOperation(value = "条件查询评分项表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemsName", value = "标签名称", paramType = "query"),
            @ApiImplicitParam(name = "itemsType", value = "标签类型", paramType = "query", dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ResponseResult<List<Items>> getlist(@RequestParam @ApiParam(hidden = true) Map<String, Object> param) {
        LOGGER.info("条件查询评分项表, 参数：{}", JSONObject.toJSONString(param));
        try {
            return ResponseResult.success(itemsService.baseListQuery(param));
        } catch (Exception e) {
            LOGGER.error("条件查询评分项表错误：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    @ApiOperation(value = "分页查询评分项表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemsName", value = "标签名称", paramType = "query"),
            @ApiImplicitParam(name = "itemsType", value = "标签类型", paramType = "query", dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/getpagelist", method = RequestMethod.GET)
    public ResponseResult<PageResult<Items>> getpagelist(@RequestParam @ApiParam(hidden = true) Map<String, Object> param) {
        LOGGER.info("条件查询评分项表, 参数：{}", JSONObject.toJSONString(param));
        Page<Items> page = new Page<>();
        try {
            page.setCurrent(Optional.ofNullable(param.get("page")).map(value -> Integer.valueOf(value.toString().trim())).orElse(1));
            page.setSize(Optional.ofNullable(param.get("rows")).map(value -> Integer.valueOf(value.toString().trim())).orElse(10));
            param.put("sort", Optional.ofNullable(param.get("sort")).map(value -> value.toString().trim()).orElse(""));
            param.put("limit", (page.getCurrent() - 1) * page.getSize() + "," + page.getSize());
            page.setTotal(itemsService.baseCountQuery(param));
            page.setRecords(itemsService.baseListQuery(param));
            return ResponseResult.success(new PageResult<>(page));
        } catch (Exception e) {
            LOGGER.error("分页查询评分项表错误：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    @ApiOperation(value = "根据id批量查询")
    @ApiImplicitParam(name = "itemsIds", value = "标签ids", paramType = "query", allowMultiple = true, required = true)
    @RequestMapping(value = "/getlistbatchbyids", method = RequestMethod.GET)
    public ResponseResult<List<Items>> getlistbatchbyids(@RequestParam String[] itemsIds) {
        LOGGER.info("根据id批量查询, 参数：{}", JSONObject.toJSONString(itemsIds));
        if (Objects.isNull(itemsIds) || itemsIds.length <= 0) {
            return ResponseResult.error(StatusCode.CODE_1000.getKey(), StatusCode.CODE_1000.getDesc());
        }
        try {
            List<Items> itemsList = itemsService.listByIds(Arrays.asList(itemsIds));
            return ResponseResult.success(itemsList);
        } catch (Exception e) {
            LOGGER.error("根据id批量查询错误：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    //    @ApiIdempotent(fields = "itemsName", serviceClass = ItemsServiceImpl.class, clazz = Items.class)
    @NotNull(fields = "itemsName", entityClass = Items.class)
    @ApiOperation(value = "新建")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(Items items) {
        LOGGER.info("新建, 参数：{}", JSONObject.toJSONString(items));
        try {
            boolean successFlag = itemsService.save(items);
            return ResponseResult.auto(successFlag);
        } catch (Exception e) {
            LOGGER.error("新建错误：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    @ApiOperation(value = "批量新建")
    @RequestMapping(value = "/savebatch", method = RequestMethod.POST)
    public ResponseResult savebatch(@RequestBody @ApiParam(hidden = true) List<Items> list) {
        LOGGER.info("批量新建, 参数：{}", JSONObject.toJSONString(list));
        if (list == null || list.size() <= 0) {
            return ResponseResult.error(StatusCode.CODE_1000.getKey(), StatusCode.CODE_1000.getDesc());
        }
        try {
            boolean successFlag = itemsService.saveBatch(list);
            return ResponseResult.auto(successFlag);
        } catch (Exception e) {
            LOGGER.error("新建错误：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseResult update(Items items) {
        LOGGER.info("修改, 参数：{}", JSONObject.toJSONString(items));
        if (items == null || StringUtils.isEmpty(items.getItemsId())) {
            return ResponseResult.error(StatusCode.CODE_1000.getKey(), StatusCode.CODE_1000.getDesc());
        }
        try {
            boolean successFlag = itemsService.updateById(items);
            return ResponseResult.auto(successFlag);
        } catch (Exception e) {
            LOGGER.error("修改错误：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    @ApiOperation(value = "根据id批量修改使用状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemsIds", value = "标签id", paramType = "query", dataType = "string", allowMultiple = true, required = true),
            @ApiImplicitParam(name = "isUse", value = "使用状态", paramType = "query", dataType = "int", required = true)
    })
    @RequestMapping(value = "/updateusebatchbyid", method = RequestMethod.POST)
    public ResponseResult updateUseBatchById(@RequestParam String[] itemsIds, @RequestParam Integer isUse) {
        if (Objects.isNull(itemsIds) || itemsIds.length <= 0 || isUse == null) {
            return ResponseResult.error(StatusCode.CODE_1000.getKey(), StatusCode.CODE_1000.getDesc());
        }
        try {
            List<Items> itemsList = new ArrayList<>();
            Arrays.asList(itemsIds).forEach(id -> {
                Items items = new Items();
                items.setItemsId(id);
                items.setIsUse(isUse);
                itemsList.add(items);
            });
            boolean successFlag = itemsService.updateBatchById(itemsList);
            return ResponseResult.auto(successFlag);
        } catch (Exception e) {
            LOGGER.error("根据id批量修改使用状态错误：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    @ApiOperation(value = "根据id查询评分项")
    @ApiImplicitParam(name = "itemsId", value = "标签id", required = true, paramType = "path")
    @RequestMapping(value = "/{itemsId}", method = RequestMethod.GET)
    public ResponseResult<Items> getById(@PathVariable String itemsId) {
        LOGGER.info("根据id查询评分项, 参数：{}", itemsId);
        if (StringUtils.isEmpty(itemsId)) {
            return ResponseResult.error(StatusCode.CODE_1000.getKey(), StatusCode.CODE_1000.getDesc());
        }
        try {
            return ResponseResult.success(itemsService.getById(itemsId));
        } catch (Exception e) {
            LOGGER.error("根据id查询评分项错误：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    @ApiOperation(value = "根据id删除评分项")
    @ApiImplicitParam(name = "itemsId", value = "标签id", required = true, paramType = "path")
    @RequestMapping(value = "/{itemsId}", method = RequestMethod.DELETE)
    public ResponseResult getlist(@PathVariable String itemsId) {
        LOGGER.info("根据id删除评分项, 参数：{}", itemsId);
        if (StringUtils.isEmpty(itemsId)) {
            return ResponseResult.error(StatusCode.CODE_1000.getKey(), StatusCode.CODE_1000.getDesc());
        }
        try {
            boolean successFlag = itemsService.removeById(itemsId);
            return ResponseResult.auto(successFlag);
        } catch (Exception e) {
            LOGGER.error("根据id删除评分项错误：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    @ApiOperation(value = "远程条件查询评分项表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemsName", value = "标签名称", paramType = "query"),
            @ApiImplicitParam(name = "itemsType", value = "标签类型", paramType = "query", dataTypeClass = Integer.class)
    })
    @RequestMapping(value = "/remotegetlist", method = RequestMethod.GET)
    public ResponseResult<List<Items>> remoteGetList(@RequestParam @ApiParam(hidden = true) Map<String, String> param) {
        LOGGER.info("远程条件查询评分项表, 参数：{}", JSONObject.toJSONString(param));
        try {
            String url = "http://127.0.0.1:9090/mp/ac/system/items/getlist";
            LOGGER.info("远程条件查询评分项表, url：{}", url);
            String result = OkHttpUtil.get(url, param, null);
            JSONObject responseObject = JSONObject.parseObject(result);
            LOGGER.info("远程条件查询评分项表, 结果：{}", JSONObject.toJSONString(responseObject));
            List<Items> itemsList = Optional.ofNullable(responseObject)
                    .map(response -> JSONObject.parseArray(response.getString("data"), Items.class))
                    .orElseGet(ArrayList::new);
            return ResponseResult.success(itemsList);
        } catch (Exception e) {
            LOGGER.error("远程条件查询评分项表异常：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "本地和远程修改标签")
    @RequestMapping(value = "/remoteupdate", method = RequestMethod.POST)
    public ResponseResult remoteUpdate(Items items) {
        LOGGER.info("本地和远程修改标签，参数：{}", JSONObject.toJSONString(items));
        if (items == null || StringUtils.isEmpty(items.getItemsId())) {
            return ResponseResult.error(StatusCode.CODE_1000.getKey(), StatusCode.CODE_1000.getDesc());
        }
        try {
            boolean successFlag = itemsService.updateById(items);
            int i = 1 / 0;

            Map<String, String> param = new HashMap<>();
            param.put("itemsId", "01a4d6dd5ace4e1cbf56c31d3dd698a4");
            param.put("field1", "测试1");
            LOGGER.info("远程修改评标签，参数：{}", JSONObject.toJSONString(param));
            String url = "http://127.0.0.1:9090/mp/ac/system/items/update";
            LOGGER.info("远程修改评标签, url：{}", url);
            String result = OkHttpUtil.post(url, param, null);
            JSONObject responseObject = JSONObject.parseObject(result);
            LOGGER.info("远程修改标签, 结果：{}", JSONObject.toJSONString(responseObject));

            return ResponseResult.auto(successFlag);
        } catch (Exception e) {
            LOGGER.error("本地和远程修改标签异常：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }
}
