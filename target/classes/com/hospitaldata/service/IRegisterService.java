package com.hospitaldata.service;

import com.hospitaldata.entity.Register;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 住院登记 服务类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
public interface IRegisterService extends IService<Register> {
    List selectRegister(@Param("registerid") Integer registerid,@Param("userName") String userName, @Param("page") Integer page, @Param("limit")Integer limit);

    List selectPay(@Param("registerid")Integer registerid,@Param("page") Integer page,@Param("limit")Integer limit);

    /**
     * 查询出院信息
     * @return
     */
    List selectLeaveHospital(@Param("registerId")Integer registerId,@Param("name")String name,@Param("page") Integer page,@Param("limit")Integer limit);

}
