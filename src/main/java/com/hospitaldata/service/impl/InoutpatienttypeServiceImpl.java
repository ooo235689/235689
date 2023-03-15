package com.hospitaldata.service.impl;

import com.hospitaldata.entity.Inoutpatienttype;
import com.hospitaldata.mapper.InoutpatienttypeMapper;
import com.hospitaldata.service.IInoutpatienttypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 住院收费项目 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class InoutpatienttypeServiceImpl extends ServiceImpl<InoutpatienttypeMapper, Inoutpatienttype> implements IInoutpatienttypeService {

    @Autowired
    private InoutpatienttypeMapper inoutpatienttypeMapper;

    public List selectInoutpatienttype(@Param("page") Integer page, @Param("limit") Integer limit, @Param("projectName") String projectName){
        return inoutpatienttypeMapper.selectInoutpatienttype(page,limit,projectName);
    }

    public List selectInoutpatienttypeAll(@Param("page") Integer page, @Param("limit") Integer limit, @Param("projectName") String projectName){
        return inoutpatienttypeMapper.selectInoutpatienttypeAll(page,limit,projectName);

    }
}
