package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.Cashier;
import com.pengqianhuai.hospitaldata.entity.SysMenu;
import com.pengqianhuai.hospitaldata.mapper.CashierMapper;
import com.pengqianhuai.hospitaldata.service.IOutpatienttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 预交金记录 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/xpay")
public class PayController {

    @Autowired
    private CashierMapper cashierMapper;

    @Resource
    private IOutpatienttypeService iOutpatienttypeService;

    @RequestMapping("/xiangpay")
    public String xiangpay(){

        return "cao/xaingmupay";
    }


    /**
     * 项目缴费信息
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selxiang")
    @ResponseBody
    public Map selxiang(Integer perid,Integer page,Integer limit){
        Map map=new HashMap();

        Page pages = new Page(page, limit);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("reportId", perid);
        queryWrapper.eq("mstate",0);
        queryWrapper.eq("state",1);

        IPage iPage = cashierMapper.selectPage(pages, queryWrapper);
        List<Cashier> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", iPage.getTotal());
        map.put("data", list);

        return map;
    }

    /**
     * 计算总收费
     * @param perid
     * @return
     */
    @RequestMapping("/selshoux")
    @ResponseBody
    public Double selshoux(Integer perid){

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("reportId", perid);
        queryWrapper.eq("mstate",0);
        queryWrapper.eq("state",1);

        List list=cashierMapper.selectList(queryWrapper);

        double count=0;

        for (int a=0;a<list.size();a++){
            Cashier cashier= (Cashier) list.get(a);
            count+=cashier.getRepicetotal();
        }
        return count;
    }

    /**
     *缴费
     * @return
     */
    @RequestMapping("/shoufei")
    @ResponseBody
    public Integer shoufei(Integer perid){

        Cashier cashier=new Cashier();
        cashier.setMstate(1);

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("reportId",perid);

        int row=cashierMapper.update(cashier,queryWrapper);

        return row;
    }

    @RequestMapping("/seljian")
    public String jiancha(){

        return "cao/jiancha";
    }

    /**
     * 项目检查
     * @param perid
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selcha")
    @ResponseBody
    public Map selcha(Integer perid,Integer page,Integer limit){
        Map map=new HashMap();

        Page pages = new Page(page, limit);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("reportId", perid);
//        queryWrapper.eq("ostate",0);
        queryWrapper.eq("state",1);

        IPage iPage = cashierMapper.selectPage(pages, queryWrapper);
        List<Cashier> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", iPage.getTotal());
        map.put("data", list);

        return map;
    }

    /**
     * 修改状态 0未检查 1已检查
     * @return
     */
    @RequestMapping("/addbing")
    @ResponseBody
    public Integer addbing(Integer reid,String bing,Integer cashier){

        QueryWrapper queryWrapper=new QueryWrapper();

        queryWrapper.eq("cashier",cashier);
        queryWrapper.eq("reportId",reid);

        Cashier cashiers=new Cashier();
        cashiers.setJie(bing);
        cashiers.setOstate(1);

        int row=cashierMapper.update(cashiers,queryWrapper);

        return row;
    }

}
