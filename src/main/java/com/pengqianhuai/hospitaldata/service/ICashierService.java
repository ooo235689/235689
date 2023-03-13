package com.pengqianhuai.hospitaldata.service;

import com.pengqianhuai.hospitaldata.entity.Cashier;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 出纳 服务类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */

public interface ICashierService extends IService<Cashier> {
    /**
     * 门诊当天统计
     * @param time
     * @return
     */
    List outpatientTodayStatistics(@Param("time")String time);

    /**
     * 门诊年度统计
     * @return
     */
    List outpatientYearStatistics();

    /**
     * 住院年度统计
     * @return
     */
    List hospitalizationYearStatistics();

    /**
     * 门诊月度统计
     * @return
     */
    List outpatientMonthStatistics(@Param("year")String year);

    /**
     * 住院月度统计
     * @param month
     * @return
     */
    List hospitalizationMonthStatistics(@Param("year")String year);

}
