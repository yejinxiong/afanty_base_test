package com.afanty.base.test.common.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

/**
 * <p>
 * spring 上下文工具类
 * </p>
 *
 * @author yejx
 * @date 2021/10/10
 */
public class SpringUtilEx {

    @Setter
    @Getter
    private static ApplicationContext applicationContext;

    /**
     * 描  述:获取applicationContext
     * 参  数:
     * 返回值: org.springframework.context.ApplicationContext
     * 创建时间: 2018/5/25
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 描  述: 设置applicationContext
     * 参  数:webAppCtx
     * 返回值: void
     * 创建时间: 2018/5/25
     */
    public static void setAppCtx(ApplicationContext webAppCtx) {
        if (webAppCtx == null)
            return;
        applicationContext = webAppCtx;
    }

    /**
     * 描  述:通过class获取Bean.
     * 参  数:clazz
     * 返回值: T
     * 创建时间: 2018/5/25
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 描  述:通过name,以及Clazz返回指定的Bean
     * 参  数:name
     * 参  数:clazz
     * 返回值: T
     * 创建时间: 2018/5/25
     */
    public static <T> T getBean(String name, Class<T> clazz) throws ClassNotFoundException {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 描  述:根据beanName(对应于Bean配置的Id属性）获取Bean对象
     * 参  数:beanName
     * 返回值: java.lang.Object
     * 创建时间: 2018/5/25
     */
    public final static Object getBean(String beanName) {
        return getApplicationContext().getBean(beanName);
    }

    public final static Object getBean(String beanName, String className) throws ClassNotFoundException {
        Class clz = Class.forName(className);
        return getApplicationContext().getBean(beanName, clz.getClass());
    }

    /**
     * 描  述:如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     * 参  数:name
     * 返回值: boolean
     * 创建时间: 2018/5/25
     */
    public static boolean containsBean(String name) {
        return getApplicationContext().containsBean(name);
    }

    /**
     * 描  述: 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     * 参  数:name
     * 返回值: boolean
     * 创建时间: 2018/5/25
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return getApplicationContext().isSingleton(name);
    }

    /**
     * 描  述: 获取实例对象的类型
     * 参  数:name
     * 返回值: java.lang.Class<?> 注册对象的类型
     * 创建时间: 2018/5/25
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return getApplicationContext().getType(name);
    }

    /**
     * 描  述: 如果给定的bean名字在bean定义中有别名，则返回这些别名
     * 参  数:name
     * 返回值: java.lang.String[]
     * 创建时间: 2018/5/25
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return getApplicationContext().getAliases(name);
    }
}
