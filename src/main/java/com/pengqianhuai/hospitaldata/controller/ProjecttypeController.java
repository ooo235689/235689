package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.*;
import com.pengqianhuai.hospitaldata.mapper.*;
import com.pengqianhuai.hospitaldata.service.IBedService;
import com.pengqianhuai.hospitaldata.service.IInoutpatienttypeService;
import com.pengqianhuai.hospitaldata.service.IOutpatienttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目类别 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/prjectType")
public class ProjecttypeController {

    @Autowired
    private ProjecttypeMapper projecttypeMapper;

    @Resource
    private IOutpatienttypeService iOutpatienttypeService;

    @Autowired
    private MoneytypeMapper moneytypeMapper;

    @Autowired
    private DepartmentsMapper departmentsMapper;

    @Resource
    private IInoutpatienttypeService iInoutpatienttypeService;

    @Autowired
    private InoutpatienttypeMapper inoutpatienttypeMapper;

    @Resource
    private IBedService iBedService;

    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private OutpatienttypeMapper outpatienttypeMapper;

    @Autowired
    private BedMapper bedMapper;

    @RequestMapping("/findAllProjecttype2")
    @ResponseBody
    public List findAllProjecttype2() {

        List list = projecttypeMapper.selectList(null);
        return list;
    }

    /**
     * 门诊收费项目
     *
     * @return
     */
    @RequestMapping("/findAllOutpatienttype")
    @ResponseBody
    public Map findAllOutpatienttype(String projectName, Integer page, Integer limit) {
        Map map = new HashMap();
        System.err.println(projectName);
        List list = iOutpatienttypeService.selectOutpatienttypeAndProjecttype(projectName, page, limit);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    /**
     * 查询社保
     *
     * @return
     */
    @RequestMapping("/findAllMoneytype")
    @ResponseBody
    public Map findAllMoneytype(String moneytype, Integer page, Integer limit) {
        Map map = new HashMap();

        QueryWrapper queryWrapper = new QueryWrapper();
        if (moneytype != null) {
            queryWrapper.like("Moneytype", moneytype);
        }

        Page<Moneytype> pages = new Page(page, limit);
        IPage<Moneytype> iPage = moneytypeMapper.selectPage(pages, queryWrapper);
        List<Moneytype> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    /**
     * 查询科室
     *
     * @return
     */
    @RequestMapping("/departmentList")
    @ResponseBody
    public List departmentList() {

        List list = departmentsMapper.selectList(null);

        return list;
    }

    /**
     * 查询住院床位
     *
     * @return
     */
    @RequestMapping("/findAllBed")
    @ResponseBody
    private Map findAllBed(String bedname, Integer page, Integer limit) {
        Map map = new HashMap();

        List list = iBedService.selectBed(bedname, page, limit);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    /**
     * 查询项目类型
     *
     * @return
     */
    @RequestMapping("/findAllProjecttype1")
    @ResponseBody
    public List findAllProjecttype1() {

        List list = projecttypeMapper.selectList(null);

        return list;
    }

    /**
     * 查询药品计量单位
     *
     * @return
     */
    @RequestMapping("/findAllUnit")
    @ResponseBody
    public List findAllUnit() {

        List list = unitMapper.selectList(null);

        return list;
    }

    /**
     * 查询药品计量单位
     *
     * @return
     */
    @RequestMapping("/findAllUnit1")
    @ResponseBody
    public List findAllUnit1() {

        List list = unitMapper.selectList(null);

        return list;
    }

    /**
     * 查询住院项目信息
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAllInoutpatienttype")
    @ResponseBody
    public Map findAllInoutpatienttype(Integer page, Integer limit) {
        Map map = new HashMap();

        List list = iInoutpatienttypeService.selectInoutpatienttypeAll(page, limit, null);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    /**
     * 查询项目类型
     *
     * @return
     */
    @RequestMapping("/findAllProjecttype")
    @ResponseBody
    public Map findAllProjecttype() {

        Map map = new HashMap();

        List list = projecttypeMapper.selectList(null);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    /**
     * 添加项目
     *
     * @param projectName
     * @return
     */
    @RequestMapping("/addProjecttype")
    @ResponseBody
    public String addProjecttype(String projectName) {

        Projecttype projecttype = new Projecttype();
        projecttype.setProjectName(projectName);

        int row = projecttypeMapper.insert(projecttype);
        if (row > 0) {
            return "添加成功";
        }

        return "添加失败";
    }

    /**
     * 修改门诊收费项目
     *
     * @return
     */
    @RequestMapping("/editOutpatienttype")
    @ResponseBody
    public String editOutpatienttype(Integer outpatientId, String projectName, double price, Integer projectId, Integer unitId, Integer ostate) {

        Outpatienttype outpatienttype = new Outpatienttype();
        outpatienttype.setOutpatientId(outpatientId);
        outpatienttype.setProjectName(projectName);
        outpatienttype.setUnit(unitId);
        outpatienttype.setBigprojectId(projectId);
        outpatienttype.setPrice(price);
        outpatienttype.setOstate(ostate);

        int row = outpatienttypeMapper.updateById(outpatienttype);

        if (row > 0) {
            return "修改成功";
        }

        return "修改失败";
    }

    /**
     * 删除门诊收费项目
     *
     * @return
     */
    @RequestMapping("/deleteOutpatienttype")
    @ResponseBody
    public String deleteOutpatienttype(Integer outpatientId) {

        int row = outpatienttypeMapper.deleteById(outpatientId);

        if (row > 0) {
            return "删除成功";
        }
        return "删除失败";
    }


    /**
     * 添加门诊收费项目
     *
     * @return
     */
    @RequestMapping("/addOutpatienttype")
    @ResponseBody
    public String addOutpatienttype(Integer outpatientId, String projectName, double price, Integer projectId, Integer unitId, Integer ostate) {

        Outpatienttype outpatienttype = new Outpatienttype();
        outpatienttype.setProjectName(projectName);
        outpatienttype.setUnit(unitId);
        outpatienttype.setBigprojectId(projectId);
        outpatienttype.setPrice(price);
        outpatienttype.setOstate(ostate);

        int row = outpatienttypeMapper.insert(outpatienttype);

        if (row > 0) {
            return "修改成功";
        }
        return "添加失败";
    }


    /**
     * 修改住院收费项目
     *
     * @return
     */
    @RequestMapping("/outpatienttype")
    @ResponseBody
    public String outpatienttype(Integer inoutpatientId, String projectName, Double price, Integer projectId, Integer unitId) {

        Inoutpatienttype inoutpatienttype = new Inoutpatienttype();
        inoutpatienttype.setInoutpatientId(inoutpatientId);
        inoutpatienttype.setProjectName(projectName);
        inoutpatienttype.setUnit(unitId);
        inoutpatienttype.setBigproJectId(projectId);
        inoutpatienttype.setPrice(price);

        int row = inoutpatienttypeMapper.updateById(inoutpatienttype);

        if (row > 0) {
            return "修改成功";
        }

        return "修改失败";
    }

    /**
     * 添加住院收费项目
     *
     * @return
     */
    @RequestMapping("/addInoutpatienttype")
    @ResponseBody
    public String addInoutpatienttype(Integer inoutpatientId, String projectName, Double price, Integer projectId, Integer unitId) {

        Inoutpatienttype inoutpatienttype = new Inoutpatienttype();
        inoutpatienttype.setProjectName(projectName);
        inoutpatienttype.setUnit(unitId);
        inoutpatienttype.setBigproJectId(projectId);
        inoutpatienttype.setPrice(price);

        int row = inoutpatienttypeMapper.insert(inoutpatienttype);
        if (row > 0) {
            return "添加成功";
        }
        return "添加失败";
    }

    /**
     * 删除住院收费项目
     *
     * @return
     */
    @RequestMapping("/deleteInoutpatienttype")
    @ResponseBody
    public String deleteInoutpatienttype(Integer inoutpatientId) {

        int row = inoutpatienttypeMapper.deleteById(inoutpatientId);
        if (row > 0) {
            return "删除成功";
        }

        return "删除失败";
    }


    /**
     * 添加床位
     *
     * @return
     */
    @RequestMapping("/addBed")
    @ResponseBody
    public String addBed(Integer bedId, String bedname, Integer departmentId, double price) {

        Bed bed = new Bed();
        bed.setBedname(bedname);
        bed.setDepartmentId(departmentId);
        bed.setState(0);
        bed.setPrice(price);

        int row = bedMapper.insert(bed);

        if (row > 0) {
            return "添加成功";
        }

        return "添加失败";
    }

    /**
     * 修改住院床位信息
     *
     * @param bedId
     * @param bedname
     * @param departmentId
     * @param price
     * @return
     */
    @RequestMapping("/editBed")
    @ResponseBody
    public String editBed(Integer bedId, String bedname, Integer departmentId, double price) {

        Bed bed = new Bed();
        bed.setBedId(bedId);
        bed.setBedname(bedname);
        bed.setDepartmentId(departmentId);
        bed.setPrice(price);

        int row = bedMapper.updateById(bed);

        if (row > 0) {
            return "添加成功";
        }

        return "修改失败";
    }

    /**
     * 删除成功
     *
     * @param bedId
     * @return
     */
    @RequestMapping("/deleteBed")
    @ResponseBody
    public String deleteBed(Integer bedId) {

        int row = bedMapper.deleteById(bedId);
        if (row > 0) {
            return "添加成功";
        }
        return "删除失败";
    }

    /**
     * 添加收费项目
     *
     * @return
     */
    @RequestMapping("/addMoneytype")
    @ResponseBody
    public String addMoneytype(Integer moneyId, String moneytype, String percent) {

        Moneytype moneytypes = new Moneytype();
        moneytypes.setMoneytype(moneytype);
        moneytypes.setPercent(percent);

        int row = moneytypeMapper.insert(moneytypes);
        if (row > 0) {
            return "添加成功";
        }

        return "添加失败";
    }

    /**
     * 修改收费类型
     *
     * @return
     */
    @RequestMapping("/editMoneytype")
    @ResponseBody
    public String editMoneytype(Integer moneyId, String moneytype, String percent) {

        Moneytype moneytypes = new Moneytype();
        moneytypes.setMoneyId(moneyId);
        moneytypes.setMoneytype(moneytype);
        moneytypes.setPercent(percent);

        int row = moneytypeMapper.updateById(moneytypes);
        if (row > 0) {
            return "添加成功";
        }

        return "修改失败";
    }

    /**
     * 删除修复类型
     *
     * @param moneyId
     * @return
     */
    @RequestMapping("/deleteMoneytype")
    @ResponseBody
    public String deleteMoneytype(Integer moneyId) {

        int row = moneytypeMapper.deleteById(moneyId);
        if (row > 0) {
            return "添加成功";
        }
        return "修改失败";
    }

}
