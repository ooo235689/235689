package com.hospitaldata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.hospitaldata.mapper")
@SpringBootApplication
public class HospitaldataApplication {

    public static void main(String[] args) {
        //启动方法入口
        SpringApplication.run(HospitaldataApplication.class, args);
    }

}
