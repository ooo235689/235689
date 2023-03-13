package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.Skull;
import com.pengqianhuai.hospitaldata.entity.Unit;
import com.pengqianhuai.hospitaldata.mapper.UnitMapper;
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
 * 计量单位 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    private UnitMapper unitMapper;

    /**
     * 查询计量单位
     * @param unitName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAllUnit")
    @ResponseBody
    public Map findAllUnit(String unitName,Integer page,Integer limit){
        Map map = new HashMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (unitName != null) {
            queryWrapper.like("unitName", unitName);
        }

        Page<Unit> pages = new Page(page, limit);
        IPage<Unit> iPage = unitMapper.selectPage(pages, queryWrapper);
        List<Unit> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    /**
     * 添加计量单位
     * @return
     */
    @RequestMapping("/addUnit")
    @ResponseBody
    public String addUnit(Unit unit){

        int row=unitMapper.insert(unit);

        if(row>0){
            return "添加成功";
        }
        return "添加失败";
    }

}
