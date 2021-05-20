package com.afanty.base.test.lamda.entity;

import lombok.Data;

/**
 * @Description
 * @Author yejx
 * @Date 2019/12/29
 */
@Data
public class Employee {
    private String name;
    private Integer age;
    private Double salary;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
}
