package com.jikaigg.note.order;

import com.jikaigg.note.feign.client.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@MapperScan("com.jikaigg.note.order.mapper")
@SpringBootApplication
//@EnableFeignClients(basePackages = "com.jikaigg.note.feign")
@EnableFeignClients(clients = UserClient.class)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * 创建RestTemplate并注入spring容器
     * @return
     */
    @Bean
    @LoadBalanced/*public class LoadBalancerInterceptor implements ClientHttpRequestInterceptor */
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}