package com.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospitaldata.entity.Cashier;
import com.hospitaldata.entity.Pharmacy;
import com.hospitaldata.entity.Report;
import com.hospitaldata.entity.SysMenu;
import com.hospitaldata.mapper.*;
import com.hospitaldata.service.IPharmacyService;
import com.hospitaldata.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门诊收费项目 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/caocc")
public class OutpatienttypeController {

    @Resource
    private IReportService iReportService;

    @Autowired
    private DrugdictionaryMapper drugdictionaryMapper;

    @Autowired
    private PharmacyMapper pharmacyMapper;

    @Resource
    private IPharmacyService iPharmacyService;

    @Autowired
    private CashierMapper cashierMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private OutpatienttypeMapper outpatienttypeMapper;


    /**
     * 进入处方划价
     *
     * @return
     */
    @RequestMapping("/cc")
    public String cc() {

        return "cao/cashier";
    }

    /**
     * 选择病人
     *
     * @return
     */
    @RequestMapping("/selpreson")
    @ResponseBody
    public Map selpreson(String durgname, Integer page, Integer limit) {
        Map map = new HashMap();

        List report = iReportService.selectReport(1,2, durgname, page, limit);

        map.put("code", 0);
        map.put("count", report.size());
        map.put("data", report);
        map.put("msg", "");

        return map;
    }

    /**
     * 查询药品数据 模糊查询
     *
     * @return
     */
    @RequestMapping("/seldrug")
    @ResponseBody
    public Map seldrug(Integer page, Integer limit, String durgname) {
        Map map = new HashMap();

//        List list=drugdictionaryMapper.selectList(null);

        List list = iPharmacyService.selectPharmacy(page, limit, durgname);

        map.put("code", 0);
        map.put("count", list.size());
        map.put("data", list);
        map.put("msg", "");

        return map;
    }

    /**
     * 处方划价 查询信息用户
     *
     * @param page
     * @param limit
     * @param durgname
     * @return
     */
    @RequestMapping("/mohu")
    @ResponseBody
    public Map mohu(Integer page, Integer limit, String durgname) {
        Map map = new HashMap();

        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 5;
        }

        List list = iReportService.selectReport(2,1, durgname, page, limit);

        map.put("code", 0);
        map.put("count", list.size());
        map.put("data", list);
        map.put("msg", "");
        return map;
    }

    /**
     * 查询对应用户的收费信息
     *
     * @param perid
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selall")
    @ResponseBody
    public Map selall(Integer perid, Integer page, Integer limit) {
        Map map = new HashMap();

        Page pages = new Page(page, limit);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("reportId", perid);

        IPage iPage = cashierMapper.selectPage(pages, queryWrapper);
        List<SysMenu> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", iPage.getTotal());
        map.put("data", list);

        return map;
    }

    /**
     * 判断用户是否填写病因
     *
     * @param reid
     * @return
     */
    @RequestMapping("/selbing")
    @ResponseBody
    public String selbing(Integer reid) {

        Report report = reportMapper.selectById(reid);

        if (report.getZhuan() != null) {
            return report.getZhuan();
        }

        return "";
    }

    /**
     * 添加病因
     *
     * @param reid
     * @param bing
     * @return
     */
    @RequestMapping("/addbing")
    @ResponseBody
    public Integer addbing(Integer reid, String bing) {

        Report report = new Report();
        report.setZhuan(bing);
        report.setReportId(reid);

        int row = reportMapper.updateById(report);

        if (row > 0) {
            return 1;
        }

        return 0;
    }

    /**
     * 判断是否买过这个药
     *
     * @return
     */
    @RequestMapping("/selchu")
    @ResponseBody
    public Integer addchu(Integer reid, String mename) {

        System.err.println(reid + "----------" + mename);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("reportId", reid);
        queryWrapper.eq("durgname", mename);

        Cashier cashier = cashierMapper.selectOne(queryWrapper);

        if (cashier != null) {
            return 1;
        }

        return 0;
    }

    /**
     * 添加药品
     *
     * @param durgname
     * @param durgnum
     * @param repiceprice
     * @param repicetotal
     * @param drugstorenum
     * @return
     */
    @RequestMapping("/addchu")
    @ResponseBody
    public Integer addchu(Integer reportId, String durgname, Integer durgnum, Double repiceprice, Double repicetotal, Integer drugstorenum) throws ParseException {

        Cashier cashier = new Cashier();
        cashier.setReportId(reportId);
        cashier.setDurgname(durgname);
        cashier.setDurgnum(durgnum);
        cashier.setRepiceprice(repiceprice);
        cashier.setRepicetotal(repicetotal);
//        if(drugstorenum==1){
        cashier.setState(0);
//        }
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //String 转 Date
        Date time =sdf.parse(sdf.format(date));
        cashier.setCtime(time);

        int row = cashierMapper.insert(cashier);

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("pharmacyName",durgname);
        Pharmacy pharmacy=pharmacyMapper.selectOne(queryWrapper);

        pharmacy.setDrugstorenum(pharmacy.getDrugstorenum()-durgnum);

        pharmacyMapper.updateById(pharmacy);

        return 1;
    }

    /**
     * 修改药品数量
     *
     * @return
     */
    @RequestMapping("/updchu")
    @ResponseBody
    public Integer updchu(Integer reportId, String durgname, Integer durgnum, Double repicetotal, Integer drugstorenum) throws ParseException {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("reportId", reportId);
        queryWrapper.eq("durgname", durgname);

        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //String 转 Date
        Date time =sdf.parse(sdf.format(date));

        Cashier cashier = cashierMapper.selectOne(queryWrapper);
        cashier.setDurgnum(cashier.getDurgnum() + durgnum);
        cashier.setRepicetotal(cashier.getRepicetotal() + repicetotal);
        cashier.setCtime(time);

        int row = cashierMapper.updateById(cashier);

        //修改库存
        QueryWrapper query=new QueryWrapper();
        query.eq("pharmacyName",durgname);
        Pharmacy pharmacy=pharmacyMapper.selectOne(query);
        pharmacy.setDrugstorenum(pharmacy.getDrugstorenum()-durgnum);
        pharmacyMapper.updateById(pharmacy);

        return 0;
    }

    /**
     * 删除药品
     * @param cashier
     * @param durnme
     * @param durnum
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public String del(Integer cashier,String durnme,Integer durnum) throws ParseException {

        int row=cashierMapper.deleteById(cashier);

        //修改库存
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("pharmacyName",durnme);
        Pharmacy pharmacy=pharmacyMapper.selectOne(queryWrapper);
        pharmacy.setDrugstorenum(pharmacy.getDrugstorenum()+durnum);
        pharmacyMapper.updateById(pharmacy);

        return "删除成功";
    }

    /**
     * 项目划价
     * @param perid
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selximu")
    @ResponseBody
    public Map selximu(Integer perid,Integer page,Integer limit) {
        Map map = new HashMap();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("reportId", perid);
        queryWrapper.eq("state",1);

        Page pages = new Page(page, limit);

        IPage iPage = cashierMapper.selectPage(pages, queryWrapper);
        List<SysMenu> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", iPage.getTotal());
        map.put("data", list);

        return map;
    }

    /**
     * 查看用户病因CT
     * @return
     */
    @RequestMapping("lookbing")
    @ResponseBody
    public String lookbing(Integer reid){

        Cashier cashier=cashierMapper.selectById(reid);

        return cashier.getJie()!=null?cashier.getJie():"";
    }


    /**
     * 药品缴费信息 指定id
     * @param perid
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selpepi")
    @ResponseBody
    public Map selpepi(Integer perid,Integer page,Integer limit){

        Map map=new HashMap();

        Page pages = new Page(page, limit);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("reportId", perid);
        queryWrapper.eq("state",0);

        IPage iPage = cashierMapper.selectPage(pages, queryWrapper);
        List<SysMenu> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", iPage.getTotal());
        map.put("data", list);

        return map;
    }

    /**
     * 判断该用户是否还有未缴费的项目
     * @return
     */
    @RequestMapping("/seljiao")
    @ResponseBody
    public  Integer seljiao(Integer reid){

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("state",1);
        queryWrapper.eq("reportId",reid);
        List list =cashierMapper.selectList(queryWrapper);

        return list.size();
    }

    /**
     * 判断该用户是否有缴费未做的项目
     * @return
     */
    @RequestMapping("/selwei")
    @ResponseBody
    public Integer selwei(Integer reid){


        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("ostate",0);
        queryWrapper.eq("reportId",reid);
        List list =cashierMapper.selectList(queryWrapper);

        if(list.size()>0){
            return 0;
        }
        return 1;
    }
    /**
     * 计算药品总费用
     * @param reportId
     * @return
     */
    @RequestMapping("/selch")
    @ResponseBody
    public Integer selch(Integer reportId){

        int count=0;

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("reportId", reportId);
        queryWrapper.eq("state",0);

        List list =cashierMapper.selectList(queryWrapper);
        System.err.println(list.size());
        for(int a=0;a<list.size();a++){
            Cashier cashier= (Cashier) list.get(a);
            count+=cashier.getRepicetotal();
        }

        return count;
    }

}
