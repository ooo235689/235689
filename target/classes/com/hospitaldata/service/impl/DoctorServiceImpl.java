package com.hospitaldata.service.impl;

import com.hospitaldata.mapper.DoctorMapper;
import com.hospitaldata.entity.Doctor;
import com.hospitaldata.service.IDoctorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 医生 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements IDoctorService {
    @Resource
    private DoctorMapper doctorMapper;

    @Override
    public List selectDoctorBeOnDuty(@Param("week") String week) {
        return doctorMapper.selectDoctorBeOnDuty(week);
    }

    @Override
    /**
     * 查询医生信息
     * @param doctorName
     * @param dstate
     * @param page
     * @param limit
     * @return
     */
    public List selectDoctor(@Param("doctorName")String doctorName,@Param("dstate")Integer dstate,@Param("page")Integer page,@Param("limit")Integer limit){

        return doctorMapper.selectDoctor(doctorName,dstate,page,limit);
    }

}
