package com.hospitaldata.service.impl;

import com.hospitaldata.service.ITypeService;
import com.hospitaldata.entity.Type;
import com.hospitaldata.mapper.TypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 类型 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

}
