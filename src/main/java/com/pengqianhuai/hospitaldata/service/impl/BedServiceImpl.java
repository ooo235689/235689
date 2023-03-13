package com.pengqianhuai.hospitaldata.service.impl;

import com.pengqianhuai.hospitaldata.entity.Bed;
import com.pengqianhuai.hospitaldata.mapper.BedMapper;
import com.pengqianhuai.hospitaldata.service.IBedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 床位 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class BedServiceImpl extends ServiceImpl<BedMapper, Bed> implements IBedService {

    @Autowired
    private BedMapper bedMapper;

    /**
     * 查询床位信息
     * @param bedname
     * @param page
     * @param limit
     * @return
     */
    public List selectBed(@Param("bedname")String bedname, @Param("page")Integer page, @Param("limit")Integer limit){
        return bedMapper.selectBed(bedname,page,limit);
    }

}
