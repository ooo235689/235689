package com.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hospitaldata.entity.Registeredtype;
import com.hospitaldata.entity.Report;
import com.hospitaldata.entity.SysUser;
import com.hospitaldata.mapper.DoctorMapper;
import com.hospitaldata.mapper.RegisteredtypeMapper;
import com.hospitaldata.mapper.ReportMapper;
import com.hospitaldata.service.IReportService;
import com.hospitaldata.mapper.DepartmentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 挂号 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
@RequestMapping("/cao")
public class ReportController {

    @Autowired
    private DepartmentsMapper departmentsMapper;

    @Autowired
    private RegisteredtypeMapper registeredtypeMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Resource
    private IReportService iReportService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 进入用户挂号
     *
     * @return
     */
    @RequestMapping("/index")

    public String index(Integer cc ,String params,Model model,HttpSession httpSession) {


        if(cc==null){
            cc=1;
        }

        List report = iReportService.selectReport(1,cc,params,null,null);


        model.addAttribute("report", report);

        httpSession.setAttribute("ban",cc);

        return "cao/report";
    }

    /**
     * 科室
     *
     * @return
     */
    @RequestMapping("/seldep")
    @ResponseBody
    public List seldep() {
        List list = departmentsMapper.selectList(null);
        return list;
    }

    /**
     * 根据科室和挂号类型查询医生信息
     *
     * @return
     */
    @RequestMapping("/seldoctor")
    @ResponseBody
    public List seldoctor(Integer registeredid, Integer departmentId) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("departmentId", departmentId);
        queryWrapper.eq("registeredid", registeredid);

        List list = doctorMapper.selectList(queryWrapper);

        return list;
    }

    /**
     * 对应挂号类型的挂号费
     *
     * @return
     */
    @RequestMapping("/seltymo")
    @ResponseBody
    public Double seltymo(Integer registeredId) {

        Registeredtype registeredtype = registeredtypeMapper.selectById(registeredId);

        return registeredtype.getPrice();
    }

    /**
     * 挂号类型
     *
     * @return
     */
    @RequestMapping("selreg")
    @ResponseBody
    public List selreg() {
        List list = registeredtypeMapper.selectList(null);
        return list;
    }

    @RequestMapping("phone")
    @ResponseBody
    public Integer phone(String phone, String carid) {
        Map map = new HashMap();
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("phone", phone);
        List list1 = reportMapper.selectList(queryWrapper);

        if(list1.size()>0){
            return 1;
        }
        QueryWrapper queryWrapper2 = new QueryWrapper();

        queryWrapper2.eq("carid", carid);
        List list2 = reportMapper.selectList(queryWrapper2);
        if(list2.size()>0){
            return 2;
        }
        return 3;
    }

    /**
     * 提交挂号
     *
     * @param report
     * @return
     */
    @RequestMapping("/addre")
    @ResponseBody
    public String addre(Report report) throws ParseException {
        System.err.println(redisTemplate.keys("sys:users"));
        SysUser sysUser=(SysUser) redisTemplate.opsForValue().get("sys:users");
        report.setUsers(sysUser.getRealname());
        System.err.println(report);
        report.setState(1);
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String 转 Date
        Date time =sdf.parse(sdf.format(date));
        report.setTime(time);

        int row = reportMapper.insert(report);

        if (row > 0) {
            return "添加成功";
        }

        return "添加失败";
    }

    /**
     * 删除挂号
     * @return
     */
    @RequestMapping("/delre")
    @ResponseBody
    public String delre(Integer id){

        Report report=new Report();
        report.setReportId(id);
        report.setState(0);
        int row=reportMapper.updateById(report);

        if(row>0){
            return "删除成功";
        }

        return "删除失败";
    }

    @RequestMapping("/zhuanyuan")
    @ResponseBody
    public Integer zhuanyuan(Integer id,String zhuan){

        Report report=new Report();
        report.setReportId(id);
        report.setState(4);
        report.setZhuan(zhuan);

        int row=reportMapper.updateById(report);

        return row;
    }
}
