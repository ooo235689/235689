package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.Cashier;
import com.pengqianhuai.hospitaldata.entity.Report;
import com.pengqianhuai.hospitaldata.mapper.CashierMapper;
import com.pengqianhuai.hospitaldata.mapper.ReportMapper;
import com.pengqianhuai.hospitaldata.service.IReportService;
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
 *  前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/caotake")
public class SysRoleMenuController {

    @Resource
    private IReportService iReportService;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private CashierMapper cashierMapper;

    @RequestMapping("/haun")
    public String haun(){

        return "cao/chuanzhe";
    }


    /**
     * 所有门诊信息
     * @param name
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selhuan")
    @ResponseBody
    public Map selhuan(String name,Integer page,Integer limit){
        Map map=new HashMap();

        List list=iReportService.selectAllReport(name,page,limit);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list);
        map.put("data", list);
        return map;
    }


    @RequestMapping("/seltake")
    public String seltake(){
        return "cao/Cquyao";
    }

    @RequestMapping("/tselpreson")
    @ResponseBody
    public Map tselpreson(String durgname, Integer page, Integer limit) {
        Map map = new HashMap();

        List report = iReportService.selectReport(2,2, durgname, page, limit);

        map.put("code", 0);
        map.put("count", report.size());
        map.put("data", report);
        map.put("msg", "");

        return map;
    }

    /**
     * 出库
     * @return
     */
    @RequestMapping("/chuku")
    @ResponseBody
    public Integer chuku(Integer reportId){


        Report report=new Report();
        report.setReportId(reportId);
        report.setState(3);

        int row=reportMapper.updateById(report);

        return row;
    }

    /**
     * 计算总金额
     * @return
     */
    @RequestMapping("/zong")
    @ResponseBody
    public double zong(Integer reid){

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("reportId",reid);

        double count=0;

        List list=cashierMapper.selectList(queryWrapper);

        for(int a=0;a<list.size();a++){
            Cashier cashier=(Cashier)list.get(a);

            count+=cashier.getRepicetotal();

        }

        return count;
    }

}
