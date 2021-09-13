package com.jaden.hrm.company.service;

import com.jaden.hrm.domain.company.Department;

import java.util.List;

public interface DepartmentService {

    void save(Department department);
    void update(Department department);
    void deleteById(String id);
    Department findById(String id);
    List<Department> findAll(String companyId);

}
