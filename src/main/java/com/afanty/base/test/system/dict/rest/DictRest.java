package com.afanty.base.test.system.dict.rest;


import com.afanty.base.test.common.web.MsgCode;
import com.afanty.base.test.common.web.ResponseResult;
import com.afanty.base.test.common.web.StatusCode;
import com.afanty.base.test.system.codetype.service.CodeTypeServiceImp;
import com.afanty.base.test.system.dict.entity.Dict;
import com.afanty.base.test.system.dict.service.DictServiceImp;
import com.afanty.base.test.system.dict.web.DictResponseCode;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
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
    public ResponseResult queryList(@RequestParam @ApiParam(hidden = true) Map<String, Object> param) {
        LOGGER.info("条件查询字典表, 参数：{}", JSONObject.toJSONString(param));
        ResponseResult rr = new ResponseResult();
        try {
            rr.setData(dictService.baseListQuery(param));
        } catch (Exception e) {
            LOGGER.error("条件查询字典表错误：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
    }

    @ApiOperation(value = "新增字典", notes = "新增字典", response = ResponseResult.class)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(Dict dict) {
        LOGGER.info("新增字典, 参数：{}", JSONObject.toJSONString(dict));
        ResponseResult rr = new ResponseResult();
        if (null == dict) {
            rr.setStatus(DictResponseCode.CODE_5001.getKey());
            rr.setMsg(DictResponseCode.CODE_5001.getDesc());
            return rr;
        } else if (StringUtils.isBlank(dict.getTypeCode())) {
            rr.setStatus(DictResponseCode.CODE_5002.getKey());
            rr.setMsg(DictResponseCode.CODE_5002.getDesc());
            return rr;
        } else if (StringUtils.isBlank(dict.getDictName())) {
            rr.setStatus(DictResponseCode.CODE_5003.getKey());
            rr.setMsg(DictResponseCode.CODE_5003.getDesc());
            return rr;
        } else if (StringUtils.isBlank(dict.getDictValue())) {
            rr.setStatus(DictResponseCode.CODE_5004.getKey());
            rr.setMsg(DictResponseCode.CODE_5004.getDesc());
            return rr;
        } else if (dict.getSortNo() > 1000) {
            rr.setStatus(DictResponseCode.CODE_5005.getKey());
            rr.setMsg(DictResponseCode.CODE_5005.getDesc());
            return rr;
        }
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("limit", 1);
            param.put("typeCode", dict.getTypeCode());
            int codeTypeExist = codeTypeService.baseCountQuery(param);
            if (codeTypeExist <= 0) {
                rr.setStatus(DictResponseCode.CODE_5006.getKey());
                rr.setMsg(DictResponseCode.CODE_5006.getDesc());
                return rr;
            }
            param.put("dictName", dict.getDictName());
            int dictNameRepeat = dictService.baseCountQuery(param);
            if (dictNameRepeat > 0) {
                rr.setStatus(DictResponseCode.CODE_5007.getKey());
                rr.setMsg(DictResponseCode.CODE_5007.getDesc());
                return rr;
            }
            param.remove("dictName");
            param.put("dictValue", dict.getDictValue());
            int dictValueRepeat = dictService.baseCountQuery(param);
            if (dictValueRepeat > 0) {
                rr.setStatus(DictResponseCode.CODE_5008.getKey());
                rr.setMsg(DictResponseCode.CODE_5008.getDesc());
                return rr;
            }
            LocalDateTime currentDateTime = LocalDateTime.now();
            this.setCreateInfo(dict, currentDateTime);
            this.setUpdateInfo(dict, currentDateTime);
            dictService.save(dict);
            rr.setData(dict);
        } catch (Exception e) {
            LOGGER.error("新增字典异常：{}", e.getMessage());
            rr = new ResponseResult(MsgCode.FAILURE.getKey(), StatusCode.CODE_3000.getKey(), StatusCode.CODE_3000.getDesc(), null);
        }
        return rr;
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
