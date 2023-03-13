package com.pengqianhuai.hospitaldata.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class reportUtil {

    private String address;
    private int age;
    private int bedId;
    private int bedName;
    private String day;
    private String department;
    private Integer departmentId;
    private String diagnose;
    private String discount;
    private int doctorId;
    private String doctorName;
    private String endDate;
    private String idcard;
    private double money ;
    private String operator;
    private String phone;
    private double price;
    private double prices ;
    private String registerDate;
    private int registerid ;
    private int reportid ;
    private String sex;
    private int state ;
    private String userName;
    private String zhuan;



}
