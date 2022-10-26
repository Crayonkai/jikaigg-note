package com.jikaigg.note.algorithm.base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Base17 {
    public static void main(String[] args) {
        Base16.Node node = new Base16.Node(3);
        node.left = new Base16.Node(1);
        node.right = new Base16.Node(2);
        node.left.left = new Base16.Node(7);
        node.left.right = new Base16.Node(8);
        node.right.left = new Base16.Node(5);
        node.right.right = new Base16.Node(6);
        node.left.left.left = new Base16.Node(6);
        node.left.left.right = new Base16.Node(9);
        node.left.right.left = new Base16.Node(5);
        node.left.right.right = new Base16.Node(7);
        node.right.left.left = new Base16.Node(3);
        node.right.left.right = new Base16.Node(2);
        node.right.right.left = new Base16.Node(8);
        node.right.right.right = new Base16.Node(4);
        List<List<Integer>> extracted = extracted(node);
        System.out.println(extracted.toString());

        System.out.println("--------------");
    }

    // 蛇形遍历二叉树
    private static List<List<Integer>> extracted(Base16.Node root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (root == null) {
            return list;
        }
        Queue<Base16.Node> queue = new LinkedList();
        queue.offer(root);
        Boolean reverse = true;
        while (!queue.isEmpty()) {
            LinkedList<Integer> res = new LinkedList<>();
            Integer size = queue.size();
            for (int i = 1; i <= size; i++) {
                root = queue.poll();
                if (reverse)
                    res.add(root.value);
                else
                    res.addFirst(root.value);
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
            reverse=!reverse;
            list.add(res);
        }
        return list;
    }
}
