package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.Registeredtype;
import com.pengqianhuai.hospitaldata.entity.Warehuose;
import com.pengqianhuai.hospitaldata.mapper.WarehuoseMapper;
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
 * 库房 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/warehouse")
public class WarehuoseController {

    @Autowired
    private WarehuoseMapper warehuoseMapper;

    /**
     * 查询仓库
     *
     * @param supplierName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAllWarehuose")
    @ResponseBody
    public Map findAllWarehuose(String supplierName, Integer page, Integer limit) {
        Map map = new HashMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (supplierName != null) {
            queryWrapper.like("supplierName", supplierName);
        }

        Page<Warehuose> pages = new Page(page, limit);
        IPage<Warehuose> iPage = warehuoseMapper.selectPage(pages, queryWrapper);
        List<Warehuose> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    /**
     * 添加仓库
     *
     * @return
     */
    @RequestMapping("/addWarehuose")
    @ResponseBody
    public String addWarehuose(Warehuose warehuose) {

        int row = warehuoseMapper.insert(warehuose);
        if (row > 0) {
            return "添加成功";
        }

        return "添加失败";
    }

}
