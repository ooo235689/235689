package com.pengqianhuai.hospitaldata.service.impl;

import com.pengqianhuai.hospitaldata.entity.SysMenu;
import com.pengqianhuai.hospitaldata.mapper.SysMenuMapper;
import com.pengqianhuai.hospitaldata.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> selectPid(@Param("pid") int pid,@Param("rid") Integer rid) {
        return sysMenuMapper.selectPid(pid,rid);
    }
}
