package com.crayon.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.crayon.redis.mapper")
@SpringBootApplication
public class CrayonDianPingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrayonDianPingApplication.class, args);
    }

}
