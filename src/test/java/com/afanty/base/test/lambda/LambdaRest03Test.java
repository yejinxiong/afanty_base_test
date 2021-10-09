package com.afanty.base.test.lambda;

import com.afanty.base.test.lambda.entity.Employee;
import com.afanty.base.test.lambda.service.LambdaService03;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author yejx
 * @Date 2020/1/18
 */
public class LambdaRest03Test {

    private List<Employee> employees = Arrays.asList(
            new Employee("张三", 38, 9999.99),
            new Employee("李四", 28, 6234.24),
            new Employee("王五", 48, 2347.56),
            new Employee("赵六", 23, 7343.53),
            new Employee("田七", 36, 4758.45)
    );

    @Test
    private void test01() {
        employees.sort((e1, e2) -> {
            if (e1.getAge().equals(e2.getAge())) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    /**
     * 需求：用于处理字符串
     */
    @Test
    private void test02() {
        String before = " 当我再见到你的时候 - 白举纲   ";
        System.out.println(before);
        String after = strHandler(before, String::trim);
        System.out.println(after);
    }

    private String strHandler(String str, LambdaService03 ls) {
        return ls.getValue(str);
    }

}
