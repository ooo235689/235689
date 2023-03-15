package com.hospitaldata.service.impl;

import com.hospitaldata.entity.Cashier;
import com.hospitaldata.mapper.CashierMapper;
import com.hospitaldata.service.ICashierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 出纳 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class CashierServiceImpl extends ServiceImpl<CashierMapper, Cashier> implements ICashierService {

    @Autowired
    private CashierMapper cashierMapper;

    /**
     * 门诊当天统计
     * @param time
     * @return
     */
    public List outpatientTodayStatistics(@Param("time")String time){
        return cashierMapper.outpatientTodayStatistics(time);
    }

    /**
     * 门诊年度统计
     * @return
     */
    public List outpatientYearStatistics(){
        return cashierMapper.outpatientYearStatistics();
    }

    /**
     * 住院年度统计
     * @return
     */
    public List hospitalizationYearStatistics(){
        return cashierMapper.hospitalizationYearStatistics();
    }

    /**
     * 门诊月度统计
     * @return
     */
    public List outpatientMonthStatistics(@Param("year")String year){
        return cashierMapper.outpatientMonthStatistics(year);
    }

    /**
     * 住院月度统计
     * @param month
     * @return
     */
   public List hospitalizationMonthStatistics(@Param("year")String year){
       return cashierMapper.hospitalizationMonthStatistics(year);
   }

}
