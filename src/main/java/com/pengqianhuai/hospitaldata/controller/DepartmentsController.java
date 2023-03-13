package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.Departments;
import com.pengqianhuai.hospitaldata.entity.Hospitalprice;
import com.pengqianhuai.hospitaldata.mapper.DepartmentsMapper;
import com.pengqianhuai.hospitaldata.mapper.HospitalpriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 科室，部门 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/department")
public class DepartmentsController {

    @Autowired
    private DepartmentsMapper departmentsMapper;

    /**
     * 查询科室信息
     * @param department
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/departmentList")
    @ResponseBody
    public Map departmentList(String department,Integer page,Integer limit){
        Map map=new HashMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        if(department!=null){
            queryWrapper.like("department", department);
        }

        Page<Departments> pages = new Page(page, limit);
        IPage<Departments> iPage = departmentsMapper.selectPage(pages,queryWrapper);
        List<Departments> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", iPage.getTotal());
        map.put("data", list);
        return map;
    }

}
