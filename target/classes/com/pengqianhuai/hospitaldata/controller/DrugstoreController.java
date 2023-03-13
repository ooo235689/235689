package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.*;
import com.pengqianhuai.hospitaldata.mapper.*;
import com.pengqianhuai.hospitaldata.service.IDrugdictionaryService;
import com.pengqianhuai.hospitaldata.service.IDrugstoreService;
import com.pengqianhuai.hospitaldata.service.IPharmacyService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 药品库存 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/seldrugstore")
public class DrugstoreController {

    @Resource
    private IDrugdictionaryService iDrugdictionaryService;

    @Autowired
    private JiluMapper jiluMapper;

    @Autowired
    private DrugdictionaryMapper drugdictionaryMapper;

    @Autowired
    private DrugstoreMapper drugstoreMapper;

    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private AreaMapper areaMapper;

    @Autowired
    private SkullMapper skullMapper;

    @Autowired
    private UpplierMapper upplierMapper;

    @Resource
    private IDrugstoreService iDrugstoreService;

    @Autowired
    private BaoqueMapper baoqueMapper;

    @Autowired
    private CaigouMapper caigouMapper;

    @Autowired
    private PharmacyMapper pharmacyMapper;

    @Resource
    private IPharmacyService iPharmacyService;
    /**
     * 入库单
     * 查询药品信息
     *
     * @param drugName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectdgty")
    @ResponseBody
    public Map selectdgty(String drugName, Integer page, Integer limit) {
        Map map = new HashMap();

        List list = iDrugdictionaryService.selectDrugdictionary(drugName, page, limit);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    /**
     * 入库
     *
     * @return
     */
    @RequestMapping("/adddrugs")
    @ResponseBody
    public Integer adddrugs(Integer supplierId, Integer skullId, Integer warehouseId, Integer unit
            , Integer area, Integer type, String drugstoreName, Integer drugstorenum, double tradePrice
            , double sellingPrice, String batch, String produceDate, String validDate
            , String jiluname, String jilutype, String jilupeople, String jilupihao, Integer jilunumber) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("batch", batch);
        queryWrapper.eq("produceDate", produceDate);
        queryWrapper.eq("validDate", validDate);
        queryWrapper.eq("drugstoreName", drugstoreName);

        Drugstore drugstore = drugstoreMapper.selectOne(queryWrapper);

        int row = 0;

        //是否存在
        if (drugstore != null) {
            drugstore.setDrugstorenum(drugstore.getDrugstorenum() + drugstore.getDrugstorenum());
            row = drugstoreMapper.updateById(drugstore);
        } else {

            Drugstore drugstore1 = new Drugstore();
            drugstore1.setDrugstoreName(drugstoreName);
            drugstore1.setSupplierId(supplierId);
            drugstore1.setSkullId(skullId);
            drugstore1.setWarehouseId(warehouseId);
            drugstore1.setUnit(unit);
            drugstore1.setTradePrice(tradePrice);
            drugstore1.setSellingPrice(sellingPrice);
            drugstore1.setArea(area);
            drugstore1.setType(type);
            drugstore1.setProduceDate(sdf.parse(produceDate));
            drugstore1.setValidDate(sdf.parse(validDate));
            drugstore1.setDrugstorenum(drugstorenum);
            drugstore1.setBatch(batch);

            row = drugstoreMapper.insert(drugstore1);
        }

        if (row > 0) {
            //当前时间
            Date date = new Date();
            SimpleDateFormat sdfa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //String 转 Date
            Date time = sdfa.parse(sdfa.format(date));
            Jilu jilu = new Jilu();
            jilu.setJiluname(jiluname);
            jilu.setJilutime(time);
            jilu.setJilutype("常规入库");
            jilu.setJilupeople(jilupeople);
            jilu.setJilunumber(jilunumber);
            jilu.setJilupihao(jilupihao);

            System.err.println(jilu);

            int rows = jiluMapper.insert(jilu);
            return 1;
        }

        return 0;
    }


    /**
     * 获取库存
     *
     * @param drugstoreName
     * @return
     */
    @RequestMapping("/selnumber")
    @ResponseBody
    public Integer selnumber(String drugstoreName, String batch) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("drugstoreName", drugstoreName);

        List list = drugstoreMapper.selectList(queryWrapper);
        int count = 0;
        for (int a = 0; a < list.size(); a++) {
            Drugstore drugstore = (Drugstore) list.get(a);
            count += drugstore.getDrugstorenum();
        }

        return count;
    }

    /**
     * 药品类型
     *
     * @return
     */
    @RequestMapping("/seltype")
    @ResponseBody
    public List seltype() {
        List list = typeMapper.selectList(null);
        return list;
    }

    /**
     * 查询计量类型
     *
     * @return
     */
    @RequestMapping("/selunit")
    @ResponseBody
    public List selunit() {
        List list = unitMapper.selectList(null);
        return list;
    }

    /**
     * 查询产地
     *
     * @return
     */
    @RequestMapping("/selarea")
    @ResponseBody
    public List selarea() {
        List list = areaMapper.selectList(null);
        return list;
    }

    /**
     * 查询经办人
     *
     * @return
     */
    @RequestMapping("/selskull")
    @ResponseBody
    public List selskull() {
        List list = skullMapper.selectList(null);
        return list;
    }

    /**
     * 查询供货单位
     *
     * @return
     */
    @RequestMapping("/selupplier")
    @ResponseBody
    public List selupplier() {
        List list = upplierMapper.selectList(null);
        return list;
    }

    /**
     * 库存查询信息
     *
     * @return
     */
    @RequestMapping("/selectdrugstore")
    @ResponseBody
    public Map selectdrugstore(String drugstoreName, Integer page, Integer limit) {
        Map map = new HashMap();

        List list = iDrugstoreService.selectdrugstore(null,0,drugstoreName, page, limit);

        map.put("code", 0);
        map.put("count", list.size());
        map.put("data", list);
        map.put("msg", "");

        return map;
    }

    /**
     * 修改库存
     *
     * @return
     */
    @RequestMapping("/updrug")
    @ResponseBody
    public String updrug(Drugstore drugstore) {

        int row = drugstoreMapper.updateById(drugstore);
        if (row > 0) {
            return "修改成功";
        }
        return "修改失败";
    }

    /**
     * 库房不足30
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selectlackdrug")
    @ResponseBody
    public Map selectlackdrug(Integer page,Integer limit){
        Map map=new HashMap();

        List list = iDrugstoreService.selectdrugstore(null,1,null, page, limit);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    /**
     * 库房采购单
     * @return
     */
    @RequestMapping("/selcaigou")
    @ResponseBody
    public Map selcaigou(Integer page,Integer limit) {

        Map map=new HashMap();
        Page<Caigou> pages = new Page(page, limit);
        IPage<Caigou> iPage = caigouMapper.selectPage(pages, null);
        List<Caigou> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    /**
     * 添加库房采购单
     * @return
     */
    @RequestMapping("/addcaigou")
    @ResponseBody
    public Integer addcaigou(Caigou caigou){

        int row=caigouMapper.insert(caigou);

        return row;
    }

    /**
     * 删除库房采购单
     * @return
     */
    @RequestMapping("/delcaigou")
    @ResponseBody
    public Integer delcaigou(Integer caigouid){
        int row=caigouMapper.deleteById(caigouid);
        return row;
    }

    /**
     * 过期提醒
     * @return
     */
    @RequestMapping("/seldrugDateguoqi")
    @ResponseBody
    public Map seldrugDateguoqi(Integer page,Integer limit){
        Map map=new HashMap();

        List list = iDrugstoreService.selectdrugstore("null",0,null, page, limit);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }


    /**
     * 删除药品过期的药品
     * @return
     */
    @RequestMapping("/delguoqidurg")
    @ResponseBody
    public Integer delguoqidurg(HttpSession session,Integer rugstoreId,Jilu jilu) throws ParseException {

        SysUser sysUser = (SysUser) session.getAttribute("sysUser");

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String 转 Date
        Date time = sdf.parse(sdf.format(date));
        jilu.setJilutime(time);
        jilu.setJilupeople(sysUser.getRealname());
        int row=jiluMapper.insert(jilu);

        int rows=drugstoreMapper.deleteById(rugstoreId);

        return rows;
    }

}
