package com.afanty.base.test.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Bean工具类
 *
 * @Author yejx
 * @Date 2022/3/13
 */
public class BeanUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 根据属性名获取属性值
     *
     * @param t            实体
     * @param propertyName 属性名
     * @param <T>          实体
     * @return
     */
    public static <T> String getProperty(T t, String propertyName) {
        String result = null;
        try {
            Class<?> aClass = t.getClass();
            Field declaredField = aClass.getDeclaredField(propertyName);
            declaredField.setAccessible(true);
            result = String.valueOf(declaredField.get(t));
        } catch (NoSuchFieldException e) {
            LOGGER.error("实体[{}]不存在[{}]字段，异常：{}", t.getClass(), propertyName, e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error("没有访问实体[{}]字段[{}]的权限，异常{}", t, propertyName, e.getMessage());
        }
        return result;
    }
}
