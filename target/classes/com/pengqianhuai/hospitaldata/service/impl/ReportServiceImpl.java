package com.pengqianhuai.hospitaldata.service.impl;

import com.pengqianhuai.hospitaldata.entity.Report;
import com.pengqianhuai.hospitaldata.mapper.ReportMapper;
import com.pengqianhuai.hospitaldata.service.IReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 挂号 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List selectReport(@Param("states") Integer states,@Param("state") Integer state,@Param("cc") String cc,@Param("page") Integer page,@Param("limit")Integer limit) {
        return reportMapper.selectReport(states,state,cc,page,limit);
    }


    /**
     * 查询所有患者
     * @param name
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List selectAllReport(@Param("name") String name,@Param("page") Integer page,@Param("limit")Integer limit){
        return reportMapper.selectAllReport(name,page,limit);
    }
}
