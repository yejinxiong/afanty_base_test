package com.afanty.base.test.utils;

import java.util.UUID;

/**
 * <p>
 * 工具类
 * </p>
 *
 * @author yejx
 * @date 2021/5/10
 */
public class AfantyUtil {

    /**
     * 生成32位uuid
     *
     * @return
     */
    public static String genUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
