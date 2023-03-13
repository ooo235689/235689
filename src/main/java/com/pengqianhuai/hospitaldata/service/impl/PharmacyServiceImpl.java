package com.pengqianhuai.hospitaldata.service.impl;

import com.pengqianhuai.hospitaldata.entity.Pharmacy;
import com.pengqianhuai.hospitaldata.mapper.PharmacyMapper;
import com.pengqianhuai.hospitaldata.service.IPharmacyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 药房 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class PharmacyServiceImpl extends ServiceImpl<PharmacyMapper, Pharmacy> implements IPharmacyService {

    @Autowired
    private PharmacyMapper pharmacyMapper;

    /**
     * 查询药品和药品单位连表
     * @param page
     * @param limit
     * @param name
     * @return
     */
    @Override
    public List selectPharmacy(@Param("page") Integer page, @Param("limit") Integer limit, @Param("name") String name) {
        return pharmacyMapper.selectPharmacy(page,limit,name);
    }

    @Override
    public List selectPharmacyType(@Param("page") Integer page,@Param("limit") Integer limit,@Param("name") String name,@Param("pharmacyId") Integer pharmacyId){
        return pharmacyMapper.selectPharmacyType(page,limit,name,pharmacyId);
    }

    /**
     * 药房详情
     * @param page
     * @param limit
     * @param pharmacyName
     * @return
     */
    public List selectpharmacy2(@Param("page") Integer page,@Param("limit") Integer limit,@Param("pharmacyName")String pharmacyName,@Param("time")String time){
        return pharmacyMapper.selectpharmacy2(page,limit,pharmacyName,time);
    }


}

