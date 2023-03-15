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
 * 住院收费
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Inhospitaltype implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "inhospitalId", type = IdType.AUTO)
    private Integer inhospitalId;

    /**
     * 类型
     */
    @TableField("projectName")
    private String projectName;

    /**
     * 单位
     */
    private Integer unit;

    /**
     * 单价
     */
    private Double price;

    /**
     * 项目分类
     */
    @TableField("bigprojectId")
    private Integer bigprojectId;


}
