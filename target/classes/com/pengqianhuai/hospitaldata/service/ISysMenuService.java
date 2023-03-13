package com.pengqianhuai.hospitaldata.service;

import com.pengqianhuai.hospitaldata.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 通过pid查询目录信息和rid
     * @param pid
     * @return
     */
    List<SysMenu> selectPid(@Param("pid") int pid,@Param("rid") Integer rid);
}
