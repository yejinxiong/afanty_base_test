package com.afanty.base.test.business.es.rest;

import com.afanty.base.test.business.es.service.GoodsServiceImpl;
import com.afanty.base.test.common.web.domain.PageResult;
import com.afanty.base.test.common.web.domain.ResponseResult;
import com.afanty.base.test.common.web.domain.StatusCode;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 商品 控制器
 * </p>
 *
 * @author yejx
 * @date 2021/5/14
 */
@RestController
@RequestMapping("/goods")
@Api(tags = "商品表接口", value = "goods", description = "商品表接口")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求已完成"),
        @ApiResponse(code = 201, message = "资源成功被创建"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")})
public class GoodsRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsRest.class);

    @Resource(name = "goodsServiceImpl")
    private GoodsServiceImpl goodsService;

    @ApiOperation(value = "解析网页并存储数据至es", notes = "解析网页并存储数据至es", response = ResponseResult.class)
    @GetMapping("/parse/{keyword}")
    public ResponseResult parse(@PathVariable("keyword") String keyword) {
        LOGGER.info("解析网页并存储数据至es, 参数：{}", keyword);
        try {
            Boolean successFlag = goodsService.parseGoods(keyword);
            return ResponseResult.auto(successFlag);
        } catch (Exception e) {
            LOGGER.error("解析网页并存储数据至es错误：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    @ApiOperation(value = "es分页查询", notes = "es分页查询", response = ResponseResult.class)
    @GetMapping("/getpagelist")
    public ResponseResult getPageList(@RequestParam Map<String, Object> param) {
        LOGGER.info("ES分页查询, 参数：{}", JSONObject.toJSONString(param));
        Page<Map<String, Object>> page = new Page<>();
        try {
            Integer pageNo = Optional.ofNullable(param.get("pageNo")).map(p -> Integer.valueOf(p.toString())).orElse(1);
            Integer pageSize = Optional.ofNullable(param.get("pageSize")).map(p -> Integer.valueOf(p.toString())).orElse(10);
            List<Map<String, Object>> pageList = goodsService.getPageList(param, pageNo, pageSize);
            page.setCurrent(pageNo);
            page.setSize(pageSize);
            page.setTotal(pageList.size());
            page.setRecords(pageList);
            return ResponseResult.success(new PageResult<>(page));
        } catch (Exception e) {
            LOGGER.error("ES分页查询出错：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

}
