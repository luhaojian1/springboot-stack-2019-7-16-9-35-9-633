package com.tw.apistackbase.repository;

import com.tw.apistackbase.modle.Company;
import com.tw.apistackbase.modle.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CompanyRepostoryImpl implements CompanyRepostory {
    private Map<String, Company> companies = new LinkedHashMap<>();

    public CompanyRepostoryImpl() {
        createCompany("a123", "CVTE", 200);
        createCompany("b123", "TCL", 300);
        createCompany("c123", "APPLE", 500);
        createCompany("d123", "HUAWEI", 1000);
        createCompany("e123", "TENGXUN", 10000);
        createCompany("f123", "JINGDONG", 900);
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

    @Override
    public List<Company> findCompaniesByPageandPageSize(int page, int pageSize) {
        boolean isValid = page == 1 && pageSize == 5;
        if (isValid) {
            return companies.values().stream().limit(5).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Company createCompany(Company company) {
        String companyId = UUID.randomUUID().toString();
        company.setCompanyId(companyId);
        companies.put(companyId, company);
        return companies.get(companyId);
    }

    @Override
    public Company updateCompany(Company company) {
        companies.put(company.getCompanyId(), company);
        return companies.get(company.getCompanyId());
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
