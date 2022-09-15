package com.jikaigg.note.juc;

import java.util.concurrent.TimeUnit;

public class DeadLock {

    Object object1 = new Object();
    Object object2 = new Object();
    public void method1() throws InterruptedException {
        synchronized (object1){
            System.out.println("method1执行");
            TimeUnit.SECONDS.sleep(2);
            synchronized (object2){
                System.out.println("method1中的中的执行");

            }
        }
    }
    public void method2() throws InterruptedException {
        synchronized (object2){
            System.out.println("method2执行");
            TimeUnit.SECONDS.sleep(2);
            synchronized (object1){
                System.out.println("method2中的中的执行");

            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(()->{
            try {
                deadLock.method1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"111").start();
        new Thread(()->{
            try {
                deadLock.method2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"222").start();
    }
}
