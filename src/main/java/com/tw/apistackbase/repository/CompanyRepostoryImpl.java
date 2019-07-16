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
    private Map<String , Company> companys = new HashMap<>();

    public CompanyRepostoryImpl(){
        createCompany("a123","CVTE");
        createCompany("b123","TCL");
    }

    @Override
    public List<Company> findAll() {
        return new ArrayList<>(companys.values());
    }
    private void createCompany(String id, String name){
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        company.setEmployees(createEmployees());
        companys.put(id,company);
    }

    private List<Employee> createEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(createEmployee("111", "小明", 18, "female"));
        employees.add(createEmployee("222", "小明2", 19, "female"));
        employees.add(createEmployee("333", "小明3", 13, "female"));
        return employees;
    }

    Employee createEmployee(String id, String name, int age, String gender) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAge(age);
        employee.setGender(gender);
        return employee;
    }


}
