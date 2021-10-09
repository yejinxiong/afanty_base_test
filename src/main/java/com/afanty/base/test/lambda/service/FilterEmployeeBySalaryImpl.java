package com.afanty.base.test.lambda.service;

import com.afanty.base.test.lambda.entity.Employee;

/**
 * @Description 根据工资过滤员工
 * @Author yejx
 * @Date 2019/12/29
 */
public class FilterEmployeeBySalaryImpl implements MyPredicateService<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 5000;
    }
}
