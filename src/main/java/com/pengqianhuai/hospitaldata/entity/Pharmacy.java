package com.pengqianhuai.hospitaldata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 药房
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Pharmacy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "pharmacyId", type = IdType.AUTO)
    private Integer pharmacyId;

    /**
     * 药品名称
     */
    @TableField("pharmacyName")
    private String pharmacyName;

    /**
     * 领货单位
     */
    @TableField("drugstoreId")
    private Integer drugstoreId;

    /**
     * 经办人
     */
    @TableField("skullId")
    private Integer skullId;

    /**
     * 库房
     */
    @TableField("warehouseId")
    private Integer warehouseId;

    /**
     * 计量单位
     */
    private Integer unit;

    /**
     * 售价
     */
    @TableField("sellingPrice")
    private Double sellingPrice;

    /**
     * 产地
     */
    private Integer area;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 生产日期
     */
    @TableField("produceDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate produceDate;

    /**
     * 有效期
     */
    @TableField("validDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate validDate;

    /**
     * 数量
     */
    private Integer drugstorenum;

    /**
     * 批号
     */
    private String skullbatch;


}
