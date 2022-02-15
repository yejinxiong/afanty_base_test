package com.afanty.base.test.system.dict.rest;


import com.afanty.base.test.common.annotation.NotNull;
import com.afanty.base.test.common.web.ResponseResult;
import com.afanty.base.test.common.web.StatusCode;
import com.afanty.base.test.system.codetype.service.CodeTypeServiceImp;
import com.afanty.base.test.system.dict.entity.Dict;
import com.afanty.base.test.system.dict.service.DictServiceImp;
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
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author yejx
 * @since 2022-01-17 22:57:52
 */
@RestController
@RequestMapping("/dict")
public class DictRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictRest.class);

    @Resource(name = "dictServiceImp")
    private DictServiceImp dictService;

    @Resource(name = "codeTypeServiceImp")
    private CodeTypeServiceImp codeTypeService;

    @ApiOperation(value = "条件查询字典表", notes = "条件查询字典表", response = ResponseResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeCode", value = "类型编码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "dictName", value = "字典名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "dictValue", value = "字典值", dataType = "String", paramType = "query")})
    @GetMapping(value = "/querylist")
    public ResponseResult<List<Dict>> queryList(@RequestParam @ApiParam(hidden = true) Map<String, Object> param) {
        LOGGER.info("条件查询字典表, 参数：{}", JSONObject.toJSONString(param));
        try {
            return ResponseResult.success(dictService.baseListQuery(param));
        } catch (Exception e) {
            LOGGER.error("条件查询字典表错误：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    //    @ApiIdempotent(fields = "typeCode,dictValue", serviceClass = DictServiceImp.class, clazz = Dict.class, errMsg = "该字典值已存在")
    @NotNull(fields = "typeCode,dictName,dictValue", entityClass = Dict.class)
    @ApiOperation(value = "新增字典", notes = "新增字典", response = ResponseResult.class)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(Dict dict) {
        String paramJson = JSONObject.toJSONString(dict);
        LOGGER.info("新增字典, 参数：{}", paramJson);
        ResponseResult rr = new ResponseResult();
        if (dict.getSortNo() > 1000) {
            rr.setStatus(StatusCode.CODE_1000.getKey());
            rr.setMsg("排序号最大为1000");
            return rr;
        }
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            this.setCreateInfo(dict, currentDateTime);
            this.setUpdateInfo(dict, currentDateTime);
            boolean successFlag = dictService.save(dict);
            return ResponseResult.auto(successFlag, dict);
        } catch (Exception e) {
            LOGGER.error("新增字典异常：{}", e.getMessage());
            return ResponseResult.error(StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc());
        }
    }

    /*-------------------------------------------------- 公共处理方法 --------------------------------------------------*/

    /**
     * 设置创建信息
     *
     * @param dict
     */
    private void setCreateInfo(Dict dict, LocalDateTime currentDateTime) {
        dict.setCreateUser("yejx");
        dict.setCreateName("叶金雄");
        dict.setCreateTime(currentDateTime);
    }

    /**
     * 设置修改信息
     *
     * @param dict
     */
    private void setUpdateInfo(Dict dict, LocalDateTime currentDateTime) {
        dict.setUpdateUser("yejx");
        dict.setUpdateName("叶金雄");
        dict.setUpdateTime(currentDateTime);
    }

}
