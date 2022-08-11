package com.jikaigg.note.lambda;

/**
 * 引用写法
 */
public class Lambda4 {
    public static void main(String[] args) {
        Lambda4 lambda4 = new Lambda4();
        // 非静态方法，实例名::方法
        interfaceA interfaceA1 = lambda4::testA;
        // 静态方法，直接类名::方法名
        interfaceA interfaceA2 = Lambda4::testB;
        int resultA = interfaceA1.test(4);
        int resultB = interfaceA2.test(4);
        System.out.println("resultA: " + resultA);
        System.out.println("resultB: " + resultB);
    }

    int testA(int a) {
        return a + 1;
    }

    static int testB(int a) {
        return a + 3;
    }

    /**
     * 单参数有返回值
     */
    interface interfaceA {
        int test(int a);
    }

}
