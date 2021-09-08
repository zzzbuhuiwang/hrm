package com.jaden.hrm.company.controller;

import com.jaden.hrm.common.entity.Result;
import com.jaden.hrm.common.entity.ResultCode;
import com.jaden.hrm.common.exception.CommonException;
import com.jaden.hrm.company.service.CompanyService;
import com.jaden.hrm.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 单位增删改查 Controller
 *
 *      异常处理：
 *          @ControllerAdvice和@ExceptionHandler可以很好的在控制层对异常进行统一处理
 *      跨域处理：
 *          controller类上添加注解@CrossOrigin
 */
@CrossOrigin
@RestController
@RequestMapping(value="/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //保存企业
    @RequestMapping(value="",method = RequestMethod.POST)
    public Result save(@RequestBody Company company)  {
        companyService.add(company);
        return new Result(ResultCode.SUCCESS);
    }

    //根据id更新企业
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable(value="id") String id, @RequestBody Company company ) {
        company.setId(id);
        companyService.update(company);
        return new Result(ResultCode.SUCCESS);
    }

    //根据id删除企业
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value="id") String id) {
        companyService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    //根据id查询企业
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable(value="id") String id) throws CommonException {
        //throw new CommonException(ResultCode.UNAUTHORISE);
        Company company = companyService.findById(id);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(company);
        return result;
    }

    //查询全部企业列表
    @RequestMapping(value="",method = RequestMethod.GET)
    public Result findAll() {
        //int i = 1/0;
        List<Company> list = companyService.findAll();
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(list);
        return result;
    }
}
