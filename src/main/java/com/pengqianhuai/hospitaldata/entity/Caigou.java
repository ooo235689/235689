package com.pengqianhuai.hospitaldata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 采购
 * </p>
 *
 * @author pengqianhuai
 * @since 2021-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Caigou implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "caigouid", type = IdType.AUTO)
    private Integer caigouid;

    private String caigouname;

    private String gonghuoshang;

    private String danwei;

    private String candi;

    private String leixing;

    private Integer shuliang;


}
