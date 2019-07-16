package com.tw.apistackbase.controller;

import com.tw.apistackbase.modle.Employee;
import com.tw.apistackbase.service.EmployeeSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeesController {
    @Autowired
    public EmployeeSerive employeeSerive;

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeSerive.createEmployee(employee);
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable String employeeId) {
        System.out.println(employeeId);
        return employeeSerive.findById(employeeId);
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeSerive.findAll();
    }

    @DeleteMapping("/employees/{employeeId}")
    public Employee deleteById(@PathVariable String employeeId) {
        return employeeSerive.deleteEmployee(employeeId);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable String employeeId, @RequestBody Employee employee) {
        employee.setId(employeeId);
        return employeeSerive.updateEmployee(employee);
    }

    @GetMapping(value = "/employees", params = "ageMini")
    public List<Employee> findAgeAbove(@RequestParam int ageMini) {
        System.out.println(ageMini);
        return employeeSerive.findSuitableEmployeesByAge(ageMini);
    }
}
