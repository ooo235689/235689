package com.pengqianhuai.hospitaldata.service.impl;

import com.pengqianhuai.hospitaldata.entity.Ban;
import com.pengqianhuai.hospitaldata.mapper.BanMapper;
import com.pengqianhuai.hospitaldata.service.IBanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 是否为无班和有班 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class BanServiceImpl extends ServiceImpl<BanMapper, Ban> implements IBanService {

}
