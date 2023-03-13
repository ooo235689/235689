package com.pengqianhuai.hospitaldata.service;

import com.pengqianhuai.hospitaldata.entity.Pharmacy;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 药房 服务类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
public interface IPharmacyService extends IService<Pharmacy> {
    /**
     * 查询药品和药品单位连表
     * @param page
     * @param limit
     * @param name
     * @return
     */
    List selectPharmacy(@Param("page") Integer page, @Param("limit") Integer limit, @Param("name") String name);

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
