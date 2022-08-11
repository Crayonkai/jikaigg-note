package com.jikaigg.note.algorithm.interview.first;

import java.util.Stack;

/**
 * 实现一个有getMin功能的栈
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，在实现返回栈中最小元素的操作
 */
public class Code1 {
    private Stack<Integer> stackDate;
    private Stack<Integer> stackMin;

    public Code1(){
        this.stackDate = new Stack<>();
        this.stackMin = new Stack<>();
    }
    public void push(int newNum){
        if (this.stackMin.isEmpty()){
            stackMin.push(newNum);
        }else if (newNum <= this.getMin()){
            stackMin.push(newNum);
        }
        this.stackDate.push(newNum);
    }

    public int pop(){
        if (stackMin.isEmpty())
            throw new RuntimeException("Your stack is Empty !");
        int value = stackDate.pop();
        if (value == this.getMin())
            stackMin.pop();
        return value;
    }

    public int getMin(){
        if (this.stackMin.isEmpty())
            throw new RuntimeException("Your stack is Empty !");
        return this.stackMin.peek();
    }

    public Stack<Integer> getStackDate() {
        return stackDate;
    }

    public void setStackDate(Stack<Integer> stackDate) {
        this.stackDate = stackDate;
    }

    public Stack<Integer> getStackMin() {
        return stackMin;
    }

    public void setStackMin(Stack<Integer> stackMin) {
        this.stackMin = stackMin;
    }
}
