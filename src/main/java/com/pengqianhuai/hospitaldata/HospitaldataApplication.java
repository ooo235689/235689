package com.pengqianhuai.hospitaldata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.pengqianhuai.hospitaldata.mapper")
@SpringBootApplication
public class HospitaldataApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitaldataApplication.class, args);
    }

}
