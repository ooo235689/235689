package com.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospitaldata.entity.Upplier;
import com.hospitaldata.mapper.UpplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供货单位 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/upplier")
public class UpplierController {

    @Autowired
    private UpplierMapper upplierMapper;

    /**
     * 查询供货商
     * @param supplierName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAllUpplier")
    @ResponseBody
    public Map findAllUpplier(String supplierName,Integer page,Integer limit){
        Map map = new HashMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (supplierName != null) {
            queryWrapper.like("supplierName", supplierName);
        }
        queryWrapper.eq("state",0);
        Page<Upplier> pages = new Page(page, limit);
        IPage<Upplier> iPage = upplierMapper.selectPage(pages, queryWrapper);
        List<Upplier> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    /**
     * 添加供货商
     * @return
     */
    @RequestMapping("/addUpplier")
    @ResponseBody
    public String addUpplier(Upplier upplier){
        upplier.setState(0);
        int row=upplierMapper.insert(upplier);
        if(row>0){
            return "添加成功";
        }

        return "添加失败";
    }

    /**
     * 删除供货商
     * @return
     */
    @RequestMapping("/deleteUpplier")
    @ResponseBody
    public String deleteUpplier(Integer supplierId){

        Upplier upplier=new Upplier();
        upplier.setSupplierId(supplierId);
        upplier.setState(1);

        int row=upplierMapper.updateById(upplier);
        if(row>0){
            return "删除成功";
        }

        return "删除失败";
    }

}
