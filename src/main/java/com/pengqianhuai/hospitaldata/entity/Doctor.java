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
 * 医生
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "doctorId", type = IdType.AUTO)
    private Integer doctorId;

    /**
     * 名称
     */
    @TableField("doctorName")
    private String doctorName;

    /**
     * 所属科室
     */
    @TableField("departmentId")
    private Integer departmentId;

    /**
     * 挂号类型
     */
    @TableField("registeredId")
    private Integer registeredId;

    /**
     * 状态
     */
    private Integer dstate;

    /**
     * 启动时间
     */
    @TableField("amStartTime")
    private String amStartTime;

    /**
     * 更改时间
     */
    @TableField("amEndTime")
    private String amEndTime;

    /**
     * 下午开始时间
     */
    @TableField("pmStartTime")
    private String pmStartTime;

    /**
     * 下午结束时间
     */
    @TableField("pmEndTime")
    private String pmEndTime;


}
