package com.hospitaldata.service;

import com.hospitaldata.entity.Drugdictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 药品清单 服务类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
public interface IDrugdictionaryService extends IService<Drugdictionary> {
    List selectDrugdictionary(@Param("drugName")String  drugName, @Param("page")Integer  page, @Param("limit")Integer  limit);

}
