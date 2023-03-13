package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.*;
import com.pengqianhuai.hospitaldata.mapper.BaoqueMapper;
import com.pengqianhuai.hospitaldata.mapper.DrugstoreMapper;
import com.pengqianhuai.hospitaldata.mapper.JiluMapper;
import com.pengqianhuai.hospitaldata.mapper.PharmacyMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 是否为无班和有班 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/chuku")
public class BanController {

    @Autowired
    private BaoqueMapper baoqueMapper;

    @Autowired
    private DrugstoreMapper drugstoreMapper;

    @Autowired
    private JiluMapper jiluMapper;

    @Autowired
    private PharmacyMapper pharmacyMapper;

    /**
     * 药房报缺
     * @return
     */
    @RequestMapping("/selbaoquedan")
    @ResponseBody
    public Map selbaoquedan(Model model) {
        Map map = new HashMap();

        List list = baoqueMapper.selectList(null);

        map.put("code", 0);
        map.put("count", list.size());
        map.put("data", list);
        map.put("msg", "");

        return map;
    }

    /**
     * 补给药品的数据
     *
     * @return
     */
    @RequestMapping("/selbaoquedrugname")
    @ResponseBody
    public Map selbaoquedrugname(String drugstoreName, Integer page, Integer limit) {
        Map map = new HashMap();

        QueryWrapper queryWrapper = new QueryWrapper();
        if (drugstoreName != null) {
            queryWrapper.eq("drugstoreName", drugstoreName);
        }

        Page<Drugstore> pages = new Page(page, limit);
        IPage<Drugstore> iPage = drugstoreMapper.selectPage(pages, queryWrapper);
        List<Drugstore> list = iPage.getRecords();

        map.put("code", 0);
        map.put("count", list.size());
        map.put("data", list);
        map.put("msg", "");

        return map;
    }

    /**
     * 修改 补给
     *
     * @return
     */
    @RequestMapping("/updatedrugnumber")
    @ResponseBody
    public Integer updatedrugnumber(HttpSession session, Drugstore drugstore, Jilu jilu) throws ParseException {

        Drugstore drugstore1 = drugstoreMapper.selectById(drugstore.getRugstoreId());
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        System.err.println(sysUser);
        jilu.setJilupeople(sysUser.getRealname());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String 转 Date
        Date time = sdf.parse(sdf.format(date));
        jilu.setJilutime(time);
        //添加出库记录
        int row = jiluMapper.insert(jilu);

        drugstore1.setDrugstorenum(drugstore1.getDrugstorenum() - drugstore.getDrugstorenum());

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("baoqueName", drugstore.getDrugstoreName());
        Baoque baoque = baoqueMapper.selectOne(queryWrapper);

        //修改药品库存
        int row1 = drugstoreMapper.updateById(drugstore1);

        //补给
        int row2 = 0;

        if (baoque.getBaoqueNum() > drugstore.getDrugstorenum()) {
            baoque.setBaoqueNum(baoque.getBaoqueNum() - drugstore.getDrugstorenum());
            row2 = baoqueMapper.updateById(baoque);
        } else {
            row2 = baoqueMapper.deleteById(baoque.getBaoqueid());
        }

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("pharmacyName", drugstore.getDrugstoreName());
        List list = pharmacyMapper.selectList(wrapper);
        for (int a = 0; a < list.size(); a++) {
            Pharmacy pharmacy = (Pharmacy) list.get(a);
            pharmacy.setDrugstorenum(pharmacy.getDrugstorenum() + drugstore.getDrugstorenum());
            pharmacyMapper.updateById(pharmacy);
        }
        int row3 = 0;

        if (row1 > 0) {
            return 1;
        }

        return 0;
    }

    /**
     * 出库单里的出库
     *
     * @return
     */
    @RequestMapping("/addpharmacy")
    @ResponseBody
    public Integer addpharmacy(HttpSession session, Pharmacy pharmacy, Integer rugstoreId, String pharmacyName, Jilu jilu) throws ParseException {

        Drugstore drugstore1 = drugstoreMapper.selectById(rugstoreId);
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        System.err.println(sysUser);
        jilu.setJilupeople(sysUser.getRealname());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String 转 Date
        Date time = sdf.parse(sdf.format(date));
        jilu.setJilutime(time);
        int row = jiluMapper.insert(jilu);

        //判断出库后数量是否为0
        if(drugstore1.getDrugstorenum()==pharmacy.getDrugstorenum()){
            int rows=drugstoreMapper.deleteById(rugstoreId);
        }else{
            drugstore1.setDrugstorenum(drugstore1.getDrugstorenum() - pharmacy.getDrugstorenum());
            //修改药品库存
            int rows = drugstoreMapper.updateById(drugstore1);

        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pharmacyName", pharmacyName);

        List list = pharmacyMapper.selectList(queryWrapper);

        int row2 = 0;

        System.err.println(list);
        if (list.size() > 0) {
            for (int a = 0; a < list.size(); a++) {
                Pharmacy pharmacys = (Pharmacy) list.get(a);

                pharmacys.setDrugstorenum(pharmacys.getDrugstorenum() + pharmacy.getDrugstorenum());

                //修改药房详情指定药品的数量
                row2 = pharmacyMapper.updateById(pharmacys);
            }
        } else {
            row2 = pharmacyMapper.insert(pharmacy);
        }

        if (row2 > 0) {
            return 1;
        }
        return 0;
    }

}
