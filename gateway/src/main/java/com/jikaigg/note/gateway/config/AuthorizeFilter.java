package com.jikaigg.note.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Order(-1)  //定义过滤器的执行顺序，数越小执行越早
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        // 2.获取参数中的AuthorizeFile
        MultiValueMap<String, String> params = request.getQueryParams();
        // 3.判断参数之是否等于admin
        String first = params.getFirst("authorization");
        // 4.是，放行
        if ("admin".equals(first)) {
            return chain.filter(exchange);
        } else {
            // 5.1 设置状态码
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            // 5.否，拦截
            return exchange.getResponse().setComplete();
        }
    }

    //事项Ordered接口重写方法，和@Order注解效果一样
    @Override
    public int getOrder() {
        return -1;
    }
}
