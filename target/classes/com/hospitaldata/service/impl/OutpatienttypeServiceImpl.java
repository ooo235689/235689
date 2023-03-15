package com.hospitaldata.service.impl;

import com.hospitaldata.service.IOutpatienttypeService;
import com.hospitaldata.entity.Outpatienttype;
import com.hospitaldata.mapper.OutpatienttypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 门诊收费项目 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class OutpatienttypeServiceImpl extends ServiceImpl<OutpatienttypeMapper, Outpatienttype> implements IOutpatienttypeService {

    @Autowired
    private OutpatienttypeMapper outpatienttypeMapper;
    /**
     * 查询项目划价信息
     * @param projectName
     * @param page
     * @param limit
     * @return
     */
    public List selectOutpatienttype(@Param("projectName")String projectName, @Param("page") Integer page, @Param("limit")Integer limit){
        return outpatienttypeMapper.selectOutpatienttype(projectName,page,limit);
    };

    /**
     * 查询收费性
     * @param projectName
     * @param page
     * @param limit
     * @return
     */
    public List selectOutpatienttypeAndProjecttype(@Param("projectName")String projectName,@Param("page") Integer page,@Param("limit")Integer limit){
        return outpatienttypeMapper.selectOutpatienttypeAndProjecttype(projectName,page,limit);
    }


}
