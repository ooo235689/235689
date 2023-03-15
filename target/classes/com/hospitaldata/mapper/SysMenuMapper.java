package com.hospitaldata.mapper;

import com.hospitaldata.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Component
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 通过pid查询目录信息
     * @param pid
     * @return
     */
    List<SysMenu> selectPid(@Param("pid") int pid,@Param("rid") Integer rid);

}
