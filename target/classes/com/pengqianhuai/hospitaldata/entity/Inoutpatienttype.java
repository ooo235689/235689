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
 * 住院收费项目
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Inoutpatienttype implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "inoutpatientId", type = IdType.AUTO)
    private Integer inoutpatientId;

    /**
     * 项目名称
     */
    @TableField("projectName")
    private String projectName;

    /**
     * 单位
     */
    private Integer unit;

    /**
     * 项目分类
     */
    @TableField("bigproJectId")
    private Integer bigproJectId;

    /**
     * 单位
     */
    private Double price;


}
