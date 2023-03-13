package com.pengqianhuai.hospitaldata.entity;

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
 * 挂号
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "reportId", type = IdType.AUTO)
    private Integer reportId;

    /**
     * 姓名
     */
    @TableField("reportName")
    private String reportName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 科室
     */
    private Integer department;

    /**
     * 医生
     */
    private Integer doctor;

    /**
     * 挂号类型
     */
    @TableField("reportType")
    private Integer reportType;

    /**
     * 挂号费
     */
    private Double price;

    /**
     * 时间
     */
    private Date time;

    /**
     * 操作员
     */
    private String users;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 电话
     */
    private String phone;

    private String carid;

    /**
     * 病名
     */
    private String zhuan;


}
