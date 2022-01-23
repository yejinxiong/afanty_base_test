package com.afanty.base.test.common.annotation;

import java.lang.annotation.*;

/**
 * 判断接口入参是否为空
 *
 * @Author yejx
 * @Date 2022/1/24
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NotNull {

    String fields();

    Class<?> entityClass();

}
