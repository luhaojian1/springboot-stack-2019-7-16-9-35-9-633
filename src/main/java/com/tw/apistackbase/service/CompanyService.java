package com.tw.apistackbase.service;

import com.tw.apistackbase.modle.Company;
import com.tw.apistackbase.modle.Employee;
import com.tw.apistackbase.repository.CompanyRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepostory companyRepostory;

    public List<Company> findAll() {
        return companyRepostory.findAll();
    }


    public Company findById(String companyId) {
        return companyRepostory.findById(companyId);
    }

    public List<Employee> findCompanyEmployeesByCompanyId(String companyId) {
        return companyRepostory.findCompanyEmployeesByCompanyId(companyId);
    }
}
