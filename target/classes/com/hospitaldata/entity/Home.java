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
public class Home implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "homeId", type = IdType.AUTO)
    private Integer homeId;

    @TableField("homeName")
    private String homeName;

    private String sex;

    private Integer age;

    @TableField("carId")
    private String carId;

    private String phone;


}
