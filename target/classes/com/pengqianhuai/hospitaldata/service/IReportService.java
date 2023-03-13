package com.pengqianhuai.hospitaldata.service;

import com.pengqianhuai.hospitaldata.entity.Report;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 挂号 服务类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
public interface IReportService extends IService<Report> {
    /**
     * 查询挂号信息
     */
    List selectReport(@Param("states") Integer states,
                      @Param("state") Integer state,
                      @Param("cc") String cc,
                      @Param("page") Integer page,
                      @Param("limit") Integer limit);

    /**
     * 查询所有患者
     *
     * @param name
     * @param page
     * @param limit
     * @return
     */
    List selectAllReport(@Param("name") String name, @Param("page") Integer page, @Param("limit") Integer limit);
}
