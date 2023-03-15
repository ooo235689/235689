package com.hospitaldata.mapper;

import com.hospitaldata.entity.Doctor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 医生 Mapper 接口
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
public interface DoctorMapper extends BaseMapper<Doctor> {
    /**
     * 查询医生上班时间
     * @return
     */
    List selectDoctorBeOnDuty(@Param("week") String week);


    /**
     * 查询医生信息
     * @param doctorName
     * @param dstate
     * @param page
     * @param limit
     * @return
     */
    List selectDoctor(@Param("doctorName")String doctorName,@Param("dstate")Integer dstate,@Param("page")Integer page,@Param("limit")Integer limit);

}
