package com.pengqianhuai.hospitaldata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 出纳
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Cashier implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "cashier", type = IdType.AUTO)
    private Integer cashier;

    /**
     * 挂号
     */
    @TableField("reportId")
    private Integer reportId;

    /**
     * 收费类型
     */
    private String durgname;

    /**
     * 名称
     */
    private Integer durgnum;

    /**
     * 次数
     */
    private Double repiceprice;

    /**
     * 单价
     */
    private Double repicetotal;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 时间
     */
    private Date ctime;

    private Integer ostate;

    private String jie;

    private Integer mstate;


}
