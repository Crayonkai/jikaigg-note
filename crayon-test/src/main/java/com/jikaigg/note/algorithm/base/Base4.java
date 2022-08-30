package com.jikaigg.note.algorithm.base;

import java.util.Stack;

/**
 * 输入  "[(a,(b)),(aaa,{c})]"
 * 输出 true
 * 输入  "[(a,b)),(aaa,{c})]"
 * 输出  false
 */
public class Base4 {
    /*
     * 检查字符是不是括号
     * */
    public static boolean checkString(String str) {
        if ("[".equals(str) || "]".equals(str)
                || "(".equals(str) || ")".equals(str)
                || "{".equals(str) || "}".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean compareTwo(String str1, String str2) {
        if ("(".equals(str1) && ")".equals(str2)) {
            return true;
        } else if ("{".equals(str1) && "}".equals(str2)) {
            return true;
        } else if ("[".equals(str1) && "]".equals(str2)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "[(a,(b)),(aaa,{c})]";
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            boolean flg = checkString(s);
            if (flg) {
                if (stack.empty()) {
                    stack.push(s);
                } else {
                    boolean b = compareTwo(stack.peek(), s);
                    if (b) {
                        stack.pop();
                    } else {
                        stack.push(s);
                    }
                }
            }
        }
        System.out.println(stack.empty());

        System.out.println("(]".charAt(0));

        System.out.println(isValid("()"));
    }


    public static boolean isValid(String s) {
        Stack stack = new Stack();
        String str = null;
        for(int i = 0; i < s.length(); i++){
            str = String.valueOf(s.charAt(i));
            if(stack.isEmpty()){
                stack.push(str);
            }else{
                if(")".equals(str) && stack.peek().equals("(")){
                    stack.pop();
                }else{
                    stack.push(str);
                }
                if( "]".equals(str) && stack.peek().equals("[")){
                    stack.pop();
                }else{
                    stack.push(str);
                }
                if("}".equals(str) && stack.peek().equals("{")){
                    stack.pop();
                }else{
                    stack.push(str);
                }
            }
        }
        return stack.isEmpty()?true:false;

    }
}
