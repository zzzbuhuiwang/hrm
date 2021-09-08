package com.jaden.hrm.company.service.impl;

import com.jaden.hrm.common.utils.IdWorker;
import com.jaden.hrm.company.dao.CompanyDao;
import com.jaden.hrm.company.service.CompanyService;
import com.jaden.hrm.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private IdWorker idWorker;
    /**
     * 保存企业
     *  1.配置idwork到工程
     *  2.在service中注入idwork
     *  3.通过idwork生成id
     *  4.保存企业
     */
    public void add(Company company) {
        //基本属性的设置
        company.setId(idWorker.nextId() + "");
        company.setCreateTime(new Date());
        company.setState(1);//0.未激活，1：已激活
        company.setAuditState("0");//0：未审核，1：已审核
        company.setBalance(0d);
        companyDao.save(company);
    }

    /**
     * 更新企业
     *  1.参数：Company
     *  2.根据id查询企业对象
     *  3.设置修改的属性
     *  4.调用dao完成更新
     */
    public void update(Company company) {
        Company temp = companyDao.findById(company.getId()).get();
        temp.setName(company.getName());
        temp.setCompanyPhone(company.getCompanyPhone());
        companyDao.save(temp);
    }


    /**
     * 删除企业
     */
    public void deleteById(String id) {
        companyDao.deleteById(id);
    }

    /**
     * 根据id查询企业
     */
    public Company findById(String id) {
        return companyDao.findById(id).get();
    }

    /**
     * 查询企业列表
     */
    public List<Company> findAll() {
        return companyDao.findAll();
    }
}
