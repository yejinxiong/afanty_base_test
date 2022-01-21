package com.afanty.base.test.common.utils;

import com.alibaba.fastjson.JSON;

/**
 * json工具类
 *
 * @Author yejx
 * @Date 2022/1/21
 */
public class JsonUtil {

    /**
     * 校验字符串是否为json格式
     *
     * @param str
     * @return
     */
    public static boolean isJsonStr(String str) {
        boolean result;
        try {
            JSON.parse(str);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
