package com.afanty.base.test.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 * spring 上下文工具类
 * </p>
 *
 * @author yejx
 * @date 2021/10/10
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过class获取Bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name获取 Bean
     *
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return getApplicationContext().getBean(beanName);
    }

    /**
     * 通过name和class获取Bean
     *
     * @param beanName
     * @param clazz
     * @param <T>
     * @return
     * @throws ClassNotFoundException
     */
    public static <T> T getBean(String beanName, Class<T> clazz) throws ClassNotFoundException {
        return getApplicationContext().getBean(beanName, clazz);
    }

    /**
     * 通过beanName和className获取Bean
     *
     * @param beanName
     * @param className
     * @return
     * @throws ClassNotFoundException
     */
    public static Object getBean(String beanName, String className) throws ClassNotFoundException {
        Class clz = Class.forName(className);
        return getApplicationContext().getBean(beanName, clz);
    }

    public static boolean containsBean(String beanName) {
        return getApplicationContext().containsBean(beanName);
    }

    public static boolean isSingleton(String beanName) throws NoSuchBeanDefinitionException {
        return getApplicationContext().isSingleton(beanName);
    }

    public static Class<?> getType(String beanName) throws NoSuchBeanDefinitionException {
        return getApplicationContext().getType(beanName);
    }

    public static String[] getAliases(String beanName) throws NoSuchBeanDefinitionException {
        return getApplicationContext().getAliases(beanName);
    }
}
