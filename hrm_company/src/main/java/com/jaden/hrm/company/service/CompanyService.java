package com.jaden.hrm.company.service;

import com.jaden.hrm.domain.company.Company;

import java.util.List;

public interface CompanyService {

    void add(Company company);
    void update(Company company);
    void deleteById(String id);
    Company findById(String id);
    List<Company> findAll();

}
