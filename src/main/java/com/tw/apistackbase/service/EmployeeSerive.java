package com.tw.apistackbase.service;

import com.tw.apistackbase.modle.Employee;
import com.tw.apistackbase.repository.EmployeesRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSerive {
    @Autowired
    private EmployeesRepostory employeesRepostory;

    public Employee createEmployee(Employee employee) {
        return employeesRepostory.createEmployee(employee);
    }

    public Employee findById(String employeeId) {
        return employeesRepostory.findById(employeeId);
    }

    public List<Employee> findAll() {
        return employeesRepostory.findAll();
    }

    public Employee deleteEmployee(String employeeId) {
        return employeesRepostory.deleteEmployee(employeeId);
    }

    public Employee updateEmployee(Employee employee) {
        return employeesRepostory.updateEmployee(employee);
    }

    public List<Employee> findSuitableEmployeesByAge(int ageMini) {
        return employeesRepostory.findSuitableEmployeesByAge(ageMini);
    }

    public List<Employee> findEmployeesByPageAndPageSize(int page, int pageSize) {
        boolean isValid = page == 1 && pageSize == 5;
        if (!isValid) {
            return null;
        }
        return employeesRepostory.findEmployeesByPageAndPageSize(page, pageSize);
    }

    public List<Employee> findEmployeesByGender(String gender) {
        return employeesRepostory.findEmployeesByGender(gender);
    }
}
