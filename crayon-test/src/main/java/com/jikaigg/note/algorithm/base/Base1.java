package com.jikaigg.note.algorithm.base;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Base1 {
    public static void main(String[] args) {
        // 待排序数组
        int[] arr = {4, 2, 1, 6, 3, 8, 4, 23, 0};
//        base1();
        selectSort(arr);
//        bubbleSort(arr);
    }

    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
                System.out.println(Arrays.toString(arr));
            }
            System.out.println("----------------");
        }
        return arr;
    }

    /**
     * 选择排序
     */
    public static int[] selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        System.out.println(arr.length);
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            if (arr[i] != arr[minIndex]) {
                arr[i] = arr[i] ^ arr[minIndex];
                arr[minIndex] = arr[i] ^ arr[minIndex];
                arr[i] = arr[i] ^ arr[minIndex];
            }

            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

    /**
     * 打印数组中两个不重复的数字
     */
    public static void base1() {
        int[] arr = {6, 4, 6, 1, 7, 4};    // 数组中1,7单独存在，其他两辆重复
        int a = 0;    // 定义一个变量
        for (int i = 0; i < arr.length; i++) {
            a ^= arr[i];   // 遍历数组，按位异或，相同的数字异或得0，最后剩下的就是1和7的异或值
            System.out.println("a:" + a);     // a保存的是1和7的异或值
        }
        int b = a & (~a + 1);
        int c = 0;     // 定义一个变量C用来存储1或7
        System.out.println(b);
        for (int i : arr) {
            if ((b & i) == 0)
                c ^= i;
        }
        System.out.println("第一个不重复的数字是" + c);
        b = c ^ a;
        System.out.println("第二个不重复的数字是" + b);
        String str = "1";
        System.out.println(str);
        System.out.println();
    }
}
