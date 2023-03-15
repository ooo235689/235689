package com.hospitaldata.mapper;

import com.hospitaldata.entity.Bed;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 床位 Mapper 接口
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
public interface BedMapper extends BaseMapper<Bed> {

    /**
     * 查询床位信息
     * @param bedname
     * @param page
     * @param limit
     * @return
     */
    List selectBed(@Param("bedname")String bedname, @Param("page")Integer page, @Param("limit")Integer limit);

}
