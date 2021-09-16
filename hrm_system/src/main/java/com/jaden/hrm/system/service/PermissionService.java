package com.jaden.hrm.system.service;

import com.jaden.hrm.domain.system.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    void save(Map<String,Object> map) throws Exception;
    void update(Map<String,Object> map) throws Exception;
    void deleteById(String id) throws Exception;
    Map<String, Object> findById(String id) throws Exception;
    List<Permission> findAll(Map<String, Object> map);

}
