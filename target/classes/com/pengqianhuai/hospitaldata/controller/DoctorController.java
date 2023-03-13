package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.*;
import com.pengqianhuai.hospitaldata.mapper.*;
import com.pengqianhuai.hospitaldata.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 医生 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DepartmentsMapper departmentsMapper;

    @Autowired
    private RegisteredtypeMapper registeredtypeMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Resource
    private IDoctorService iDoctorService;

    @Autowired
    private PaibanMapper paibanMapper;

    /**
     * 医生信息
     *
     * @param doctorName
     * @param dstate
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/doctorList")
    @ResponseBody
    public Map doctorList(String doctorName, Integer dstate, Integer page, Integer limit) {
        Map map = new HashMap();

        List list = iDoctorService.selectDoctor(doctorName, dstate, page, limit);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    /**
     * 部门信息
     *
     * @return
     */
    @RequestMapping("/findAllDepartments")
    @ResponseBody
    public List findAllDepartments() {

        List list = departmentsMapper.selectList(null);
        return list;
    }

    /**
     * 挂号类型
     *
     * @return
     */
    @RequestMapping("/findAllRegisteredtype")
    @ResponseBody
    public List findAllRegisteredtype() {

        List list = registeredtypeMapper.selectList(null);
        return list;
    }

    /**
     * 修改医生
     *
     * @return
     */
    @RequestMapping("/editDoctor")
    @ResponseBody
    public String editDoctor(Integer doctorId, String doctorName, Integer departmentId, Integer registeredId, Integer dstate) {

        Doctor doctor=new Doctor();
        doctor.setDoctorId(doctorId);
        doctor.setDoctorName(doctorName);
        doctor.setDepartmentId(departmentId);
        doctor.setRegisteredId(registeredId);
        doctor.setDstate(dstate);

        int row = doctorMapper.updateById(doctor);
        if (row > 0) {
            return "修改成功";
        }

        return "修改失败";
    }

    /**
     * 删除医生
     * @return
     */
    @RequestMapping("/deleteDoctor")
    @ResponseBody
    public String deleteDoctor(Integer doctorId){

        Doctor doctor=new Doctor();
        doctor.setDoctorId(doctorId);
        doctor.setDstate(3);

        int row=doctorMapper.updateById(doctor);
        if(row>0){
            return "删除成功";
        }

        return "删除失败";
    }


    /**
     * 添加医生
     * @param doctorId
     * @param doctorName
     * @param departmentId
     * @param registeredId
     * @param dstate
     * @return
     */
    @RequestMapping("/addDoctor")
    @ResponseBody
    public String addDoctor(Integer doctorId, String doctorName, Integer departmentId, Integer registeredId, Integer dstate){

        Doctor doctor=new Doctor();
        doctor.setDoctorName(doctorName);
        doctor.setDepartmentId(departmentId);
        doctor.setRegisteredId(registeredId);
        doctor.setDstate(dstate);

        int row = doctorMapper.insert(doctor);

        Paiban paiban=new Paiban();
        paiban.setDoctorId(doctor.getDoctorId());
        paiban.setOne("无班");
        paiban.setTwo("无班");
        paiban.setThree("无班");
        paiban.setFour("无班");
        paiban.setFive("无班");
        paiban.setSix("无班");
        paiban.setSeven("无班");
        int rows=paibanMapper.insert(paiban);

        if(row>0){
            return "添加成功";
        }

        return "添加失败";
    }

}
