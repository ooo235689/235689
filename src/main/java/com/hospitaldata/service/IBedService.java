package com.hospitaldata.service;

import com.hospitaldata.entity.Bed;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 床位 服务类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
public interface IBedService extends IService<Bed> {
    /**
     * 查询床位信息
     * @param bedname
     * @param page
     * @param limit
     * @return
     */
    List selectBed(@Param("bedname")String bedname, @Param("page")Integer page, @Param("limit")Integer limit);

}
