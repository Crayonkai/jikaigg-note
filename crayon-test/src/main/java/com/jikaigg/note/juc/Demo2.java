package com.jikaigg.note.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Demo2 {
    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao")
    );

    public static List<String> getPrice(List<NetMall> list ,String productName){
        return list.stream()
                .map(netMall ->
                        String.format(productName+ " in %s price is %.2f",
                                netMall.getNetMallname(),
                                netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    public static List<String> getPriceByCompletableFuture(List<NetMall> list ,String productName){
        return list.stream()
                .map(netMall ->CompletableFuture.supplyAsync(()->
                        String.format(productName+ " in %s price is %.2f",
                                netMall.getNetMallname(),
                                netMall.calcPrice(productName))))
                .collect(Collectors.toList())
                .stream()
                .map(s->s.join())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // 一个一个查
        /*long start = System.currentTimeMillis();
        List<String> mysql = getPrice(list, "mysql");
        for (String s : mysql) {
            System.out.println(s);
        }
        long end = System.currentTimeMillis();
        System.out.println("总共耗时："+ (end-start)+" 秒");*/

        //并发查
        long start = System.currentTimeMillis();
        List<String> mysql = getPriceByCompletableFuture(list, "mysql");
        for (String s : mysql) {
            System.out.println(s);
        }
        long end = System.currentTimeMillis();
        System.out.println("总共耗时："+ (end-start)+" 秒");
    }
}

class NetMall {
    @Getter
    private String netMallname;

    public NetMall(String netMallname) {
        this.netMallname = netMallname;
    }

    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
