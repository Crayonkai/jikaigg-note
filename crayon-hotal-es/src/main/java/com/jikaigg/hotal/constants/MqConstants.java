package com.jikaigg.hotal.constants;

public class MqConstants {
    // 交换机
    public final static String HOTEL_EXCHANGE = "hotal.topic";
    // 监听新增和修改的队列
    public final static String HOTEL_INSERT_QUEUE = "hotal.insert.queue";
    // 监听删除的队列
    public final static String HOTEL_DELETE_QUEUE = "hotal.delete.queue";
    // 新增或修改的RoutingKey
    public final static String HOTEL_INSERT_KEY = "hotal.insert";
    // 删除的RoutingKey
    public final static String HOTEL_DELETE_KEY = "hotal.delete";
}
