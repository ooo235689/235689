package com.pengqianhuai.hospitaldata.service.impl;

import com.pengqianhuai.hospitaldata.entity.Register;
import com.pengqianhuai.hospitaldata.mapper.RegisterMapper;
import com.pengqianhuai.hospitaldata.service.IRegisterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 住院登记 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements IRegisterService {

    @Autowired
    private RegisterMapper registerMapper;

    public List selectRegister(@Param("registerid") Integer registerid,@Param("userName") String userName, @Param("page") Integer page, @Param("limit") Integer limit){
        return registerMapper.selectRegister(registerid,userName,page,limit);
    }
    public List selectPay(@Param("registerid")Integer registerid,@Param("page") Integer page,@Param("limit")Integer limit){

        return registerMapper.selectPay(registerid,page,limit);
    }

    /**
     * 查询出院信息
     * @return
     */
    public List selectLeaveHospital(@Param("registerId")Integer registerId,@Param("name")String name, @Param("page") Integer page,@Param("limit")Integer limit){
        return registerMapper.selectLeaveHospital(registerId,name,page,limit);
    }

}
