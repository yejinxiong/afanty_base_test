package com.afanty.base.test.system.codetype.rest;


import com.afanty.base.test.common.annotation.NotNull;
import com.afanty.base.test.common.web.ResponseResult;
import com.afanty.base.test.common.web.StatusCode;
import com.afanty.base.test.system.codetype.entity.CodeType;
import com.afanty.base.test.system.codetype.service.CodeTypeServiceImp;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class CodeTypeRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeTypeRest.class);

    @Resource(name = "codeTypeServiceImp")
    private CodeTypeServiceImp codeTypeService;

    @ApiOperation(value = "条件查询字典类型表", notes = "条件查询字典类型表", response = ResponseResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeCode", value = "类型编码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "typeName", value = "类型名称", dataType = "String", paramType = "query")})
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
    @ApiOperation(value = "新增字典类型", notes = "新增字典类型", response = ResponseResult.class)
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
                    LocalDateTime currentDateTime = LocalDateTime.now();
                    this.setCreateInfo(codeType, currentDateTime);
                    this.setUpdateInfo(codeType, currentDateTime);
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

    /*-------------------------------------------------- 公共处理方法 --------------------------------------------------*/

    /**
     * 设置创建信息
     *
     * @param codeType
     */
    private void setCreateInfo(CodeType codeType, LocalDateTime currentDateTime) {
        codeType.setCreateUser("yejx");
        codeType.setCreateName("叶金雄");
        codeType.setCreateTime(currentDateTime);
    }

    /**
     * 设置修改信息
     *
     * @param codeType
     */
    private void setUpdateInfo(CodeType codeType, LocalDateTime currentDateTime) {
        codeType.setUpdateUser("yejx");
        codeType.setUpdateName("叶金雄");
        codeType.setUpdateTime(currentDateTime);
    }

}
