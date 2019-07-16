package com.tw.apistackbase.repository;

import com.tw.apistackbase.modle.Company;
import com.tw.apistackbase.modle.Employee;

import java.util.List;

public interface CompanyRepostory {

    List<Company> findAll();

    Company findById(String companyId);

    List<Employee> findCompanyEmployeesByCompanyId(String companyId);

    List<Company> findCompaniesByPageandPageSize(int page, int pageSize);

    Company createCompany(Company company);

    Company updateCompany(Company company);

    Company deleteCompany(String companyId);
}
