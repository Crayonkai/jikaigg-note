package com.jikaigg.note.algorithm.interview.first;

import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        Code1 code = new Code1();
        Stack<Integer> stackDate = code.getStackDate();
        Stack<Integer> stackMin = code.getStackMin();
        code.push(4);
        code.push(5);
        code.push(1);
        Integer peek = stackDate.peek();
        Integer peekmin = stackMin.peek();
        System.out.println("stackDate 栈顶元素是：" + peek + "，stackMin 栈顶元素是：" + peekmin);
        int search = stackDate.search(1);
        System.out.println(search);

    }
}
