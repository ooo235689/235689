package com.hospitaldata.service.impl;

import com.hospitaldata.entity.Drugstore;
import com.hospitaldata.mapper.DrugstoreMapper;
import com.hospitaldata.service.IDrugstoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 药品库存 服务实现类
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Service
public class DrugstoreServiceImpl extends ServiceImpl<DrugstoreMapper, Drugstore> implements IDrugstoreService {

    @Autowired
    private DrugstoreMapper drugstoreMapper;

    /**
     * 库存查询
     * @return
     */
    public List selectdrugstore(@Param("time")String time,@Param("select") Integer select,@Param("drugstoreName")String drugstoreName, @Param("page")Integer page, @Param("limit")Integer limit){
        return drugstoreMapper.selectdrugstore(time,select,drugstoreName,page,limit);
    }

}
