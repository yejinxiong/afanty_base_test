package com.afanty.base.test.common.annotation;

import org.apache.poi.ss.formula.functions.T;

import java.lang.annotation.*;
import java.util.Map;

/**
 * @Documented    用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化
 *
 * @Inherited    某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于class，则这个annotation将被用于该class的子类
 *
 * @Retention   定义了该Annotation被保留的时间长短
 *     RetentionPolicy.RUNTIME 注解会在class字节码文件中存在，在运行时可以通过反射获取到
 *     RetentionPolicy.CLASS 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得
 *     RetentionPolicy.SOURCE 注解仅存在于源码中，在class字节码文件中不包含

 * @Target   说明了Annotation所修饰的对象范围
 *     ElementType.CONSTRUCTOR 作用于构造器
 * 　　ElementType.FIELD 作用于域/属性
 * 　　ElementType.LOCAL_VARIABLE 用于描述局部变量
 * 　　ElementType.METHOD 作用于方法
 * 　　ElementType.PACKAGE 用于描述包
 * 　　ElementType.PARAMETER 用于描述参数
 * 　　ElementType.TYPE 用于描述类、接口(包括注解类型) 或enum声明

 * @Author yejx
 * @Date 2022/1/21
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiIdempotent {

    public Class serviceClass();

    public String method();

    public String param() default "{\"limit\": 1}";
}
