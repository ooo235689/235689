package com.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospitaldata.mapper.SkullMapper;
import com.hospitaldata.entity.Skull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 经办人 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/skull")
public class SkullController {

    @Autowired
    private SkullMapper skullMapper;

    /**
     * 查询经办人
     * @param skullName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAllSkull")
    @ResponseBody
    public Map findAllSkull(String skullName,Integer page,Integer limit){
        Map map = new HashMap();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (skullName != null) {
            queryWrapper.like("skullName", skullName);
        }

        Page<Skull> pages = new Page(page, limit);
        IPage<Skull> iPage = skullMapper.selectPage(pages, queryWrapper);
        List<Skull> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    /**
     * 添加经办人
     * @param skull
     * @return
     */
    @RequestMapping("/addSkull")
    @ResponseBody
    public String addSkull(Skull skull){

        int row=skullMapper.insert(skull);

        if(row>0){
            return "添加失败";
        }

        return "添加成功";
    }

    /**
     * 修改经办人
     * @return
     */
    @RequestMapping("/editSkull")
    @ResponseBody
    public String editSkull(Skull skull){

        int row=skullMapper.updateById(skull);

        if(row>0){
            return "修改成功";
        }
        return "修改失败";
    }

    /**
     * 删除经办人
     * @param skullId
     * @return
     */
    @RequestMapping("/deleteSkull")
    @ResponseBody
    public String deleteSkull(Integer skullId){

        int row=skullMapper.deleteById(skullId);

        if(row>0){
            return "删除成功";
        }
        return "删除失败";
    }

}
