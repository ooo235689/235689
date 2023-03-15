package com.hospitaldata.service.impl;

import com.hospitaldata.entity.Pay;
import com.hospitaldata.service.IPayService;
import com.hospitaldata.mapper.PayMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 预交金记录 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements IPayService {

}
