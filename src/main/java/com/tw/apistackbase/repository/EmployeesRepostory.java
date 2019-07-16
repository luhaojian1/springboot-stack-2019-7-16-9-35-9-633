package com.tw.apistackbase.repository;

import com.tw.apistackbase.modle.Employee;

import java.util.List;

public interface EmployeesRepostory {
    Employee createEmployee(Employee employee);

    Employee findById(String employeeId);

    List<Employee> findAll();

    Employee updateEmployee(Employee employee);

    Employee deleteEmployee(String employeeId);

    List<Employee> findSuitableEmployeesByAge(int age);
}
