package com.afanty.base.test.system.codetype.rest;


import com.afanty.base.test.common.annotation.NotNull;
import com.afanty.base.test.common.web.domain.ResponseResult;
import com.afanty.base.test.common.web.domain.StatusCode;
import com.afanty.base.test.system.codetype.entity.CodeType;
import com.afanty.base.test.system.codetype.service.CodeTypeServiceImp;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典类型 前端控制器
 * </p>
 *
 * @author yejx
 * @since 2022-01-17 00:36:04
 */
@RestController
@RequestMapping("/codetype")
@Api(tags = "字典类型接口", value = "CodeType")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "请求已完成"),
        @ApiResponse(code = 201, message = "资源成功被创建"),
        @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
        @ApiResponse(code = 401, message = "未授权客户机访问数据"),
        @ApiResponse(code = 403, message = "服务器接受请求，但是拒绝处理"),
        @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
        @ApiResponse(code = 500, message = "服务器出现异常")
})
public class CodeTypeRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeTypeRest.class);

    @Resource(name = "codeTypeServiceImp")
    private CodeTypeServiceImp codeTypeService;

    @ApiOperation(value = "条件查询字典类型表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeCode", value = "类型编码", paramType = "query"),
            @ApiImplicitParam(name = "typeName", value = "类型名称", paramType = "query")
    })
    @GetMapping(value = "/querylist")
    public ResponseResult<List<CodeType>> queryList(@RequestParam @ApiParam(hidden = true) Map<String, Object> param) {
        LOGGER.info("条件查询字典类型表, 参数：{}", JSONObject.toJSONString(param));
        try {
            return ResponseResult.success(codeTypeService.baseListQuery(param));
        } catch (Exception e) {
            LOGGER.error("条件查询字典类型表错误：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    //    @ApiIdempotent(fields = "typeCode", serviceClass = CodeTypeServiceImp.class, clazz = CodeType.class, errMsg = "编码已存在")
    @NotNull(fields = "typeCode,typeName,dictOrTree,enableFlag", entityClass = CodeType.class)
    @ApiOperation(value = "新增字典类型")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(CodeType codeType) {
        String paramJson = JSONObject.toJSONString(codeType);
        LOGGER.info("新增字典类型, 参数：{}", paramJson);
        String typeCode = codeType.getTypeCode();
        try {
            synchronized (typeCode.intern()) {
                LOGGER.info("[{}] 开始", typeCode);
                // 验证幂等是否有效
                Map<String, Object> param = new HashMap<>();
                param.put("typeCode", typeCode);
                int count = codeTypeService.baseCountQuery(param);
                if (count > 0) {
                    LOGGER.info("[{}] 该类型编码已存在", typeCode);
                    return ResponseResult.error(StatusCode.CODE_1000.getKey(), "该类型编码已存在");
                } else {
                    boolean successFlag = codeTypeService.save(codeType);
                    LOGGER.info("[{}] {}", typeCode, "操作完成");
                    return ResponseResult.auto(successFlag);
                }
            }
        } catch (Exception e) {
            LOGGER.error("新增字典类型异常：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    @ApiOperation(value = "手动回滚")
    @PostMapping(value = "/manualrollback")
    public ResponseResult manualrollback(CodeType codeType) {
        String paramJson = JSONObject.toJSONString(codeType);
        LOGGER.info("新增字典类型, 参数：{}", paramJson);
        try {
            boolean successFlag = codeTypeService.save(codeType);
            // 判断操作数据库是否成功，不成功，则手动回滚
            // 注意：如果写了全局异常拦截，这里也主动抛出异常了，则不需要手动回滚
            if (!successFlag) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
            } else {
                return ResponseResult.success();
            }
        } catch (Exception e) {
            LOGGER.error("新增字典类型异常：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

}
