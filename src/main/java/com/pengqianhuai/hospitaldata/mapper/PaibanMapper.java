package com.pengqianhuai.hospitaldata.mapper;

import com.pengqianhuai.hospitaldata.entity.Paiban;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
public interface PaibanMapper extends BaseMapper<Paiban> {

    /**
     * 查询排班信息
     * @param doctorName
     * @param page
     * @param limit
     * @return
     */
    List selectPaiban(@Param("doctorName") String doctorName, @Param("page") Integer page, @Param("limit") Integer limit);

}
