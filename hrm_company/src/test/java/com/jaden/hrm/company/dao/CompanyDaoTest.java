package com.jaden.hrm.company.dao;

import com.jaden.hrm.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * CompanyDao测试类
 *      保存或更新（id为空是保存，id不为空是更新）
 *          save(company) ;
 *      根据id删除
 *          deleteByIid);
 *      查询全部
 *          findAll()
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyDaoTest {
    @Autowired
    private CompanyDao companyDao;

    @Test
    public void test() {
        // 根据id 查询单位信息
        Company company = companyDao.findById("1").get();
        System.out.println("查询的单位信息：" + company);
    }

}
