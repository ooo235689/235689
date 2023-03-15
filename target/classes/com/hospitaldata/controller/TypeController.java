package com.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospitaldata.entity.Type;
import com.hospitaldata.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 类型 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 查询药品分类信息
     * @param typeName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAllType")
    @ResponseBody
    public Map findAllType(String typeName,Integer page,Integer limit){
        Map map = new HashMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (typeName != null) {
            queryWrapper.like("typeName", typeName);
        }
        Page<Type> pages = new Page(page, limit);
        IPage<Type> iPage = typeMapper.selectPage(pages, queryWrapper);
        List<Type> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    /**
     * 添加药品分类
     * @param type
     * @return
     */
    @RequestMapping("/addType")
    @ResponseBody
    public String addType(Type type){

        int row=typeMapper.insert(type);
        if(row>0){
            return "添加成功";
        }

        return "添加失败";
    }

}
