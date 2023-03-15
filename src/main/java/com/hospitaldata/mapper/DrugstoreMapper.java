package com.hospitaldata.mapper;

import com.hospitaldata.entity.Drugstore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 药品库存 Mapper 接口
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
public interface DrugstoreMapper extends BaseMapper<Drugstore> {

    /**
     * 库存查询
     * @return
     */
    List selectdrugstore(@Param("time")String time,@Param("select") Integer select,@Param("drugstoreName")String drugstoreName,@Param("page")Integer page,@Param("limit")Integer limit);

}
