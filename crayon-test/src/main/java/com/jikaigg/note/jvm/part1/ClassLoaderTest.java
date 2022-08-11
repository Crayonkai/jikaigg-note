package com.jikaigg.note.jvm.part1;

/**
 * jvm类加载器：
 *  分为启动类加载器(引导类加载器)和自定义类加载器(又分为扩展类加载器、应用类加载器、用户自定义类加载器)
 *  启动类加载器为C/C++编写，无法在代码中获取到它的引用。
 *  自定义类加载器为java编写，都间接继承自抽象类ClassLoader
 *
 * getClassLoader()和getContextClassLoader()的区别：
 *  getClassLoader()是当前类加载器,而getContextClassLoader是当前线程的类加载器
 *
 * 双亲委派机制：
 *      BootStrapClassLoder
 *            ↑    ↓
 *        ExtClassLoader
 *            ↑    ↓
 *        AppClassLoader
 *
 */
public class ClassLoaderTest {
    public static void main(String[] args) {

        // 获取类ClassLoaderTest的类加载器，系统类加载器/应用类加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);  // sun.misc.Launcher$AppClassLoader@18b4aac2

        // 向上获取父加载器，扩展类加载器
        ClassLoader classLoaderParent = classLoader.getParent();
        System.out.println(classLoaderParent);  // sun.misc.Launcher$ExtClassLoader@1b6d3586

        // 向上获取父加载器，引导类加载器/启动类加载器(C/C++编写，无法获取到类的引用，所以返回一个null，返回null的类加载器就是启动类加载器)
        ClassLoader parent = classLoaderParent.getParent();
        System.out.println(parent);  // null

        // getContextClassLoader获取当前线程的类加载器
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);

    }
}
