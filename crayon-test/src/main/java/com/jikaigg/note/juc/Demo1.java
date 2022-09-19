package com.jikaigg.note.juc;

public class Demo1 {
    public static void main(String[] args) {
        ThreadLocal<Integer> local1 = ThreadLocal.withInitial(()->4);
        System.out.println(local1.get());
        ThreadLocal<Integer> local2 = ThreadLocal.withInitial(()->5);
        System.out.println(local2.get());


    }
}
