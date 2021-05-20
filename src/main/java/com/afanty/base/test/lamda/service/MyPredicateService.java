package com.afanty.base.test.lamda.service;

/**
 * @Description
 * @Author yejx
 * @Date 2019/12/29
 */
@FunctionalInterface    //用于判断是否是函数式接口
public interface MyPredicateService<T> {

    public boolean test(T t);

//    public boolean testHanshushi(T t);

}
