package com.jikaigg.note.algorithm.base;

import java.util.Stack;

public class Base16 {
    public static void main(String[] args) {
        Node node = new Node(3);
        node.left = new Node(1);
        node.right = new Node(2);
        node.left.left = new Node(7);
        node.left.right = new Node(8);
        node.right.left = new Node(5);
        node.right.right = new Node(6);
        node.left.left.left = new Node(6);
        node.left.left.right = new Node(9);
        node.left.right.left = new Node(5);
        node.left.right.right = new Node(7);
        node.right.left.left = new Node(3);
        node.right.left.right = new Node(2);
        node.right.right.left = new Node(8);
        node.right.right.right = new Node(4);
        System.out.println("先序遍历");
        pre(node);
        System.out.println("中序遍历");
        in(node);
        System.out.println("后序遍历");
        after(node);
        System.out.println("非递归先序遍历");
        npre(node);
        System.out.println("非递归中序遍历");
        nin(node);
        System.out.println("非递归后序遍历");
        nafter(node);

    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 先序遍历
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    // 中序遍历
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    // 后序遍历
    public static void after(Node head) {
        if (head == null) {
            return;
        }
        after(head.left);
        after(head.right);
        System.out.println(head.value);
    }

    // 非递归先序遍历
    public static void npre(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value);
                if (head.right != null)
                    stack.push(head.right);
                if (head.left != null)
                    stack.push(head.left);
            }
        }
    }

    // 非递归中序遍历
    public static void nin(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.value);
                    head = head.right;
                }
            }
        }
    }

    /*
     * 非递归后序遍历
     * 先序遍历是先压有再压左，后序遍历是先压左再压右，将来打印的就是后序遍历结果得逆序
     * 将后序遍历结果放在另一个栈，将来再出栈打印，那么结果就是后序遍历得结果
     */
    public static void nafter(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            Stack<Node> sout = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                sout.push(head);
                if (head.left != null)
                    stack.push(head.left);
                if (head.right != null)
                    stack.push(head.right);
            }
            while (!sout.isEmpty()) {
                System.out.println(sout.pop().value);
            }
        }
    }
}
