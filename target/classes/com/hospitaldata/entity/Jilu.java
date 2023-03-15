package com.hospitaldata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
public class Jilu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "jiluid", type = IdType.AUTO)
    private Integer jiluid;

    private String jiluname;

    private Date jilutime;

    private String jilutype;

    private String jilupeople;

    private Integer jilunumber;

    private String jilupihao;


}
