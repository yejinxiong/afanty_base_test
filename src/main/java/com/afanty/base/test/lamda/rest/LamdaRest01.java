package com.afanty.base.test.lamda.rest;

import com.afanty.base.test.lamda.entity.Employee;
import com.afanty.base.test.lamda.service.FilterEmployeeByAge;
import com.afanty.base.test.lamda.service.FilterEmployeeBySalary;
import com.afanty.base.test.lamda.service.MyPredicateService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @Description
 * @Author yejx
 * @Date 2019/12/29
 */
@SpringBootTest
public class LamdaRest01 {

    /**
     * 原来的匿名内部类：比较两个Integer的大小
     */
    @Test
    public void test01(){
        Comparator<Integer> cp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(cp);
    }

    /**
     * Lamda表达式
     */
    @Test
    public void test02(){
        Comparator<Integer> cp = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(cp);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 38, 9999.99),
            new Employee("李四", 28, 6234.24),
            new Employee("王五", 48, 2347.56),
            new Employee("赵六", 23, 7343.53),
            new Employee("田七", 36, 4758.45)
    );

    /**
     * 需求：获取员工年龄大于35的员工信息
     */
    @Test
    public void test03(){
        List<Employee> employeeList = filterEmployeeByAge(employees);
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployeeByAge(List<Employee> list) {
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getAge() >= 35) {
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    /**
     * 需求：获取工资大于5000的员工的信息
     */
    @Test
    public void test04(){
        List<Employee> employeeList = filterEmployeeBySalary(employees);
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployeeBySalary(List<Employee> list){
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : list) {
            if (employee.getSalary() >= 5000) {
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    /**
     * 问题：通过上面两个例子可以看出每新增一个需求，就要新建一个对应的方法，而这些方法大部分相同，只需要改动“if”条件，代码大量冗余
     * 优化方式一：策略设计模式
     * 1、写一个接口，返回值为boolean类型，参数为一个泛型
     * 2、写一个类，实现上一个接口，在类里面写一个方法，根据需求在方法中做业务判断
     * 3、写一个公共方法，在该方法的if条件处调用上一个方法即可
     */
    @Test
    public void test05(){
        List<Employee> employeeList1 = filterEmployee(employees, new FilterEmployeeByAge());
        for (Employee employee : employeeList1) {
            System.out.println(employee);
        }

        System.out.println("----------------------------------");

        List<Employee> employeeList2 = filterEmployee(employees, new FilterEmployeeBySalary());
        for (Employee employee : employeeList2) {
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployee(List<Employee> list, MyPredicateService<Employee> mp) {
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : list) {
            if (mp.test(employee)) {
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    /**
     * 优化方式二：匿名内部类
     */
    @Test
    public void test06(){
        List<Employee> employeeList = filterEmployee(employees, new MyPredicateService<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 5000;
            }
        });
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    /**
     * 优化方式三：Lambda表达式
     */
    @Test
    public void test07(){
        List<Employee> employeeList = filterEmployee(employees, (e) -> e.getSalary() <= 5000);
        employeeList.forEach(System.out::println);
    }

    /**
     * 优化方式四：Stream API
     */
    @Test
    public void test08(){
        //获取工资小于5000的员工的信息
        employees.stream()
                .filter((e) -> e.getSalary() <= 5000)
                .forEach(System.out::println);

        System.out.println("---------------------------------");

        //获取所有员工的姓名
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

}
