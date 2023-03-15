package com.hospitaldata.service;

import com.hospitaldata.entity.Drugstore;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 药品库存 服务类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
public interface IDrugstoreService extends IService<Drugstore> {

    /**
     * 库存查询
     * @return
     */
    List selectdrugstore(@Param("time")String time,@Param("select") Integer select,@Param("drugstoreName")String drugstoreName, @Param("page")Integer page, @Param("limit")Integer limit);

}
