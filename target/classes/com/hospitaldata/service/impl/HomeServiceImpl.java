package com.hospitaldata.service.impl;

import com.hospitaldata.entity.Home;
import com.hospitaldata.mapper.HomeMapper;
import com.hospitaldata.service.IHomeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class HomeServiceImpl extends ServiceImpl<HomeMapper, Home> implements IHomeService {

}
