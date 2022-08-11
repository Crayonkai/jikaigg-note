package com.jikaigg.note.lambda;

/**
 * 精简写法
 * 参数类型可以省略
 * 只有一个参数，小括号（）可以省略
 * 方法体只有一个语句，花括号{}可以省略。唯一的语句是return xxx;的时候return也要省略
 */
public class Lambda3 {
    public static void main(String[] args) {
        /**
         * 无参数无返回值
         */
        Test1 test1 = () -> System.out.println("Test1：无参数无返回值！");
        ;
        test1.test();
        /**
         * 单参数无返回值
         */
        Test2 test2 = a -> System.out.println("Test2:单参数无返回值！a = " + a);
        test2.test(1);
        /**
         * 多参数无返回值
         */
        Test3 test3 = (a, b) -> System.out.println("Test3：多参数无返回值！a + b = " + (a + b));

        test3.test(1, 1);
        /**
         * 无参数有返回值
         */
        Test4 test4 = () -> {
            return 3;
        };
        int result1 = test4.test();
        System.out.println("Test4：无参数有返回值！" + result1);
        /**
         * 单参数有返回值
         */
        Test5 test5 = a -> a;
        int result2 = test5.test(4);
        System.out.println("Test5：单参数有返回值！ a = " + result2);
        /**
         * 多参数有返回值
         */
        Test6 test6 = (a, b) -> a - b;
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
