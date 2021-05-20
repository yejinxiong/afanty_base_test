package com.afanty.base.test.lamda.rest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Description
 * @Author yejx
 * @Date 2020/1/18
 *
 * Java8 内置的四大核心接口
 *
 * Consumer<T>：消费接口
 *      void accept(T t);
 *
 * Supplier<T>：供给型接口
 *      T get();
 *
 * Funcyion<T, R>：函数型接口
 *      R apply(T t);
 *
 * Predicate<T>：断言型接口
 *      boolean test(T t);
 *
 */
public class LambdaRest04 {

    /**
     * Consumer<T>：消费型接口
     */
    @Test
    public void test01(){
        happy(1000, (m) -> System.out.println("共计消费：" + m + "元"));
    }

    public void happy(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    /**
     * Supplier<T>：供给型接口
     */
    @Test
    public void test02(){
        List<Integer> numList = getNumList(10, () -> (int)(Math.random() * 100));
        for (Integer integer : numList) {
            System.out.println(integer);
        }
    }

    public List<Integer> getNumList(int num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }

    /**
     * Function<T, R>：函数型接口
     */
    @Test
    public void test03(){
        String before = " 当我再见到你的时候 - 白举纲   ";
        System.out.println(before);
        String after = strHandler(before, (str) -> str.trim());
        System.out.println(after);
    }

    public String strHandler(String str, Function<String, String> function){
        return function.apply(str);
    }

    @Test
    public void test04(){
        List<String> stringList = Arrays.asList("Hello", "hero", "company", "information", "ok");
        List<String> filterStr = filterStr(stringList, (s) -> s.length() > 4);
        for (String s : filterStr) {
            System.out.println(s);
        }
    }

    /**
     * Predicate<T>：断言型接口
     * 需求：将满足条件的字符串放入集合中
     */
    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> strList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                strList.add(s);
            }
        }
        return strList;
    }




}
