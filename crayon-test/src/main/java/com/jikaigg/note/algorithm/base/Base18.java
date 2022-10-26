package com.jikaigg.note.algorithm.base;

import sun.awt.image.ImageWatched;

import java.util.*;

public class Base18 {
    public static void main(String[] args) {
        Base16.Node node = new Base16.Node(1);
        node.left = new Base16.Node(2);
        node.right = new Base16.Node(3);
        node.left.left = new Base16.Node(4);
        node.left.right = new Base16.Node(5);
        node.right.left = new Base16.Node(6);
        node.right.right = new Base16.Node(7);
        List<Integer> before = before(node);
        System.out.println(before.toString());
        System.out.println("--------------");
        List<Integer> in = in(node);
        System.out.println(in.toString());
        System.out.println("--------------");
        List<Integer> after = after(node);
        System.out.println(after.toString());
        System.out.println("--------------");
        List<List<Integer>> sort = sort(node);
        System.out.println(sort.toString());
        System.out.println("--------------");
        System.out.println("--------------");
    }

    public static List<Integer> before(Base16.Node node) {
        List<Integer> list = new ArrayList<>();
        if (node != null) {
            Stack<Base16.Node> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()) {
                Base16.Node pop = stack.pop();
                list.add(pop.value);
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            }
        }
        return list;
    }

    public static List<Integer> in(Base16.Node node) {
        List<Integer> list = new ArrayList<>();
        Stack<Base16.Node> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                Base16.Node pop = stack.pop();
                list.add(pop.value);
                node = pop.right;
            }
        }

        return list;
    }

    public static List<Integer> after(Base16.Node node) {
        List<Integer> list = new ArrayList<>();
        Stack<Base16.Node> stack = new Stack<>();
        Stack<Base16.Node> sout = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Base16.Node pop = stack.pop();
            sout.push(pop);
            if (pop.left != null)
                stack.push(pop.left);
            if (pop.right != null)
                stack.push(pop.right);
        }
        while (!sout.isEmpty())
            list.add(sout.pop().value);
        return list;
    }

    public static List<List<Integer>> sort(Base16.Node node) {
        List<List<Integer>> list = new ArrayList<>();
        Boolean flag = true;
        Queue<Base16.Node> queue = new LinkedList();
        queue.offer(node);
        while (!queue.isEmpty()) {
            LinkedList<Integer> list1 = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Base16.Node poll = queue.poll();
                if (flag)
                    list1.add(poll.value);
                else
                    list1.addFirst(poll.value);
                if (poll.left != null)
                    queue.offer(poll.left);
                if (poll.right != null)
                    queue.offer(poll.right);
            }
            list.add(list1);
            flag = !flag;
        }
        return list;
    }

    public List<Integer> nin(Base16.Node node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        return null;
    }

    public List<Integer> nafter(Base16.Node node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        return null;
    }
}
