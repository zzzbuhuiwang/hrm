package com.jaden.hrm.system.dao;

import com.jaden.hrm.domain.system.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

}