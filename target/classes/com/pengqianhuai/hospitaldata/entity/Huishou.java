package com.pengqianhuai.hospitaldata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Huishou implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "huishouid", type = IdType.AUTO)
    private Integer huishouid;

    private String huishouname;

    private Integer huishounumber;

    private String huishoupihao;

    private String jbr;

    private String beizhu;


}
