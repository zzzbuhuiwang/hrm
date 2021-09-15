package com.jaden.hrm.company.dao;

import com.jaden.hrm.domain.company.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * DepartmentDao测试类
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentDaoTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void test() {
        String companyId = "1";
        Specification<Department> spec = new Specification<Department>() {
            /**
             * 用户构造查询条件
             *      root   ：包含了所有的对象数据
             *      cq     ：一般不用
             *      cb     ：构造查询条件
             */
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //根据企业id查询
                return cb.equal(root.get("companyId").as(String.class),companyId);
            }
        };
        List<Department> departments = departmentDao.findAll(spec);
        System.out.println(departments);
    }

}
