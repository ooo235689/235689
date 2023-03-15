package com.hospitaldata.controller;


import com.hospitaldata.service.ICashierService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 床位 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/finance")
public class BedController {

    @Resource
    private ICashierService iCashierService;

    /**
     * 门诊月度统计
     *
     * @return
     */
    @RequestMapping("/reportYearFinance")
    @ResponseBody
    public List reportYearFinance(String year) {
        List list =iCashierService.outpatientMonthStatistics(year);
        System.err.println(list);
        return list;
    }

    /**
     * 门诊当前统计
     *
     * @return
     */
    @RequestMapping("/currentFinance")
    @ResponseBody
    public List currentFinance(String current) {

        List list = iCashierService.outpatientTodayStatistics(current);

        return list;
    }

    /**
     * 门诊年度统计
     *
     * @return
     */
    @RequestMapping("/reportYearBingFinance")
    @ResponseBody
    public List reportYearBingFinance() {

        List list = iCashierService.outpatientYearStatistics();

        return list;
    }

    /**
     * 住院年度统计
     * @return
     */
    @RequestMapping("/zhuYuanYearBingFinance")
    @ResponseBody
    public List zhuYuanYearBingFinance() {
        List list = iCashierService.hospitalizationYearStatistics();
        return list;
    }

    /**
     * 住院月度统计
     * @return
     */
    @RequestMapping("/zhuYuanYearFinance")
    @ResponseBody
    public List zhuYuanYearFinance(String year){

        List list =iCashierService.hospitalizationMonthStatistics(year);

        return list;
    }




}
