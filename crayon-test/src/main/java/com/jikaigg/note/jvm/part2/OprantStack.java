package com.jikaigg.note.jvm.part2;

/**
 * 局部变量表 Local Variables
 *      数组实现
 * 操作数栈 Operand Stack
 *      数组实现，只有入栈出栈操作，不能通过索引调用
 * 栈顶缓存技术 Top-of-Stack-Cashing
 * 动态链接 Dynamic Linking
 *
 * 虚方法与非虚方法：
 *      如果方法在编译器就确定了具体的调用版本，这个版本在运行时是不可变得，
 *    这样的方法称为非虚方法。
 *      静态方法、私有方法、final方法、实例构造器、父类方法都是非虚方法
 *      其他方法称为虚方法
 * 虚拟机中提供了以下几条方法调用指令:
 *      1.invokestatic:调用静态方法，解析阶段确定唯一方法版本
 *      2.invokespecial:调用<init>方法、私有及父类方法，解析阶段确定唯一方法版本
 *      3.invokevirtual:调用所有虚方法
 *      4.invokeinterface:调用接口方法
 *      5.invokedynamic：动态解析出需要调用的方法，然后执行
 */
public class OprantStack {































    /**
     * 预留问题
     */
    public void testEnd() {
        // 1.
        int i1 = 10;
        i1++;
        int i2 = 10;
        ++i2;

        // 2.
        int i3 = 10;
        int i4 = i3++;
        int i5 = 10;
        int i6 = ++i5;

        // 3.
        int i7 = 10;
        i7 = i7++;
        int i8 = 10;
        i8 = ++i8;

        // 4.
        int i9 = 10;
        int i10 = i9++ + ++ i9;
    }
}
