package com.jikaigg.note.algorithm.base;

public class Base13 {
    public static void main(String[] args) {
        int a=1,b=2;
        System.out.println("a= "+a+" , b= " +b);
        a=a^b;
        b=a^b;
        a=a^b;
        System.out.println("a= "+a+" , b= " +b);
    }
}
