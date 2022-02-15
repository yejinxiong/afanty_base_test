package com.afanty.base.test.system.codetype.rest;

import com.afanty.base.test.common.BaseTest;
import com.afanty.base.test.common.web.ResponseResult;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典类型 前端控制器 测试
 *
 * @Author yejx
 * @Date 2022/1/28
 */
public class CodeTypeRestTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeTypeRestTest.class);

    @Autowired
    private CodeTypeRest codeTypeRest;

    @Test
    public void testQueryList() {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("typeCode", "");
            param.put("typeName", "");
            ResponseResult responseResult = codeTypeRest.queryList(param);
            System.out.println(JSONObject.toJSONString(responseResult));
        } catch (Exception e) {
            LOGGER.error("testQueryList 异常：{}", e.getMessage());
        }
    }

}
