package com.jaden.hrm.system.service;

import com.jaden.hrm.domain.system.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface UserService {

    void add(User user);
    void update(User user);
    void deleteById(String id);
    User findById(String id);
    List<User> findAll();
    Page findPage(Map<String,Object> map, int page, int size);

    void assignRoles(String userId,List<String> roleIds);
}
