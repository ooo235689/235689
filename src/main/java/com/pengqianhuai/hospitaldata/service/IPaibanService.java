package com.pengqianhuai.hospitaldata.service;

import com.pengqianhuai.hospitaldata.entity.Paiban;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
public interface IPaibanService extends IService<Paiban> {
    /**
     * 查询排班信息
     * @param doctorName
     * @param page
     * @param limit
     * @return
     */
    List selectPaiban(@Param("doctorName") String doctorName, @Param("page") Integer page, @Param("limit") Integer limit);
}
