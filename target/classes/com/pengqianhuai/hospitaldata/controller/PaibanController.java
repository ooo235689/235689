package com.pengqianhuai.hospitaldata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengqianhuai.hospitaldata.entity.Paiban;
import com.pengqianhuai.hospitaldata.mapper.BanMapper;
import com.pengqianhuai.hospitaldata.mapper.PaibanMapper;
import com.pengqianhuai.hospitaldata.service.IPaibanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Controller
@RequestMapping("/paiban")
public class PaibanController {

    @Autowired
    private PaibanMapper paibanMapper;

    @Resource
    private IPaibanService iPaibanService;

    @Autowired
    private BanMapper banMapper;

    /**
     * 查询上班类型
     *
     * @return
     */
    @RequestMapping("/findAllBan")
    @ResponseBody
    public List findAllBan() {
        List list = banMapper.selectList(null);
        return list;
    }

    /**
     * 查询医生上班信息
     *
     * @param doctorName
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAllPaiban")
    @ResponseBody
    public Map findAllPaiban(String doctorName, Integer page, Integer limit) {

        Map map = new HashMap<>();

        List list = iPaibanService.selectPaiban(doctorName, page, limit);

        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);

        return map;
    }

    /**
     * 修改医生的上班时间
     *
     * @return
     */
    @RequestMapping("/editPaiban")
    @ResponseBody
    public String editPaiban(Paiban paiban) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("doctorId", paiban.getDoctorId());

        int row = paibanMapper.update(paiban, queryWrapper);

        if (row > 0) {
            return "修改成功";
        }
        return "修改失败";
    }

}
