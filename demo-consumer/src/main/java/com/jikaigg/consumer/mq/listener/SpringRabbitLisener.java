package com.jikaigg.consumer.mq.listener;

import lombok.Value;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

@Component
public class SpringRabbitLisener {
    /*@RabbitListener(queues = "simple.queue")
    public void lisenerQueue(String msg) {
        System.out.println("消费者接收到simple.queue:" + msg);
    }*/

    /*
     * @RabbitListener注解监听simple.queue获取消息并消费
     * */
    @RabbitListener(queues = "simple.queue")
    public void lisenerWorkQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1接收到simple.queue:[" + msg + "]" + LocalTime.now());
        Thread.sleep(50);
    }

    /*
     * @RabbitListener注解监听simple.queue获取消息并消费
     * */
    @RabbitListener(queues = "simple.queue")
    public void lisenerWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2接收到simple.queue:[" + msg + "]" + LocalTime.now());
        Thread.sleep(200);
    }

    /*
     * @RabbitListener注解监听fanout.queue1获取消息并消费
     * */
    @RabbitListener(queues = "fanout.queue1")
    public void lisenerWorkQueue3(String msg) throws InterruptedException {
        System.out.println("fanout.queue1:[" + msg + "]" + LocalTime.now());
        Thread.sleep(200);
    }

    /*
     * @RabbitListener注解监听fanout.queue2获取消息并消费
     * */
    @RabbitListener(queues = "fanout.queue2")
    public void lisenerWorkQueue4(String msg) throws InterruptedException {
        System.out.println("fanout.queue2:[" + msg + "]" + LocalTime.now());
        Thread.sleep(200);
    }

    /*
     * @RabbitListener注解监听direct.queue1获取消息并消费
     * */
    @RabbitListener(bindings = @QueueBinding(  //绑定一个队列
            value = @Queue(name = "direct.queue1"),  //绑定队列的名称
            exchange = @Exchange(value = "jikaigg.direct",  // 绑定到的交换器的名称
                    type = ExchangeTypes.DIRECT), //交换机的类型，默认为direct
            key = {"red", "blue"}))  //绑定队列的key
    public void lisenerWorkQueue5(String msg) throws InterruptedException {
        System.out.println("direct.queue1:[" + msg + "]" + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(value = "jikaigg.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}))
    public void lisenerWorkQueue6(String msg) throws InterruptedException {
        System.out.println("direct.queue2:[" + msg + "]" + LocalTime.now());
        Thread.sleep(200);
    }

    /*@RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(value = "jikaigg.topic",type = ExchangeTypes.TOPIC),
            key={"china.#"}))
    public void lisenerWorkQueue7(String msg) throws InterruptedException {
        System.out.println("china is :[" + msg + "]" + LocalTime.now());
    }*/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(value = "jikaigg.topic", type = ExchangeTypes.TOPIC),
            key = {"china.#"}))
    public void lisenerWorkQueue8(String msg) {
        System.out.println("china all is :[" + msg + "]" + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(value = "jikaigg.topic", type = ExchangeTypes.TOPIC),
            key = {"*.news"}))
    public void lisenerWorkQueue9(String msg) {
        System.out.println("news is :[" + msg + "]" + LocalTime.now());
    }
    @RabbitListener(queues = "object.queue")
    public void lisenerWorkQueue10(Map<String,Object> msg) {
        System.out.println(msg);
    }
}
