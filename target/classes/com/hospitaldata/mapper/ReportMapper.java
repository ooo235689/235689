package com.hospitaldata.mapper;

import com.hospitaldata.entity.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 挂号 Mapper 接口
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
public interface ReportMapper extends BaseMapper<Report> {
    /**
     * 查询挂号信息
     */
    List selectReport(@Param("states") Integer states, @Param("state") Integer state,@Param("cc") String cc,@Param("page") Integer page,@Param("limit")Integer limit);


    /**
     * 查询所有患者
     * @param name
     * @param page
     * @param limit
     * @return
     */
    List selectAllReport(@Param("name") String name,@Param("page") Integer page,@Param("limit")Integer limit);
}
