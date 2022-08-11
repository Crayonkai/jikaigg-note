package com.jikaigg.note.lambda;

public class Lambda2 {
    public static void main(String[] args) {
        Test1 test1 = () -> {
            System.out.println("Test1：无参数无返回值！");
        };
        test1.test();
        Test2 test2 = (int a) -> {
            System.out.println("Test2:单参数无返回值！a = " + a);
        };
        test2.test(1);
        Test3 test3 = (int a, int b) -> {
            System.out.println("Test3：多参数无返回值！a + b = " + (a + b));
        };
        test3.test(1, 1);
        Test4 test4 = () -> {
            return 3;
        };
        int result1 = test4.test();
        System.out.println("Test4：无参数有返回值！" + result1);
        Test5 test5 = (int a) -> {
            return a;
        };
        int result2 = test5.test(4);
        System.out.println("Test5：单参数有返回值！ a = " + result2);
        Test6 test6 = (int a, int b) -> {
            return a - b;
        };
        int result3 = test6.test(10, 5);
        System.out.println("Test6：多参数有返回值！ a - b = " + result3);
    }

    /**
     * 无参数无返回值
     */
    interface Test1 {
        void test();
    }

    /**
     * 单参数无返回值
     */
    interface Test2 {
        void test(int a);
    }

    /**
     * 多参数无返回值
     */
    interface Test3 {
        void test(int a, int b);
    }

    /**
     * 无参数有返回值
     */
    interface Test4 {
        int test();
    }

    /**
     * 单参数有返回值
     */
    interface Test5 {
        int test(int a);
    }

    /**
     * 多参数有返回值
     */
    interface Test6 {
        int test(int a, int b);
    }
}
