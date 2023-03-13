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
public class Baoque implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "baoqueid", type = IdType.AUTO)
    private Integer baoqueid;

    @TableField("baoqueName")
    private String baoqueName;

    @TableField("baoqueNum")
    private Integer baoqueNum;


}
