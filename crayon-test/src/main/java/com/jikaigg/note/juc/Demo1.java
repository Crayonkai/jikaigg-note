package com.jikaigg.note.juc;

import java.util.LinkedList;
import java.util.concurrent.ForkJoinPool;

public class Demo1 {
    public static void main(String[] args) {
        DoubleNode doubleNode = new DoubleNode(1);
        doubleNode.next = new DoubleNode(2);
        doubleNode.last = null;
        doubleNode.next.next = new DoubleNode(3);
        doubleNode.next.last = doubleNode;
        doubleNode.next.next.next = null;
        doubleNode.next.next.last = doubleNode.next;

        System.out.println(doubleNode);
        System.out.println(doubleNode.next);
        System.out.println(doubleNode.next.next);
        System.out.println(doubleNode.last);
        System.out.println(doubleNode.next.last);
        System.out.println(doubleNode.next.last.last);


    }

    public static int[] test1(int nums[]) {
        return null;
    }
}

class SingleNode {
    private int val;
    private SingleNode next;

    public SingleNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public SingleNode getNext() {
        return next;
    }

    public void setNext(SingleNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "SingleNode{" +
                "val='" + val + '\'' +
                ", next=" + next +
                '}';
    }
}

class DoubleNode {
    public Integer value;
    public DoubleNode last = null;
    public DoubleNode next = null;

    public DoubleNode(int value) {
        this.value = value;
    }
    @Override
    public String toString(){
        return "last:"+last+"next:"+next;
    }
}
