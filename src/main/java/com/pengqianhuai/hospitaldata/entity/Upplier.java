package com.pengqianhuai.hospitaldata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 供货单位
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Upplier implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "supplierId", type = IdType.AUTO)
    private Integer supplierId;

    /**
     * 供货单位
     */
    @TableField("supplierName")
    private String supplierName;

    /**
     * 联系电话
     */
    @TableField("supplierPhone")
    private String supplierPhone;

    /**
     * 地址
     */
    @TableField("supplierAddress")
    private String supplierAddress;

    /**
     * 状态
     */
    private Integer state;


}
