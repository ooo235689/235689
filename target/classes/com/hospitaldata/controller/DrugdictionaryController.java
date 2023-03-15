package com.hospitaldata.controller;


import com.hospitaldata.entity.Drugdictionary;
import com.hospitaldata.mapper.AreaMapper;
import com.hospitaldata.mapper.DrugdictionaryMapper;
import com.hospitaldata.mapper.UnitMapper;
import com.hospitaldata.service.IDrugdictionaryService;
import com.hospitaldata.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 药品清单 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/drugdictionary")
public class DrugdictionaryController {

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Resource
    private IDrugdictionaryService iDrugdictionaryService;

    @Autowired
    private DrugdictionaryMapper drugdictionaryMapper;

    /**
     * 药品字典
     *
     * @param drugName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAllSdrugdictionary")
    @ResponseBody
    public Map findAllSdrugdictionary(String drugName, Integer page, Integer limit) {

        Map map = new HashMap();

        List list = iDrugdictionaryService.selectDrugdictionary(drugName, page, limit);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }


    /**
     * 查询产地
     *
     * @return
     */
    @RequestMapping("/findAllArea")
    @ResponseBody
    public List findAllArea() {

        List lsit = areaMapper.selectList(null);
        return lsit;
    }

    /**
     * 查询计量单位
     *
     * @return
     */
    @RequestMapping("/findAllUnit")
    @ResponseBody
    public List findAllUnit() {

        List lsit = unitMapper.selectList(null);
        return lsit;
    }

    /**
     * 查询药品类型
     *
     * @return
     */
    @RequestMapping("/findAllType")
    @ResponseBody
    public List findAllType() {

        List lsit = typeMapper.selectList(null);
        return lsit;
    }

    /**
     * 添加药品清单
     *
     * @param drugdictionary
     * @return
     */
    @RequestMapping("/addSdrugdictionary")
    @ResponseBody
    public String addSdrugdictionary(Drugdictionary drugdictionary) {

        int row = drugdictionaryMapper.insert(drugdictionary);
        if (row > 0) {
            return "添加成功";
        }
        return "添加失败";
    }

    /**
     * 修改药品清单
     *
     * @param drugdictionary
     * @return
     */
    @RequestMapping("/editSdrugdictionary")
    @ResponseBody
    public String editSdrugdictionary(Drugdictionary drugdictionary) {

        int row = drugdictionaryMapper.updateById(drugdictionary);
        if (row > 0) {
            return "修改成功";
        }
        return "修改失败";
    }

    /**
     * 删除药品
     * @param drugId
     * @return
     */
    @RequestMapping("/deleteSdrugdictionary")
    @ResponseBody
    public String deleteSdrugdictionary(Integer drugId){

        int row=drugdictionaryMapper.deleteById(drugId);
        if (row > 0) {
            return "删除成功";
        }
        return "删除成功";
    }

}
