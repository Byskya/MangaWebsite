package com.pratice.shopcar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@MapperScan("com.pratice.shopcar.mappers")
public class ShopcarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopcarApplication.class, args);
    }

}
