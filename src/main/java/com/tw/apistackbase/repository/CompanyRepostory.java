package com.tw.apistackbase.repository;

import com.tw.apistackbase.modle.Company;
import com.tw.apistackbase.modle.Employee;

import java.util.List;

public interface CompanyRepostory {

    List<Company> findAll();

    Company findById(String companyId);

    List<Employee> findCompanyEmployeesByCompanyId(String companyId);
}
