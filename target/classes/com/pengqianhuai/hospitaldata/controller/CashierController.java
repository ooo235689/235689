package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengqianhuai.hospitaldata.entity.Cashier;
import com.pengqianhuai.hospitaldata.entity.Report;
import com.pengqianhuai.hospitaldata.mapper.CashierMapper;
import com.pengqianhuai.hospitaldata.mapper.ReportMapper;
import com.pengqianhuai.hospitaldata.service.IOutpatienttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 出纳 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/caoout")
public class CashierController {

    @Autowired
    private CashierMapper cashierMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Resource
    private IOutpatienttypeService iOutpatienttypeService;

    /**
     * 判断是否已经就诊
     * 计算中金额
     * @param reportId
     * @return
     */
    @RequestMapping("/selch")
    @ResponseBody
    public Integer selch(Integer reportId) {

        int count = 0;

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("reportId", reportId);
        queryWrapper.eq("state", 0);

        List list = cashierMapper.selectList(queryWrapper);

        for (int a = 0; a < list.size(); a++) {
            Cashier cashier = (Cashier) list.get(a);
            count += cashier.getRepicetotal();
        }

        return count;
    }

    /**
     * 进入项目划价
     *
     * @return
     */
    @RequestMapping("/xiang")
    public String xiang() {

        return "cao/Cxiangmu";
    }

    /**
     * 项目信息
     * @param projectName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selout")
    @ResponseBody
    public Map selout(String projectName, Integer page, Integer limit) {

        Map map=new HashMap();

        List list = iOutpatienttypeService.selectOutpatienttype(projectName, page, limit);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    /**
     * 添加项目
     * @return
     */
    @RequestMapping("/addchuo")
    @ResponseBody
    public Integer addchuo(Integer reportId, String durgname, Integer durgnum, Double repiceprice, Double repicetotal, Integer drugstorenum) throws ParseException {

        Cashier cashier = new Cashier();
        cashier.setReportId(reportId);
        cashier.setDurgname(durgname);
        cashier.setDurgnum(durgnum);
        cashier.setRepiceprice(repiceprice);
        cashier.setRepicetotal(repicetotal);
        cashier.setState(1);
        cashier.setOstate(0);
        cashier.setMstate(0);

        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //String 转 Date
        Date time =sdf.parse(sdf.format(date));
        cashier.setCtime(time);

        int row = cashierMapper.insert(cashier);

        return 1;
    }


    /**
     * 删除项目
     * @return
     */
    @RequestMapping("/delo")
    @ResponseBody
    public String delo(Integer cashier){

        int row=cashierMapper.deleteById(cashier);

        if(row>0){
            return "删除成功";
        }

        return "删除失败";
    }

    /**
     * 进入药品缴费
     * @return
     */
    @RequestMapping("/out")
    public String out(){

        return "cao/Ctoll";
    }

    /**
     * 药品缴费提交
     * @return
     */
    @RequestMapping("/shoufei")
    @ResponseBody
    public String shoufei(Integer reportId,Double price) throws ParseException {

        Report report=new Report();
        report.setReportId(reportId);
        report.setPrice(price);
        report.setState(2);

        int row=reportMapper.updateById(report);
        Cashier cashier=new Cashier();
        cashier.setReportId(reportId);
        cashier.setDurgname("挂号费");
        cashier.setDurgnum(1);
        cashier.setRepiceprice(price);
        cashier.setRepicetotal(price);
        cashier.setState(2);

        Date date = new Date();
        SimpleDateFormat sdfa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String 转 Date
        Date time = sdfa.parse(sdfa.format(date));

        cashier.setCtime(time);

        cashierMapper.insert(cashier);

        return "缴费成功";
    }

}
