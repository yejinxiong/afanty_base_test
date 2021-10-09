package com.afanty.base.test.business.es.rest;

import com.afanty.base.test.business.es.service.GoodsServiceImpl;
import com.afanty.base.test.common.web.MsgCode;
import com.afanty.base.test.common.web.PageResult;
import com.afanty.base.test.common.web.ResponseResult;
import com.afanty.base.test.common.web.StatusCode;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/goodsrest")
public class GoodsRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsRest.class);

    @Resource(name = "goodsServiceImpl")
    private GoodsServiceImpl goodsService;

    @ApiOperation(value = "解析网页并存储数据至es", notes = "解析网页并存储数据至es", response = ResponseResult.class)
    @GetMapping("/parse/{keyword}")
    public ResponseResult parse(@PathVariable("keyword") String keyword) {
        LOGGER.info("解析网页并存储数据至es, 参数：{}", keyword);
        ResponseResult rr = new ResponseResult();
        try {
            Boolean result = goodsService.parseGoods(keyword);
            if (!result) {
                rr = new ResponseResult(result, StatusCode.CODE_3000.getKey(), "网页解析并持久化失败");
            }
        } catch (Exception e) {
            LOGGER.error("解析网页并存储数据至es错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        LOGGER.info("请求结果：{}", JSONObject.toJSONString(rr));
        return rr;
    }

    @ApiOperation(value = "es分页查询", notes = "es分页查询", response = ResponseResult.class)
    @GetMapping("/getpagelist")
    public ResponseResult getPageList(@RequestParam Map<String, Object> param) {
        LOGGER.info("ES分页查询, 参数：{}", param);
        ResponseResult rr = new ResponseResult();
        Page<Map<String, Object>> page = new Page<>();
        try {
            Integer pageNo = Optional.ofNullable(param.get("pageNo")).map(p -> Integer.valueOf(p.toString())).orElse(1);
            Integer pageSize = Optional.ofNullable(param.get("pageSize")).map(p -> Integer.valueOf(p.toString())).orElse(10);
            List<Map<String, Object>> pageList = goodsService.getPageList(param, pageNo, pageSize);
            page.setCurrent(pageNo);
            page.setSize(pageSize);
            page.setTotal(pageList.size());
            page.setRecords(pageList);
            rr.setData(new PageResult<>(page));
        } catch (Exception e) {
            LOGGER.error("ES分页查询出错：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        LOGGER.info("请求结果：{}", JSONObject.toJSONString(rr));
        return rr;
    }

}
