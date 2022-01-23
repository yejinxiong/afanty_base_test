package com.afanty.base.test.reflect.rest;

import com.afanty.base.test.business.items.entity.Items;
import com.afanty.base.test.business.items.service.ItemsServiceImpl;
import com.afanty.base.test.common.utils.SpringUtil;
import com.afanty.base.test.common.web.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Java反射的使用：
 * 1. 根据指定字段名获取字段值；
 * 2. 调用指定类的指定方法；
 *
 * @Author yejx
 * @Date 2022/1/23
 */
@RestController
@RequestMapping("/reflectdemo")
public class ReflectDemoRest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectDemoRest.class);

    /**
     * 要反射赋值的字段
     */
    private static String fields = "itemsName,isDelete";

    /**
     * 要执行的方法所在的类
     */
    private static Class serviceClass = ItemsServiceImpl.class;

    /**
     * 要执行的方法的名称
     */
    private static String methodName = "baseListQuery";

    @SuppressWarnings("unchecked")
    @GetMapping(value = "/testreflect")
    public ResponseResult testReflect() {
        LOGGER.info("Java反射的使用，Staring....");
        ResponseResult rr = new ResponseResult();
        try {
            Map<String, Object> paramMap = new HashMap<>();
            /**
             * 一、通过反射，将传入的参数赋值给指定的实体的指定字段
             */
            // 模拟传参
            Object object = this.getEntity();
            Class<?> entityClass = object.getClass();
            String[] fieldArr = fields.split(",");
            for (String fieldName : fieldArr) {
                Field field = entityClass.getDeclaredField(fieldName);
                // 设置对象的访问权限，保证对private的属性的访问
                field.setAccessible(true);
                Object fieldValue = field.get(object);
                if (Objects.nonNull(fieldValue)) {
                    paramMap.put(fieldName, fieldValue);
                } else {
                    LOGGER.info("testReflect 参数{}的值为空", fieldName);
                }
            }

            /**
             * 二、通过反射，调用指定类的指定方法
             */
            Method method = serviceClass.getDeclaredMethod(methodName, Map.class);
            List list = (List) method.invoke(SpringUtil.getBean(serviceClass), paramMap);
            rr.setData(list);
        } catch (NoSuchMethodException e) {
            LOGGER.error("testReflect NoSuchMethodException：{}", e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error("testReflect IllegalAccessException：{}", e.getMessage());
        } catch (InvocationTargetException e) {
            LOGGER.error("testReflect InvocationTargetException：{}", e.getMessage());
        } catch (NoSuchFieldException e) {
            LOGGER.error("testReflect NoSuchFieldException：{}", e.getMessage());
        } finally {
            LOGGER.info("Java反射的使用，Ending....");
        }
        return rr;
    }

    public Object getEntity() {
        Items items = new Items();
        items.setItemsName("获取租户");
        items.setItemsType(2);
        items.setItemsScore(new BigDecimal(5.0));
        items.setIsDelete(0);
        return items;
    }

}
