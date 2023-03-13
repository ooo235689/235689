package com.pengqianhuai.hospitaldata.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PayTotalPrice {

    private String durgname;
    private Integer durgnum;
    private String hospitalpriceid;
    private String htime;
    private Integer registerid;
    private Double repiceprice;
    private Double repicetotal;
    private String state;

}
