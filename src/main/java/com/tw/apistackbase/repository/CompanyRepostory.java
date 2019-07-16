package com.tw.apistackbase.repository;

import com.tw.apistackbase.modle.Company;

import java.util.List;

public interface CompanyRepostory {

    List<Company> findAll();
}
