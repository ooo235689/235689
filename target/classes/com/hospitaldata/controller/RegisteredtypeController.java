package com.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospitaldata.entity.Registeredtype;
import com.hospitaldata.mapper.RegisteredtypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 挂号类型 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/registeredType")
public class RegisteredtypeController {

    @Autowired
    private RegisteredtypeMapper registeredtypeMapper;

    /**
     * 挂号类型信息
     * @return
     */
    @RequestMapping("/registeredTypeList")
    @ResponseBody
    public Map registeredTypeList(String type,Integer page,Integer limit){

        Map map = new HashMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (type != null) {
            queryWrapper.like("type", type);
        }

        Page<Registeredtype> pages = new Page(page, limit);
        IPage<Registeredtype> iPage = registeredtypeMapper.selectPage(pages, queryWrapper);
        List<Registeredtype> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    /**
     * 添加挂号类型
     * @param registeredId
     * @param type
     * @param price
     * @return
     */
    @RequestMapping("/addRegisteredType")
    @ResponseBody
    public String addRegisteredType(Integer registeredId,String type,double price){

        Registeredtype registeredtype=new Registeredtype();
        registeredtype.setType(type);
        registeredtype.setPrice(price);

        int row=registeredtypeMapper.insert(registeredtype);
        if(row>0){
            return "添加成功";
        }

        return "添加失败";
    }

    /**
     * 修改挂号类型
     * @param registeredId
     * @param type
     * @param price
     * @return
     */
    @RequestMapping("/editRegisteredType")
    @ResponseBody
    public String editRegisteredType(Integer registeredId,String type,double price){
        Registeredtype registeredtype=new Registeredtype();
        registeredtype.setRegisteredId(registeredId);
        registeredtype.setType(type);
        registeredtype.setPrice(price);

        int row=registeredtypeMapper.updateById(registeredtype);
        if(row>0){
            return "修改成功";
        }

        return "修改失败";
    }

}
