package com.jackasher.ware_manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = "com.jackasher.ware_manager.mapper")
public class WareManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WareManagerApplication.class, args);
    }

}
