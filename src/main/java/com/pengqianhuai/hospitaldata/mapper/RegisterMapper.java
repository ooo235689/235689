package com.pengqianhuai.hospitaldata.mapper;

import com.pengqianhuai.hospitaldata.entity.Register;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 住院登记 Mapper 接口
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
public interface RegisterMapper extends BaseMapper<Register> {

    /**
     * 查询住院信息
     * @param registerid
     * @param userName
     * @param page
     * @param limit
     * @return
     */
    List selectRegister(@Param("registerid") Integer registerid,@Param("userName") String userName,@Param("page") Integer page,@Param("limit")Integer limit);

    /**
     * 查询对应预约金
     * @param registerid
     * @param page
     * @param limit
     * @return
     */
    List selectPay(@Param("registerid")Integer registerid,@Param("page") Integer page,@Param("limit")Integer limit);

    /**
     * 查询出院信息
     * @return
     */
    List selectLeaveHospital(@Param("registerId")Integer registerId,@Param("name") String name,@Param("page") Integer page,@Param("limit")Integer limit);
}
