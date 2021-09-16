package com.jaden.hrm.system.service.impl;

import com.jaden.hrm.common.service.BaseService;
import com.jaden.hrm.common.utils.IdWorker;
import com.jaden.hrm.domain.system.User;
import com.jaden.hrm.system.dao.UserDao;
import com.jaden.hrm.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 1.保存用户
     */
    @Override
    public void add(User user) {
        //设置主键的值
        String id = idWorker.nextId()+"";
        user.setPassword("123456");//设置初始密码
        user.setEnableState(1);
        user.setId(id);
        //调用dao保存部门
        userDao.save(user);
    }

    /**
     * 2.更新用户
     */
    @Override
    public void update(User user) {
        //1.根据id查询部门
        User targetUser = userDao.findById(user.getId()).get();
        //2.设置部门属性
        targetUser.setUsername(user.getUsername());
        targetUser.setPassword(user.getPassword());
        targetUser.setDepartmentId(user.getDepartmentId());
        targetUser.setDepartmentName(user.getDepartmentName());
        //3.更新部门
        userDao.save(targetUser);
    }

    /**
     * 3.根据id删除用户
     */
    @Override
    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    /**
     * 4.根据id查询用户
     */
    @Override
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Page findPage(Map<String,Object> map, int page,int size) {
        Page<User> pageUser = userDao.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                //根据请求的companyId构造查询条件
                if(!StringUtils.isEmpty(map.get("companyId"))){
                    predicateList.add(criteriaBuilder.equal(root.get("companyId").as(String.class), (String)map.get("companyId")));
                }
                //根据请求的部门id构造查询条件
                if(!StringUtils.isEmpty(map.get("departmentId"))) {
                    predicateList.add(criteriaBuilder.equal(root.get("departmentId").as(String.class), (String)map.get("departmentId")));
                }
                if(!StringUtils.isEmpty(map.get("hasDept"))) {
                    //根据请求的hasDept判断  是否分配部门 0未分配（departmentId = null），1 已分配 （departmentId ！= null）
                    if("0".equals((String) map.get("hasDept"))) {
                        predicateList.add(criteriaBuilder.isNull(root.get("departmentId")));
                    }else {
                        predicateList.add(criteriaBuilder.isNotNull(root.get("departmentId")));
                    }
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        }, new PageRequest(page-1, size));
        return pageUser;
    }
}
