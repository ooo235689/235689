package com.hospitaldata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 药品清单
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Drugdictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "drugId", type = IdType.AUTO)
    private Integer drugId;

    /**
     * 药品名称
     */
    @TableField("drugName")
    private String drugName;

    /**
     * 计量单位
     */
    @TableField("unitId")
    private Integer unitId;

    /**
     * 售价
     */
    @TableField("sellingPrice")
    private Double sellingPrice;

    /**
     * 产地
     */
    @TableField("areaId")
    private Integer areaId;

    /**
     * 类型
     */
    @TableField("typeId")
    private Integer typeId;


}
