package com.jikaigg.publisher.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*
     * 发送消息到简单队列中
     * */
    @Test
    public void test1() {
        String queue = "simple.queue";
        String message = "Hello Yao Jikai";
        rabbitTemplate.convertAndSend(queue, message);
    }

    /*
     * 发送消息到简单队列中
     * */
    @Test
    public void test2() throws InterruptedException {
        String queue = "simple.queue";
        String message = "Hello Yao Jikai__";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queue, message + i);
            Thread.sleep(20);
        }
    }

    /*
     * 发送消息到jikaigg.fanout交换机上，交换机会以广播的形式转发到绑定的队列中
     * */
    @Test
    public void test3() throws InterruptedException {
        // 交换机名称
        String exchangeName = "jikaigg.fanout";
        String message = "Hello jiakigg__";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(exchangeName, null, message + i);
        }
    }

    /*
     * 发送消息到jikaigg.direct交换机上，交换机会转发到绑定routingKey的队列中
     * */
    @Test
    public void test4() throws InterruptedException {
        // 交换机名称
        String exchangeName = "jikaigg.direct";
        String messageBlue = "Hello blue__";
        String messageRed = "Hello red__";
        String messageYellow = "Hello yellow";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(exchangeName, "blue", messageBlue + i);
        }
    }

    /*
     * 发送消息到jikaigg.direct交换机上，交换机会转发到绑定routingKey的队列中
     * */
    @Test
    public void test5() throws InterruptedException {
        // 交换机名称
        String exchangeName = "jikaigg.direct";
        String messageBlue = "Hello blue__";
        String messageRed = "Hello red__";
        String messageYellow = "Hello yellow";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(exchangeName, "yellow", messageYellow + i);
        }
    }

    /*
     * 发送消息到jikaigg.direct交换机上，交换机会转发到绑定routingKey的队列中
     * */
    @Test
    public void test6() throws InterruptedException {
        // 交换机名称
        String exchangeName = "jikaigg.direct";
        String messageBlue = "Hello blue__";
        String messageRed = "Hello red__";
        String messageYellow = "Hello yellow";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(exchangeName, "red", messageYellow + i);
        }
    }

    /*
     * 发送消息到jikaigg.direct交换机上，交换机会转发到绑定routingKey的队列中
     * */
    @Test
    public void test7() throws InterruptedException {
        // 交换机名称
        String exchangeName = "jikaigg.topic";
        String message1 = "China wethers is sunny!";
        rabbitTemplate.convertAndSend(exchangeName, "china.wethers", message1);

        String message2 = "japanese wethers is rain!";
        rabbitTemplate.convertAndSend(exchangeName, "japanese.wethers", message2);

        String message3 = "China news is no!";
        rabbitTemplate.convertAndSend(exchangeName, "china.news", message3);

        String message4 = "japanese news is yes!";
        rabbitTemplate.convertAndSend(exchangeName, "japanese.news", message4);

        String message5 = "China go go go !";
        rabbitTemplate.convertAndSend(exchangeName, "china.go.go", message5);

        String message6 = "japanese slow slow slow !";
        rabbitTemplate.convertAndSend(exchangeName, "japanese.go.go", message6);
    }

    @Test
    public void test8() {
        String queue = "object.queue";
        Map<String, Object> map = new HashMap<>();
        map.put("name","yaojikai");
        map.put("age",18);
        rabbitTemplate.convertAndSend(queue,map);
    }
}
