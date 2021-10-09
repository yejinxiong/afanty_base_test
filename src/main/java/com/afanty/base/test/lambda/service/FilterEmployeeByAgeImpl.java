package com.afanty.base.test.lambda.service;

import com.afanty.base.test.lambda.entity.Employee;

/**
 * @Description 根据年龄过滤员工
 * @Author yejx
 * @Date 2019/12/29
 */
public class FilterEmployeeByAgeImpl implements MyPredicateService<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}
