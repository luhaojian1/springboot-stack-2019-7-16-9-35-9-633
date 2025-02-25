package com.tw.apistackbase.controller;

import com.tw.apistackbase.modle.Company;
import com.tw.apistackbase.modle.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/companies/{companyId}")
    public Company findCompanyById(@PathVariable String companyId) {
        return companyService.findById(companyId);
    }


    @GetMapping("/companies/{companyId}/employees")
    public List<Employee> findCompanyEmployeesByCompanyId(@PathVariable String companyId) {
        return companyService.findCompanyEmployeesByCompanyId(companyId);
    }

    @GetMapping(value = "/companies", params = {"page", "pageSize"})
    public List<Company> findCompaniesByPageAndPageSize(@RequestParam int page, @RequestParam int pageSize) {
        return companyService.findCompaniesByPageandPageSize(page, pageSize);
    }

    @PostMapping("/companies")
    public Company createCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @PutMapping("/companies/{companyId}")
    public Company updateCompany(@PathVariable String companyId, @RequestBody Company company) {
        company.setCompanyId(companyId);
        return companyService.updateCompany(company);
    }

    @DeleteMapping("/companies/{companyId}")
    public Company deleteCompany(@PathVariable String companyId) {
        return companyService.deleteCompany(companyId);
    }
}
