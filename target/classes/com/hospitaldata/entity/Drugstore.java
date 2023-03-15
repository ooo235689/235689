package com.hospitaldata.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 药品库存
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Drugstore implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "rugstoreId", type = IdType.AUTO)
    private Integer rugstoreId;

    /**
     * 药品名称
     */
    @TableField("drugstoreName")
    private String drugstoreName;

    /**
     * 供货
     */
    @TableField("supplierId")
    private Integer supplierId;

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
     * 批发价
     */
    @TableField("tradePrice")
    private Double tradePrice;

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
     * 生成日期
     */
    @TableField("produceDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date produceDate;

    /**
     * 有效期
     */
    @TableField("validDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validDate;

    /**
     * 数量
     */
    private Integer drugstorenum;

    /**
     * 批号
     */
    private String batch;


}
