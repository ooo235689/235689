package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengqianhuai.hospitaldata.entity.Baoque;
import com.pengqianhuai.hospitaldata.entity.Cashier;
import com.pengqianhuai.hospitaldata.entity.Huishou;
import com.pengqianhuai.hospitaldata.entity.SysUser;
import com.pengqianhuai.hospitaldata.mapper.BaoqueMapper;
import com.pengqianhuai.hospitaldata.mapper.HuishouMapper;
import com.pengqianhuai.hospitaldata.mapper.PharmacyMapper;
import com.pengqianhuai.hospitaldata.service.IPharmacyService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 药房 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/pharmacy")
public class PharmacyController {

    @Autowired
    private PharmacyMapper pharmacyMapper;

    @Resource
    private IPharmacyService iPharmacyService;

    @Autowired
    private BaoqueMapper baoqueMapper;

    @Autowired
    private HuishouMapper huishouMapper;

    @RequestMapping("/selectpharmacy")
    @ResponseBody
    public Map selectpharmacy(String pharmacyName, Integer page, Integer limit) {
        Map map = new HashMap();

        List list = iPharmacyService.selectpharmacy2(page, limit, pharmacyName,null);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    /**
     * 药房添加报缺
     *
     * @return
     */
    @RequestMapping("/addbaoque")
    @ResponseBody
    public Integer addbaoque(Baoque baoque) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("baoqueName", baoque.getBaoqueName());

        Baoque baoque1 = baoqueMapper.selectOne(queryWrapper);

        int row = 0;

        if (baoque1==null||"".equals(baoque1)) {
            row = baoqueMapper.insert(baoque);
        } else {
            baoque1.setBaoqueNum(baoque1.getBaoqueNum() + baoque.getBaoqueNum());
            row = baoqueMapper.updateById(baoque1);
        }
        if (row > 0) {
            return 1;
        }

        return 0;
    }

    /**
     * 库房回收
     *
     * @return
     */
    @RequestMapping("delpharymary")
    @ResponseBody
    public Integer delpharymary(HttpSession session, Integer pharmacyId, Huishou huishou) {

        int rows = pharmacyMapper.deleteById(pharmacyId);

        SysUser sysUser = (SysUser) session.getAttribute("sysUser");

        System.err.println(sysUser.getLoginname());

        huishou.setJbr(sysUser.getRealname());

        int row = huishouMapper.insert(huishou);

        if (row > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * 药品回收信息
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/selecthuishou")
    @ResponseBody
    public Map selecthuishou(Integer page, Integer limit) {
        Map map = new HashMap();
        Page<Huishou> pages = new Page(page, limit);
        IPage<Huishou> iPage = huishouMapper.selectPage(pages, null);
        List<Huishou> list = iPage.getRecords();

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

}