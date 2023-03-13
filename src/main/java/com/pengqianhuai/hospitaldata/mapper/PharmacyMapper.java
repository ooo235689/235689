package com.pengqianhuai.hospitaldata.mapper;

import com.pengqianhuai.hospitaldata.entity.Pharmacy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 药房 Mapper 接口
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
public interface PharmacyMapper extends BaseMapper<Pharmacy> {

    /**
     * 查询药品和药品单位连表
     * @param page
     * @param limit
     * @param name
     * @return
     */
    List selectPharmacy(@Param("page") Integer page,@Param("limit") Integer limit,@Param("name") String name);

    /**
     * 查询药品,药品类型和药品单位连表
     * @param page
     * @param limit
     * @param name
     * @return
     */
    List selectPharmacyType(@Param("page") Integer page,@Param("limit") Integer limit,@Param("name") String name,@Param("pharmacyId") Integer pharmacyId);


    /**
     * 药房详情
     * @param page
     * @param limit
     * @param pharmacyName
     * @return
     */
    List selectpharmacy2(@Param("page") Integer page,@Param("limit") Integer limit,@Param("pharmacyName")String pharmacyName,@Param("time")String time);

}
