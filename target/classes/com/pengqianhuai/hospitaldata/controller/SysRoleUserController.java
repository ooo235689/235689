package com.pengqianhuai.hospitaldata.controller;


import com.pengqianhuai.hospitaldata.entity.Warehuose;
import com.pengqianhuai.hospitaldata.mapper.BaoqueMapper;
import com.pengqianhuai.hospitaldata.mapper.SkullMapper;
import com.pengqianhuai.hospitaldata.mapper.UpplierMapper;
import com.pengqianhuai.hospitaldata.mapper.WarehuoseMapper;
import com.pengqianhuai.hospitaldata.service.IDrugstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/dsnavigation")
public class SysRoleUserController {

    @Autowired
    private UpplierMapper upplierMapper;

    @Autowired
    private SkullMapper skullMapper;

    @Autowired
    private WarehuoseMapper warehuoseMapper;

    @Resource
    private IDrugstoreService iDrugstoreService;

    @Autowired
    private BaoqueMapper baoqueMapper;

    @RequestMapping("/pharymacyhtml")
    public String pharymacyhtml() {

        return "drugstore/c_pharmacy";
    }

    /**
     * 进入入库单
     *
     * @return
     */
    @RequestMapping("/beputinstorage")
    public String beputinstorage(Model model) {

        List list1 = upplierMapper.selectList(null);

        List list2 = skullMapper.selectList(null);

        List list3 = warehuoseMapper.selectList(null);

        model.addAttribute("selupp", list1);
        model.addAttribute("selsku", list2);
        model.addAttribute("selwar", list3);

        return "drugstore/c_beputinstorage";
    }

    /**
     * 进入库存查询
     *
     * @return
     */
    @RequestMapping("/selectdurg")
    public String selectdurg() {
        return "drugstore/c_selectDrug";
    }

    /**
     * 进入操作记录
     *
     * @return
     */
    @RequestMapping("/record")
    public String record() {

        return "drugstore/c_record";
    }

    /**
     * 进入出库单
     *
     * @return
     */
    @RequestMapping("/selectchuku")
    public String selectchuku(Model model) {
        List list = baoqueMapper.selectList(null);

        model.addAttribute("selbaoquecount", list.size());

        return "drugstore/c_gooutstore";
    }

    /**
     * 进入药品回收
     *
     * @return
     */
    @RequestMapping("/recycle")
    public String recycle() {
        return "drugstore/c_recycle";
    }

    /**
     * 进入库库存不足
     *
     * @return
     */
    @RequestMapping("/selectless")
    public String selectless() {
        return "drugstore/c_lackdrug";
    }

    /**
     * 进入过期处理
     *
     * @return
     */
    @RequestMapping("/seldrugDate")
    public String seldrugDate(Model model) {

        List list = iDrugstoreService.selectdrugstore("null",0,null, null, null);

        model.addAttribute("selcountlessnum",list.size());

        return "drugstore/c_storedrugDate";
    }

}
