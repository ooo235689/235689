package com.hospitaldata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

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
public class Hospitalprice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "hospitalpriceid", type = IdType.AUTO)
    private Integer hospitalpriceid;

    /**
     * 住院
     */
    @TableField("registerId")
    private Integer registerId;

    /**
     * 名称
     */
    private String durgname;

    /**
     * 数量
     */
    private Integer durgnum;

    /**
     * 价钱
     */
    private Double repiceprice;

    /**
     * 小计
     */
    private Double repicetotal;

    private Date htime;

    /**
     * 判断是药品还是收费项目
     */
    private Integer state;


}
