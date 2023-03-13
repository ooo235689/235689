package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.Jilu;
import com.pengqianhuai.hospitaldata.entity.Skull;
import com.pengqianhuai.hospitaldata.mapper.JiluMapper;
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
 *  前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/record")
public class JiluController {

    @Autowired
    private JiluMapper jiluMapper;

    /**
     * 操作纪律
     * @return
     */
    @RequestMapping("/selrecord")
    @ResponseBody
    public Map record(String jilutype, Integer page, Integer limit){
        Map map=new HashMap();

        QueryWrapper queryWrapper=new QueryWrapper();

        System.err.println("全部类型".equals(jilutype));

        if(jilutype!=null&&"全部类型".equals(jilutype)==false){
            queryWrapper.eq("jilutype",jilutype);
        }

        Page<Jilu> pages = new Page(page, limit);
        IPage<Jilu> iPage = jiluMapper.selectPage(pages, queryWrapper);
        List<Jilu> list = iPage.getRecords();

        map.put("code", 0);
        map.put("count", list.size());
        map.put("data", list);
        map.put("msg", "");
        return map;
    }
}
