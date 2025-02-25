package com.tw.apistackbase.repository;

import com.tw.apistackbase.modle.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeesRepostoryImpl implements EmployeesRepostory {
    private static Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee createEmployee(Employee employee) {
        String employeeId = UUID.randomUUID().toString();
        employee.setId(employeeId);
        employee.setName(employee.getName());
        employee.setAge(employee.getAge());
        employee.setGender(employee.getGender());
        employees.put(employeeId, employee);
        return employees.get(employeeId);
    }

    public EmployeesRepostoryImpl() {
        createEmployee("111", "小明", 18, "female", 7000);
        createEmployee("222", "小明2", 12, "male", 8000);
        createEmployee("333", "小明3", 13, "female", 9000);
        createEmployee("444", "小明4", 14, "male", 6000);
    }

    private void createEmployee(String id, String name, int age, String gender, int salary) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAge(age);
        employee.setGender(gender);
        employee.setSalary(salary);
        employees.put(id, employee);
    }

    @Override
    public Employee findById(String employeeId) {
        return employees.get(employeeId);
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employees.put(employee.getId(), employee);
        return employees.get(employee.getId());
    }

    @Override
    public Employee deleteEmployee(String employeeId) {
        return employees.remove(employeeId);
    }


    @Override
    public List<Employee> findSuitableEmployeesByAge(int age) {
        return employees.values().stream().filter(employee -> employee.getAge() > age).collect(Collectors.toList());
    }

    @Override
    public List<Employee> findEmployeesByPageAndPageSize(int page, int pageSize) {
        return employees.values().stream().limit(pageSize - page + 1).collect(Collectors.toList());
    }

    @Override
    public List<Employee> findEmployeesByGender(String gender) {
        return employees.values().stream().filter(employee -> employee.getGender().equals("male")).collect(Collectors.toList());
    }
}
