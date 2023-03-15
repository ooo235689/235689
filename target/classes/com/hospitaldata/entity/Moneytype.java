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
 * 
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Moneytype implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 注释
     */
    @TableId(value = "MoneyId", type = IdType.AUTO)
    private Integer MoneyId;

    /**
     * 收费类型
     */
    @TableField("Moneytype")
    private String Moneytype;

    /**
     * 比例
     */
    @TableField("Percent")
    private String Percent;


}
