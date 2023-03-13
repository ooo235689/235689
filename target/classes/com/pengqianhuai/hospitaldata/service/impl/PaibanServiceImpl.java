package com.pengqianhuai.hospitaldata.service.impl;

import com.pengqianhuai.hospitaldata.entity.Paiban;
import com.pengqianhuai.hospitaldata.mapper.PaibanMapper;
import com.pengqianhuai.hospitaldata.service.IPaibanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PaibanServiceImpl extends ServiceImpl<PaibanMapper, Paiban> implements IPaibanService {

    @Autowired
    private PaibanMapper paibanMapper;

    @Override
    public List selectPaiban(String doctorName, Integer page, Integer limit) {
        return paibanMapper.selectPaiban(doctorName,page,limit);
    }
}
