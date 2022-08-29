package com.jikaigg.note.algorithm.base;

public class Base11 {
    public static void main(String[] args) {
        System.out.println(reverse(964632435));
        System.out.println(reverse(-123));
    }

    public static int reverse(int x) {
        String value = String.valueOf(x);
        char[] strings = new char[value.length()];
        if ('-' ==  value.charAt(0)) {
            strings[0] = '-';
            for (int i = value.length() - 1; i > 0; i--) {
                strings[value.length() - i] = value.charAt(i);
            }
        } else {
            for (int i = value.length() - 1; i >= 0; i--) {
                strings[value.length() - i - 1] = value.charAt(i);
            }
        }
        String str = String.valueOf(strings);
        Long aLong = Long.valueOf(str);
        if (aLong>Integer.MIN_VALUE || aLong<Integer.MAX_VALUE){
            return Integer.valueOf(str);
        }else {
            return 0;
        }
    }
}
