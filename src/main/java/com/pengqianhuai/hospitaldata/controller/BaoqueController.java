package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengqianhuai.hospitaldata.entity.Register;
import com.pengqianhuai.hospitaldata.entity.Report;
import com.pengqianhuai.hospitaldata.mapper.RegisterMapper;
import com.pengqianhuai.hospitaldata.mapper.ReportMapper;
import com.pengqianhuai.hospitaldata.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/main")
public class BaoqueController {
    @Resource
    IDoctorService iDoctorService;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private RegisterMapper registerMapper;

    @RequestMapping("/one")
    @ResponseBody
    public List one() {
        List list = iDoctorService.selectDoctorBeOnDuty("one");
        return list;
    }

    @RequestMapping("/two")
    @ResponseBody
    public List two() {
        List list = iDoctorService.selectDoctorBeOnDuty("two");
        return list;
    }

    @RequestMapping("/three")
    @ResponseBody
    public List three() {
        List list = iDoctorService.selectDoctorBeOnDuty("three");
        return list;
    }

    @RequestMapping("/four")
    @ResponseBody
    public List four() {
        List list = iDoctorService.selectDoctorBeOnDuty("four");
        return list;
    }

    @RequestMapping("/five")
    @ResponseBody
    public List five() {
        List list = iDoctorService.selectDoctorBeOnDuty("five");
        return list;
    }

    @RequestMapping("/six")
    @ResponseBody
    public List six() {
        List list = iDoctorService.selectDoctorBeOnDuty("six");
        return list;
    }

    @RequestMapping("/seven")
    @ResponseBody
    public List seven() {
        List list = iDoctorService.selectDoctorBeOnDuty("seven");
        return list;
    }

    /**
     * 今日门诊数
     *
     * @return
     */
    @RequestMapping("/currentNum")
    @ResponseBody
    public int currentNum() {

        QueryWrapper<Report> wrapper = new QueryWrapper<>();

        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.err.println("时间" + simpleDateFormat.format(date));
        wrapper.ge("time", simpleDateFormat.format(date));

        wrapper.lt("time", simpleDateFormat.format(date.getTime()+(long)24*60*60*1000));
        List list = reportMapper.selectList(wrapper);

        return list.size();
    }

    /**
     * 门诊总人数
     *
     * @return
     */
    @RequestMapping("/Total")
    @ResponseBody
    public int Total() {
        int total = reportMapper.selectCount(null);
        return total;
    }

    /**
     * 住院总人数
     *
     * @return
     */
    @RequestMapping("/zhuyuanTotal")
    @ResponseBody
    public int zhuyuanTotal() {
        int total = registerMapper.selectCount(null);
        return total;
    }

    /**
     * 今日住院人数
     *
     * @return
     */
    @RequestMapping("/currentZhuYuan")
    @ResponseBody
    public int currentZhuYuan() {
        QueryWrapper<Register> wrapper = new QueryWrapper<>();

        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.err.println("时间" + simpleDateFormat.format(date));
        wrapper.ge("registerDate", simpleDateFormat.format(date));

        wrapper.lt("registerDate", simpleDateFormat.format(date.getTime()+(long)24*60*60*1000));
        List list = registerMapper.selectList(wrapper);

        return list.size();
    }
}
