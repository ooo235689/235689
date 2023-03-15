package com.hospitaldata.service.impl;

import com.hospitaldata.service.IDepartmentsService;
import com.hospitaldata.entity.Departments;
import com.hospitaldata.mapper.DepartmentsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 科室，部门 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class DepartmentsServiceImpl extends ServiceImpl<DepartmentsMapper, Departments> implements IDepartmentsService {

}
