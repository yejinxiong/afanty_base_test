package com.afanty.base.test.lamda.rest;

import com.afanty.base.test.lamda.service.MyFunction;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * @Description
 * @Author yejx
 * @Date 2019/12/30
 *
 * 一、Lamda表达式的基础语法：Java8中引入了一个新的操作符“->”，该操作符称为箭头操作符或Lamda操作符
 *      箭头操作符将Lamda表达式拆分成两部分：
 *      左侧：Lamda表达式的参数列表
 *      右侧：Lamda表达式中所需要执行的功能，即Lamda体
 *
 * 语法格式一：参数，无返回值
 *      () -> System.out.println("Hello World!");
 *
 * 语法格式二：有一个参数，无返回值
 *      (x) -> System.out.println(x);
 *
 * 语法格式三：若只有一个参数，小括号可以省略不写
 *      x -> System.out.println(x);
 *
 * 语法格式四：有两个以上的参数，有返回值，且Lambda体中有多条语句
 *      Comparator<Integer> comparator = (x, y) -> {
 *             System.out.println("函数式接口");
 *             return Integer.compare(x, y);
 *      };
 *
 * 语法格式五：有两个以上的参数，有返回值，但Lambda体中只有一条语句，则return和大括号都可以省略不写
 *      Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
 *
 * 语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出数据类型，即“类型推断”，要指明类型，则全部都需要指明
 *      Comparator<Integer> comparator = (Integer x, Integer y) -> Integer.compare(x, y);
 *
 * 总结：
 *      左右遇一括号省
 *      左侧推断类型省
 *
 * 二、Lambda表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口。 可以使用@FunctionalInterface 修饰，可以检查是否是函数式接口
 *
 */
public class LamdaRest02 {

    /**
     * 语法格式一
     */
    @Test
    public void test01(){
        int num = 0;    //局部内部类中，应用了同级别的局部变量时：jdk1.7前，必须是final；jdk1.8以后，默认是final

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };
        r.run();

        System.out.println("----------------------------------");

        Runnable r1 = () -> System.out.println("Hello World!" + num);
        r1.run();
    }

    /**
     * 语法格式二：有一个参数，无返回值
     * 语法格式三：若只有一个参数，小括号可以省略不写
     */
    @Test
    public void test02(){
//        Consumer<String> consumer = (x) -> System.out.println(x);
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("Lambda学习");
    }

    /**
     * 语法格式四：有两个以上的参数，有返回值，且Lambda体中有多条语句
     */
    @Test
    public void test03(){
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
        System.out.println(comparator.compare(2, 2));
    }

    /**
     * 语法格式五：有两个以上的参数，有返回值，但Lambda体中只有一条语句，则return和大括号都可以省略不写
     */
    @Test
    public void test04(){
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        System.out.println(comparator.compare(2, 2));
    }

    /**
     * 语法格式六：JVM中的“类型推断”
     */
    @Test
    public void test05() {
        String[] str = {"aaa", "bbb", "ccc"};   //√
//        String[] str2;
//        str2 = {"aaa", "bbb", "ccc"}; //×

        List<String> stringList = new ArrayList<>();    //√

        //java8对类型推断进行了优化，此处HashMap后面的括号中不用指定show()方法需要的map类型，但是在jdk1.7中会编译不通过
        this.show(new HashMap<>());
    }

    public void show(Map<String, Integer> map){

    }

    /**
     * 需求：对一个数进行运算
     */
    @Test
    public void test06(){
        Integer num = operation(100, (x) -> x * x);
        System.out.println(num);
    }

    public Integer operation(Integer num, MyFunction myFunction){
        return myFunction.getValue(num);
    }

}
