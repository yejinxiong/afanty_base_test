package com.afanty.base.test.lambda;

import com.afanty.base.test.lambda.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Description
 * @Author yejx
 * @Date 2020/1/18
 * <p>
 * 一、方法引用：若Lambda体中的内容有方法已经实现了，即可以使用“方法引用”
 * （可以理解为方法引用是Lambda表达式的另外一种表现形式）
 * <p>
 * 主要有三种语法格式：
 * <p>
 * 对象::实例方法名
 * <p>
 * 类::静态方法名
 * <p>
 * 类::实例方法名
 * <p>
 * 注意：
 * ①Lambda体中调用方法的参数列表和返回值类型要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 * ②若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用 类名::实例方法名
 * <p>
 * <p>
 * 二、构造器引用
 * 格式：
 * 类名::new
 * <p>
 * 注意：需要调用的构造器的参数列表要与函数式接口抽象方法的参数列表保持一致
 * <p>
 * <p>
 * 三、数组引用
 * 格式：
 * 类型::new
 */
public class MethodRefRestTest {

    /**
     * 对象::实例方法名
     */
    @Test
    public void test01() {
        //Lambda
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("abcdef");

        //方法引用
        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("abcdef");
    }

    /**
     * 对象::实例方法名
     */
    @Test
    public void test02() {
        //Lambda
        Employee employee = new Employee();
        Supplier<String> supplier = () -> employee.getName();
        System.out.println(supplier.get());

        //方法引用
        Supplier<Integer> supplier1 = employee::getAge;
        System.out.println(supplier1.get());
    }

    /**
     * 类::静态方法名
     */
    @Test
    public void test03() {
        //Lambda
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

        //方法引用
        Comparator<Integer> comparator1 = Integer::compare;
    }

    /**
     * 类::实例方法名
     */
    @Test
    public void test04() {
        //Lambda
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        //方法引用
        BiPredicate<String, String> biPredicate1 = String::equals;
    }

    /**
     * 构造器引用
     */
    @Test
    public void test05() {
        //Lambda
        Supplier<Employee> supplier = () -> new Employee();

        //构造器引用
        Supplier<Employee> supplier1 = Employee::new;
        System.out.println(supplier1.get());
    }

    @Test
    public void test06() {
        //Lambda
        Function<String, Employee> function = (str) -> new Employee(str);

        //构造器引用
        Function<String, Employee> function1 = Employee::new;
        System.out.println(function1.apply("yejx"));
    }

    /**
     * 数组引用
     */
    @Test
    public void test07() {
        Function<Integer, String[]> function = (x) -> new String[x];
        String[] strs = function.apply(10);
        System.out.println(strs.length);

        Function<Integer, String[]> function1 = String[]::new;
        String[] strs2 = function1.apply(20);
        System.out.println(strs2.length);
    }

}
