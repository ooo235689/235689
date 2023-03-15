package com.hospitaldata.service;

import com.hospitaldata.entity.Doctor;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 医生 服务类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
public interface IDoctorService extends IService<Doctor> {

    /**
     * 查询医生上班时间
     * @return
     */
    List selectDoctorBeOnDuty(String week);

    /**
     * 查询医生信息
     * @param doctorName
     * @param dstate
     * @param page
     * @param limit
     * @return
     */
    List selectDoctor(@Param("doctorName")String doctorName, @Param("dstate")Integer dstate, @Param("page")Integer page, @Param("limit")Integer limit);

}
