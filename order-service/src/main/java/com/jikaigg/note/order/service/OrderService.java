package com.jikaigg.note.order.service;

import com.jikaigg.note.feign.client.UserClient;
import com.jikaigg.note.order.mapper.OrderMapper;
import com.jikaigg.note.order.pojo.Order;
import com.jikaigg.note.feign.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {


    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.1 利用RestTemplate发起HTTP请求
//        String url = "http://localhost:8081/user/"+order.getUserId();
        //2.2 利用RestTemplate发起HTTP请求RPC请求
//        String url = "http://userservice/user/"+order.getUserId();
//        User user = restTemplate.getForObject(url, User.class);
        User user = userClient.queryById(order.getUserId());
        // 3. 封装user到order
        order.setUser(user);
        // 4.返回
        return order;
    }
}
