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
 * 
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Paiban implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "paiId", type = IdType.AUTO)
    private Integer paiId;

    private String one;

    private String two;

    private String three;

    private String four;

    private String five;

    private String six;

    private String seven;

    @TableField("doctorId")
    private Integer doctorId;


}
