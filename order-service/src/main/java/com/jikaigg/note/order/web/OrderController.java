package com.jikaigg.note.order.web;

import com.jikaigg.note.order.pojo.Order;
import com.jikaigg.note.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    /**
     * @Autowired 是按照类型进行装配（ByType）也就是标明的类型必须是Bean中存在的且唯一。
     * @Autowired
     * private OrderMapper orderMapper;
     * 如果同一类型存在多个bean需要指明bean的名称，结合@Qualifier注解使用
     */
   /*@Autowired
   private OrderService order;*/

    @Autowired
    private OrderService orderService;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        return orderService.queryOrderById(orderId);
    }
}
