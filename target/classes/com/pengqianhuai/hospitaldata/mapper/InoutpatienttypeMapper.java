package com.pengqianhuai.hospitaldata.mapper;

import com.pengqianhuai.hospitaldata.entity.Inoutpatienttype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 住院收费项目 Mapper 接口
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
public interface InoutpatienttypeMapper extends BaseMapper<Inoutpatienttype> {

    List selectInoutpatienttype(@Param("page") Integer page, @Param("limit") Integer limit, @Param("projectName") String projectName);

    /**
     * 项目查询
     * @param page
     * @param limit
     * @param projectName
     * @return
     */
    List selectInoutpatienttypeAll(@Param("page") Integer page, @Param("limit") Integer limit, @Param("projectName") String projectName);

}
