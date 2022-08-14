package com.jikaigg.consumer.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    /*
    * 定义一个fanout交换机
    * */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("jikaigg.fanout");
    }


    /*
     * 定义一个fanout.queue1队列
     * */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.queue1");
    }
    /*
     * 将fanout.queue1队列绑定到jikaigg.fanout交换机上
     * */
    @Bean
    public Binding fanoutBinding1(Queue fanoutQueue1,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }


    /*
     * 定义一个fanout.queue2队列
     * */
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.queue2");
    }
    /*
     * 将fanout.queue2队列绑定到jikaigg.fanout交换机上
     * */
    @Bean
    public Binding fanoutBinding2(Queue fanoutQueue2,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
    @Bean
    public Queue fanoutQueue3() {
        return new Queue("object.queue");
    }
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
