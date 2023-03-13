package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.Area;
import com.pengqianhuai.hospitaldata.entity.Hospitalprice;
import com.pengqianhuai.hospitaldata.mapper.AreaMapper;
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
 * 产地 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaMapper areaMapper;

    /**
     * 药品场地
     *
     * @param areaName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAllArea")
    @ResponseBody
    public Map findAllArea(String areaName, Integer page, Integer limit) {
        Map map = new HashMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (areaName != null) {
            queryWrapper.like("areaName", areaName);
        }

        Page<Area> pages = new Page(page, limit);
        IPage<Area> iPage = areaMapper.selectPage(pages, queryWrapper);
        List<Area> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    @RequestMapping("/addArea")
    @ResponseBody
    public String addArea(Integer areaId, String areaName) {

        Area area = new Area();
        area.setAreaName(areaName);

        int row = areaMapper.insert(area);

        if (row > 0) {
            return "添加成功";
        }

        return "添加失败";
    }

}
