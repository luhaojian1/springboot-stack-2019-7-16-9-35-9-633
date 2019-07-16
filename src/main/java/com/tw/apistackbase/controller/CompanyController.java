package com.tw.apistackbase.controller;

import com.tw.apistackbase.modle.Company;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> findAll(){
        return companyService.findAll();
    }

    @GetMapping("/companies/{companyId}")
    public Company findCompanyById(@PathVariable String companyId) {
        return companyService.findById(companyId);
    }


}
