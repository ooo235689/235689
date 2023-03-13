package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.*;
import com.pengqianhuai.hospitaldata.mapper.*;
import com.pengqianhuai.hospitaldata.service.IInoutpatienttypeService;
import com.pengqianhuai.hospitaldata.service.IPharmacyService;
import com.pengqianhuai.hospitaldata.service.IRegisterService;
import com.pengqianhuai.hospitaldata.service.IReportService;
import com.pengqianhuai.hospitaldata.util.HospitalPriceUtil;
import com.pengqianhuai.hospitaldata.util.PayTotalPrice;
import com.pengqianhuai.hospitaldata.util.reportUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 住院登记 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/liao")
public class RegisterController {

    @Autowired
    private RegisterMapper registerMapper;

    @Resource
    private IRegisterService iRegisterService;

    @Autowired
    private MoneytypeMapper moneytypeMapper;

    @Autowired
    private DepartmentsMapper departmentsMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private BedMapper bedMapper;

    @Resource
    private IReportService iReportService;

    @Autowired
    private PayMapper payMapper;

    @Autowired
    private HospitalpriceMapper hospitalpriceMapper;

    @Autowired
    private PharmacyMapper pharmacyMapper;

    @Resource
    private IPharmacyService iPharmacyService;

    @Resource
    private IInoutpatienttypeService iInoutpatienttypeService;

    @RequestMapping("/admin")
    public String admin() {
        return "liao/admin";
    }

    /**
     * 住院用户信息
     *
     * @param userName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selRegister")
    @ResponseBody
    public Map selRegister(String userName, Integer page, Integer limit) {
        Map map = new HashMap();

        List list = iRegisterService.selectRegister(0, userName, page, limit);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    /**
     * 医保
     *
     * @return
     */
    @RequestMapping("/selDis")
    @ResponseBody
    public List selDis() {

        List list = moneytypeMapper.selectList(null);
        return list;
    }

    /**
     * 科室
     *
     * @return
     */
    @RequestMapping("/selDepartment")
    @ResponseBody
    public List selDepartment() {


        List list = departmentsMapper.selectList(null);

        return list;
    }

    /**
     * 医生
     *
     * @param departmentId
     * @return
     */
    @RequestMapping("/selDoctor")
    @ResponseBody
    public List selDoctor(Integer departmentId) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("departmentId", departmentId);
        queryWrapper.eq("dstate", 1);

        List list = doctorMapper.selectList(queryWrapper);

        return list;
    }

    /**
     * 床位
     *
     * @param departmentId
     * @return
     */
    @RequestMapping("/selBed")
    @ResponseBody
    public List selBed(Integer departmentId) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("departmentId", departmentId);
        queryWrapper.eq("state", 0);

        List list = bedMapper.selectList(queryWrapper);

        return list;
    }

    /**
     * 转科
     *
     * @return
     */
    @RequestMapping("/updKe")
    @ResponseBody
    public String updKe(Integer registerid, Integer departmentId, Integer doctorId, Integer bedId) {
        if (departmentId == null) {
            return "科室不能为空";
        }
        if (doctorId == null) {
            return "医生不能为空";
        }
        if (bedId == null) {
            return "床位不能为空";
        }

        //旧
        Register former = registerMapper.selectById(registerid);

        Register register = new Register();
        register.setRegisterid(registerid);
        register.setDepartment(departmentId);
        register.setDoctor(doctorId);
        register.setBedNum(bedId);
        //更换床位，科室，医生
        int row = registerMapper.updateById(register);

        //旧床位空出来，变可以
        Bed bed = bedMapper.selectById(former.getBedNum());
        bed.setState(0);
        int row1 = bedMapper.updateById(bed);
        System.err.println("---------");
        //新床位在在使用中
        Register news = registerMapper.selectById(registerid);
        Bed bed1 = bedMapper.selectById(bedId);
        bed1.setState(1);
        int row2 = bedMapper.updateById(bed1);

        if (row > 0) {
            return "变更成功";
        }

        return "变更失败";
    }


    @RequestMapping("/selDoor")
    @ResponseBody
    public Map selDoor(Integer page, Integer limit) {
        Map map = new HashMap();

//        List report = iReportService.selectReport(4,1,null,page,limit);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state", 4);

        List report = reportMapper.selectList(queryWrapper);

        List list = new ArrayList();

        for (int a = 0; a < report.size(); a++) {
            Report report1 = (Report) report.get(a);
            reportUtil reportUtil = new reportUtil();

            reportUtil.setAge(report1.getAge());
            reportUtil.setDiagnose(report1.getZhuan());
            reportUtil.setIdcard(report1.getCarid());
            reportUtil.setPhone(report1.getPhone());
            reportUtil.setReportid(report1.getReportId());
            reportUtil.setSex(report1.getSex());
            reportUtil.setUserName(report1.getReportName());
            list.add(reportUtil);
        }


        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    @RequestMapping("/addRegister")
    @ResponseBody
    public String addRegister(HttpSession session, reportUtil reportUtil) throws ParseException {

        SysUser sysUser = (SysUser) session.getAttribute("sysUser");

        Register register = new Register();
        register.setUserName(reportUtil.getUserName());
        register.setDiagnose(reportUtil.getDiagnose());
        register.setSex(reportUtil.getSex());
        register.setAge(reportUtil.getAge());
        register.setDepartment(reportUtil.getDepartmentId());
        register.setDoctor(reportUtil.getDoctorId());
        register.setAddress(reportUtil.getAddress());
        register.setBedNum(reportUtil.getBedId());
        register.setPhone(reportUtil.getPhone());
        register.setMoney(reportUtil.getMoney());
        register.setIdcard(reportUtil.getIdcard());
        register.setDiscount(reportUtil.getDiscount());
        register.setState(0);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String 转 Date
        Date time = sdf.parse(sdf.format(date));

        register.setRegisterDate(time);
        register.setOperator(sysUser.getRealname());

        //添加住院信息
        int row = registerMapper.insert(register);

        if (row > 0) {

            //该床位已使用
            Bed bed = new Bed();
            bed.setBedId(register.getBedNum());
            bed.setState(1);
            int row1 = bedMapper.updateById(bed);

            System.err.println("id:" + reportUtil.getReportid());

            //修改患者表，状态改为住院
            Report report = new Report();
            report.setReportId(reportUtil.getReportid());
            report.setState(5);
            int row2 = reportMapper.updateById(report);

            return "添加成功";
        }


        return "添加失败";
    }

    /**
     * 进入缴费管理
     *
     * @return
     */
    @RequestMapping("/pay")
    public String pay() {
        return "liao/pay";
    }

    /**
     * 查看对应用户的住院信息 和 添加预约金
     *
     * @return
     */
    @RequestMapping("/selPay")
    @ResponseBody
    public List selPay(HospitalPriceUtil hospitalPriceUtil) throws ParseException {

        List list = iRegisterService.selectRegister(hospitalPriceUtil.getRegisterid(), null, null, null);

        if (hospitalPriceUtil.getMoneys() != 0) {
            Pay pay = new Pay();
            pay.setRegisterid(hospitalPriceUtil.getRegisterid());
            pay.setMoney(hospitalPriceUtil.getMoneys());

            //添加当前时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //String 转 Date
            Date time = sdf.parse(sdf.format(date));
            pay.setPayDate(time);

            int row = payMapper.insert(pay);

            Register register = registerMapper.selectById(hospitalPriceUtil.getRegisterid());

            register.setMoney(register.getMoney() + hospitalPriceUtil.getMoneys());

            int rows = registerMapper.updateById(register);

        }

        return list;
    }

    /**
     * 预约金
     *
     * @return
     */
    @RequestMapping("/selPays")
    @ResponseBody
    public Map selPays(HospitalPriceUtil hospitalPriceUtil) {
        Map map = new HashMap();
        List list = new ArrayList();
        if (hospitalPriceUtil.getUserName() != null) {
            list = registerMapper.selectPay(hospitalPriceUtil.getRegisterid(), hospitalPriceUtil.getPage(), hospitalPriceUtil.getLimit());
        }
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    /**
     * 计算已用
     *
     * @return
     */
    @RequestMapping("/selSurplus")
    @ResponseBody
    public List selSurplus(HospitalPriceUtil hospitalPriceUtil) {

        List list = new ArrayList();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("registerid", hospitalPriceUtil.getRegisterid());
        queryWrapper.in("state", 0, 1);

        List listHospitalprice = hospitalpriceMapper.selectList(queryWrapper);
        double total = 0;
        for (int a = 0; a < listHospitalprice.size(); a++) {
            Hospitalprice hospitalprice = (Hospitalprice) listHospitalprice.get(a);
            total += hospitalprice.getRepicetotal();
        }

        Register register = registerMapper.selectById(hospitalPriceUtil.getRegisterid());

        String discount = register.getDiscount();

        double dis = Double.valueOf(discount.substring(0, discount.indexOf("%"))) / 100;
        PayTotalPrice payTotalPrice = new PayTotalPrice();

        //double保留两位小数
        NumberFormat nbf = NumberFormat.getInstance();
        nbf.setMinimumFractionDigits(2);
        payTotalPrice.setRepicetotal(Double.valueOf(nbf.format(total - total * dis)));

        list.add(payTotalPrice);
        return list;
    }

    /**
     * 进入药品记账
     *
     * @return
     */
    @RequestMapping("/drug")
    public String drug() {
        return "liao/drug";
    }

    /**
     * 查询药品信息
     *
     * @return
     */
    @RequestMapping("/selDrug")
    @ResponseBody
    public Map selDrug(HospitalPriceUtil hospitalPriceUtil) {
        Map map = new HashMap();
        System.err.println(hospitalPriceUtil);
        List list = iPharmacyService.selectPharmacyType(hospitalPriceUtil.getPage(), hospitalPriceUtil.getLimit(), hospitalPriceUtil.getPharmacyName(), null);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    /**
     * 当前住院用户的药品信息
     *
     * @return
     */
    @RequestMapping("/selDrugs")
    @ResponseBody
    public Map selDrugs(HospitalPriceUtil hospitalPriceUtil) {
        Map map = new HashMap();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state", 0);
        queryWrapper.eq("registerId", hospitalPriceUtil.getRegisterid());

        Page<Hospitalprice> pages = new Page(hospitalPriceUtil.getPage(), hospitalPriceUtil.getLimit());
        IPage<Hospitalprice> iPage = hospitalpriceMapper.selectPage(pages, queryWrapper);
        List<Hospitalprice> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", iPage.getTotal());
        map.put("data", list);

        return map;
    }

    /**
     * 删除当前用户的药品
     *
     * @param hospitalpriceid
     * @param durgname
     * @param durgnum
     * @return
     */
    @RequestMapping("/delDrug")
    @ResponseBody
    public String delDrug(Integer hospitalpriceid, String durgname, Integer durgnum) {

        //删除用户的药品信息
        Hospitalprice hospitalprice = hospitalpriceMapper.selectById(hospitalpriceid);

        //删除
        int row = hospitalpriceMapper.deleteById(hospitalpriceid);

        Register register = registerMapper.selectById(hospitalprice.getRegisterId());

        if (row > 0) {

            //修改库存
            QueryWrapper query = new QueryWrapper();
            query.eq("pharmacyName", durgname);
            Pharmacy pharmacy = pharmacyMapper.selectOne(query);
            pharmacy.setDrugstorenum(pharmacy.getDrugstorenum() + durgnum);
            pharmacyMapper.updateById(pharmacy);

            return "移出成功";
        }
        return "移出失败";
    }

    /**
     * 查看药品的库存
     *
     * @param pharmacyId
     * @return
     */
    @RequestMapping("/selNum")
    @ResponseBody
    public List selNum(Integer pharmacyId) {

        List list = iPharmacyService.selectPharmacyType(null, null, null, pharmacyId);

        return list;
    }

    /**
     * 添加住院用户药品
     *
     * @return
     */
    @RequestMapping("/addDrug")
    @ResponseBody
    public String addDrug(Integer durgnum, Integer registerid, String durgname, Integer repiceprice, Integer pharmacyId) throws ParseException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state", 0);
        queryWrapper.eq("registerId", registerid);
        queryWrapper.eq("durgname", durgname);

        Hospitalprice hospital = hospitalpriceMapper.selectOne(queryWrapper);

        if (hospital == null) {
            Pharmacy pharmacy1 = pharmacyMapper.selectById(pharmacyId);

            Hospitalprice hospitalprice = new Hospitalprice();
            hospitalprice.setRegisterId(registerid);
            hospitalprice.setDurgname(durgname);
            hospitalprice.setDurgnum(durgnum);
            hospitalprice.setRepiceprice(pharmacy1.getSellingPrice());
            hospitalprice.setRepicetotal(durgnum * pharmacy1.getSellingPrice());
            hospitalprice.setState(0);

            //添加当前时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //String 转 Date
            Date time = sdf.parse(sdf.format(date));
            hospitalprice.setHtime(time);

            int row = hospitalpriceMapper.insert(hospitalprice);

            if (row > 0) {
                QueryWrapper queryWrapper1 = new QueryWrapper();
                queryWrapper1.eq("pharmacyId", pharmacyId);
                Pharmacy pharmacy = pharmacyMapper.selectOne(queryWrapper1);

                pharmacy.setDrugstorenum(pharmacy.getDrugstorenum() - durgnum);

                pharmacyMapper.updateById(pharmacy);

                return "添加成功";
            }
        } else {
            //修改数量
            Pharmacy pharmacy = pharmacyMapper.selectById(pharmacyId);
            pharmacy.setDrugstorenum(pharmacy.getDrugstorenum() - durgnum);
            pharmacyMapper.updateById(pharmacy);
            int row = pharmacyMapper.updateById(pharmacy);

//pharmacyMapper.update(pharmacy, queryWrapper);

            hospital.setDurgnum(hospital.getDurgnum() + durgnum);
            hospital.setRepicetotal(hospital.getDurgnum() * durgnum * hospital.getRepiceprice());
            int row1 = hospitalpriceMapper.updateById(hospital);
            return "添加成功";
        }

        return "添加失败";
    }

    @RequestMapping("/item")
    public String item() {
        return "liao/item";
    }

    /**
     * 项目信息
     *
     * @param hospitalPriceUtil
     * @return
     */
    @RequestMapping("/selItems")
    @ResponseBody
    public Map selItems(HospitalPriceUtil hospitalPriceUtil) {
        Map map = new HashMap();
        System.err.println(hospitalPriceUtil);
        System.err.println(hospitalPriceUtil.getProjectName());
        List list = iInoutpatienttypeService.selectInoutpatienttype(hospitalPriceUtil.getPage(), hospitalPriceUtil.getLimit(), hospitalPriceUtil.getProjectName());

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    @RequestMapping("/selItem")
    @ResponseBody
    public Map selItem(HospitalPriceUtil hospitalPriceUtil) {
        Map map = new HashMap();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("registerId", hospitalPriceUtil.getRegisterid());
        queryWrapper.eq("state", 1);

        List list = hospitalpriceMapper.selectList(queryWrapper);
        System.err.println(list);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    /**
     * 添加项目
     *
     * @param durgname
     * @param repiceprice
     * @param registerid
     * @return
     * @throws ParseException
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public String selItem(String durgname, Double repiceprice, Integer registerid) throws ParseException {

        Hospitalprice hospitalprice = new Hospitalprice();

        hospitalprice.setRegisterId(registerid);
        hospitalprice.setDurgname(durgname);
        hospitalprice.setDurgnum(1);
        hospitalprice.setRepiceprice(repiceprice);
        hospitalprice.setRepicetotal(repiceprice);

        //添加当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String 转 Date
        Date time = sdf.parse(sdf.format(date));
        hospitalprice.setHtime(time);
        hospitalprice.setState(1);

        int row = hospitalpriceMapper.insert(hospitalprice);

        if (row > 0) {
            return "添加成功";
        }

        return "添加失败";
    }

    @RequestMapping("/leave")
    public String leave() {
        return "liao/leave";
    }

    /**
     * 可以出院
     * @return
     */
    @RequestMapping("/selRegisters")
    @ResponseBody
    public Map selRegisters(HospitalPriceUtil hospitalPriceUtil) {
        Map map = new HashMap();

        List list = iRegisterService.selectLeaveHospital(null,hospitalPriceUtil.getUserName(),hospitalPriceUtil.getPage(),hospitalPriceUtil.getLimit());


        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    /**
     * 查询药品信息
     * @param registerid
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selPhar")
    @ResponseBody
    public Map selPhar(Integer registerid,Integer page,Integer limit){
        Map map = new HashMap();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state", 2);
        queryWrapper.eq("registerId", registerid);

        Page<Hospitalprice> pages = new Page(page, limit);
        IPage<Hospitalprice> iPage = hospitalpriceMapper.selectPage(pages, queryWrapper);
        List<Hospitalprice> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", iPage.getTotal());
        map.put("data", list);
        return map;
    }

    /**
     * 出院结算
     * 用户信息个人详情
     * @return
     */
    @RequestMapping("/selregis")
    @ResponseBody
    public List selregis(Integer registerid){
        Map map=new HashMap();
        List list = iRegisterService.selectLeaveHospital(registerid,null,null,null);


        return list;
    }

    @RequestMapping("/pharmacy")
    public String pharmacy(){

        return "liao/pharmacy";
    }

    /**
     * 取药
     * @return
     */
    @RequestMapping("/updDrug")
    @ResponseBody
    public String updDrug(HospitalPriceUtil hospitalPriceUtil){

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("registerId",hospitalPriceUtil.getRegisterid());
        queryWrapper.eq("state",0);

        Hospitalprice hospitalprice=new Hospitalprice();
        hospitalprice.setState(2);
        int row=hospitalpriceMapper.update(hospitalprice,queryWrapper);

        if(row>0){
            return "取药成功";
        }

        return "取药失败";
    }

}
