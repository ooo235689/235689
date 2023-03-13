package com.pengqianhuai.hospitaldata.service;

import com.pengqianhuai.hospitaldata.entity.Outpatienttype;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 门诊收费项目 服务类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
public interface IOutpatienttypeService extends IService<Outpatienttype> {
    /**
     * 查询项目划价信息
     * @param projectName
     * @param page
     * @param limit
     * @return
     */
    List selectOutpatienttype(@Param("projectName")String projectName, @Param("page") Integer page, @Param("limit")Integer limit);

    /**
     * 查询收费性
     * @param projectName
     * @param page
     * @param limit
     * @return
     */
    List selectOutpatienttypeAndProjecttype(@Param("projectName")String projectName,@Param("page") Integer page,@Param("limit")Integer limit);

}
