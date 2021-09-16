package com.jaden.hrm.system.service;

import com.jaden.hrm.domain.system.Role;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    void assignPerms(String roleId, List<String> permIds);

    void save(Role role);
    void update(Role role);
    void delete(String id);
    Role findById(String id);
    List<Role> findAll(String companyId);
    Page<Role> findByPage(String companyId, int page, int size);

}
