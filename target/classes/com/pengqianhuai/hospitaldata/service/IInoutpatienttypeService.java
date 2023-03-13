package com.pengqianhuai.hospitaldata.service;

import com.pengqianhuai.hospitaldata.entity.Inoutpatienttype;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 住院收费项目 服务类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
public interface IInoutpatienttypeService extends IService<Inoutpatienttype> {
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
