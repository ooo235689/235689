package com.pengqianhuai.hospitaldata.mapper;

import com.pengqianhuai.hospitaldata.entity.Outpatienttype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 门诊收费项目 Mapper 接口
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
public interface OutpatienttypeMapper extends BaseMapper<Outpatienttype> {

    /**
     * 查询项目划价信息
     *
     * @param projectName
     * @param page
     * @param limit
     * @return
     */
    List selectOutpatienttype(@Param("projectName") String projectName, @Param("page") Integer page, @Param("limit") Integer limit);


    /**
     * 查询收费性
     *
     * @param projectName
     * @param page
     * @param limit
     * @return
     */
    List selectOutpatienttypeAndProjecttype(@Param("projectName") String projectName, @Param("page") Integer page, @Param("limit") Integer limit);

}
