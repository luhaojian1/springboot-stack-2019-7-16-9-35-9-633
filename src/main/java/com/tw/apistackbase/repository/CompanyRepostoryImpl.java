package com.tw.apistackbase.repository;

import com.tw.apistackbase.modle.Company;
import com.tw.apistackbase.modle.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CompanyRepostoryImpl implements CompanyRepostory{
    private Map<String, Company> companies = new HashMap<>();

    public CompanyRepostoryImpl(){
        createCompany("a123", "CVTE", 200);
        createCompany("b123", "TCL", 300);
    }

    @Override
    public List<Company> findAll() {
        return new ArrayList<>(companies.values());
    }

    @Override
    public Company findById(String companyId) {
        return companies.get(companyId);
    }

    @Override
    public List<Employee> findCompanyEmployeesByCompanyId(String companyId) {
        return companies.get(companyId).getEmployees();
    }

    private void createCompany(String id, String companyName, int employeesNumber) {
        Company company = new Company();
        company.setCompanyId(id);
        company.setCompanyName(companyName);
        company.setEmployeesNumber(employeesNumber);
        company.setEmployees(createEmployees());
        companies.put(id, company);
    }

    private List<Employee> createEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(createEmployee("111", "小明", 18, "female", 6000));
        employees.add(createEmployee("222", "小明2", 19, "female", 7000));
        employees.add(createEmployee("333", "小明3", 13, "female", 8000));
        return employees;
    }

    Employee createEmployee(String id, String name, int age, String gender, int salary) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setSalary(salary);
        employee.setAge(age);
        employee.setGender(gender);
        return employee;
    }


}
