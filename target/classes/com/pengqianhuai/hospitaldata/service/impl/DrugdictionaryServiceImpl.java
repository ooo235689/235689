package com.pengqianhuai.hospitaldata.service.impl;

import com.pengqianhuai.hospitaldata.entity.Drugdictionary;
import com.pengqianhuai.hospitaldata.mapper.DrugdictionaryMapper;
import com.pengqianhuai.hospitaldata.service.IDrugdictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 药品清单 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class DrugdictionaryServiceImpl extends ServiceImpl<DrugdictionaryMapper, Drugdictionary> implements IDrugdictionaryService {

    @Autowired
    private DrugdictionaryMapper drugdictionaryMapper;

    @Override
    public List selectDrugdictionary(@Param("drugName")String  drugName, @Param("page")Integer  page, @Param("limit")Integer  limit) {
        return drugdictionaryMapper.selectDrugdictionary(drugName,page,limit);
    }
}
