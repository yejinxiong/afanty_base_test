package com.afanty.base.test.system.codetype.rest;


import com.afanty.base.test.common.web.MsgCode;
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
import java.util.Map;

/**
 * <p>
 * 字典/树类型 前端控制器
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

    @ApiOperation(value = "条件查询字典/树类型表", notes = "条件查询字典/树类型表", response = ResponseResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeCode", value = "类型编码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "typeName", value = "类型名称", dataType = "String", paramType = "query")})
    @GetMapping(value = "/querylist")
    public ResponseResult queryList(@RequestParam @ApiParam(hidden = true) Map<String, Object> param) {
        LOGGER.info("条件查询字典/树类型表, 参数：{}", JSONObject.toJSONString(param));
        ResponseResult rr = new ResponseResult();
        try {
            rr.setData(codeTypeService.baseListQuery(param));
        } catch (Exception e) {
            LOGGER.error("条件查询字典/树类型表错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
    }

    @ApiOperation(value = "新建", notes = "新建", response = ResponseResult.class)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(CodeType codeType) {
        LOGGER.info("新建, 参数：{}", JSONObject.toJSONString(codeType));
        ResponseResult rr = new ResponseResult();
        if (codeType == null) {
            rr.setStatus(StatusCode.CODE_1000.getKey());
            rr.setMsg(StatusCode.CODE_1000.getDesc());
            return rr;
        }
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            this.setCreateInfo(codeType, currentDateTime);
            this.setUpdateInfo(codeType, currentDateTime);
            codeTypeService.save(codeType);
        } catch (Exception e) {
            LOGGER.error("新建错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
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
