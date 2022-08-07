package com.jikaigg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
@MapperScan("com.jikaigg.orm.mapper")
public class ChowderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChowderApplication.class, args);
    }
}
