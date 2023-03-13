package com.pengqianhuai.hospitaldata.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HospitalPriceUtil {
    private Integer registerid;
    private String pharmacyName;
    private String projectName;
    private Double  moneys;
    private String userName;
    private String  department;
    private Double money;
    private String  sex;
    private String doctorName;
    private Integer age;
    private String bedName;
    private int page;
    private int limit;
}
