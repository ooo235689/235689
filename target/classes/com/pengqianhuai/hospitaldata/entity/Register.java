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
 * 住院登记
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Register implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "registerid", type = IdType.AUTO)
    private Integer registerid;

    /**
     * 患者名字
     */
    @TableField("userName")
    private String userName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 科室
     */
    private Integer department;

    /**
     * 医生
     */
    private Integer doctor;

    /**
     * 入院
     */
    private String diagnose;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    @TableField("Phone")
    private String Phone;

    /**
     * 身份证号
     */
    @TableField("Idcard")
    private String Idcard;

    /**
     * 入院日期
     */
    @TableField("registerDate")
    private Date registerDate;

    /**
     * 创号
     */
    @TableField("bedNum")
    private Integer bedNum;

    /**
     * 操作员
     */
    @TableField("Operator")
    private String Operator;

    /**
     * 预交金
     */
    private Double money;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 结束时间
     */
    @TableField("endDate")
    private Date endDate;

    /**
     * 价格
     */
    private Double price;

    /**
     * 折扣
     */
    private String discount;

    private Date zhuan;


}
