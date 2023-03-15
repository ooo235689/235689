package com.hospitaldata.mapper;

import com.hospitaldata.entity.Drugdictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 药品清单 Mapper 接口
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
public interface DrugdictionaryMapper extends BaseMapper<Drugdictionary> {

    /**
     * 查询药品清单
     * @param drugName
     * @param page
     * @param limit
     * @return
     */
    List selectDrugdictionary(@Param("drugName")String  drugName,@Param("page")Integer  page,@Param("limit")Integer  limit);

}
