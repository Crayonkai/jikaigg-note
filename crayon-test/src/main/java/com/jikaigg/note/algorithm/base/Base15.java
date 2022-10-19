package com.jikaigg.note.algorithm.base;


public class Base15 {
    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(1);
        node.next.next = new Node(2);
        node.next.next.next = new Node(3);
        node.next.next.next.next = new Node(3);
        /*while (node!=null){
            System.out.println( node.value);
            node = node.next;
        }*/
//        Node reverse = reverse(node);
        Node delete = delete(node);
        while (delete != null) {
            System.out.println(delete.value);
            delete = delete.next;
        }

    }
    public static Node delete(Node head) {
        Node p = head;
        while (p.next != null) {
            if (p.value == p.next.value){
                p.next = p.next.next;
            }else {
                p = p.next;
            }

        }
        return head;
    }


    public static Node reverse(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static  ListNode mergeTwo(ListNode list1, ListNode list2){
        ListNode res = new ListNode(-1),p = res;
        while(list1!=null || list2 != null){
            ListNode tmp = null;
            if(list1 == null && list2!=null){
                p.next = list2;
                list2 = list2.next;
                p = p.next;
            }
            if(list2 == null && list1!=null){
                p.next = list1;
                list1 = list1.next;
                p = p.next;
            }
            if(list1 != null && list2!=null){
                if(list1.val <= list2.val){
                    p.next = list1;
                    list1 = list1.next;
                }else{
                    p.next = list2;
                    list2 = list2.next;
                }
                p = p.next;
            }
        }
        return res.next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = list1, p2 = list2;

        while (p1 != null && p2 != null) {
            if (p1.val >= p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return dummy.next;

    }


}

class Node {
    int value;
    Node next;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
