package com.pengqianhuai.hospitaldata.mapper;

import com.pengqianhuai.hospitaldata.entity.Cashier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 出纳 Mapper 接口
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
public interface CashierMapper extends BaseMapper<Cashier> {

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
