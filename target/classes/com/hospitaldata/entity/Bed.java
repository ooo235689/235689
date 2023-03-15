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
 * 床位
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Bed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 床位
     */
    @TableId(value = "bedId", type = IdType.AUTO)
    private Integer bedId;

    /**
     * 名称
     */
    private String bedname;

    /**
     * 科室
     */
    @TableField("departmentId")
    private Integer departmentId;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 价钱
     */
    @TableField("Price")
    private Double Price;


}
