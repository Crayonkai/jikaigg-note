package com.jikaigg.note.base.annotation;

import java.io.Serializable;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/*
 * 注解和反射
 * */
public class Annotation1 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 1. 通过包名称+类名称获取class
        Class user1 = Class.forName("com.jikaigg.note.base.annotation.User");
        System.out.println(user1);
        System.out.println(user1.hashCode());
        // 2. 通过实例对象获取class
        User userModel = new User();
        Class user2 = userModel.getClass();
        System.out.println(user2);
        System.out.println(user2.hashCode());
        // 3. 通过类名.class获取class
        Class user3 = User.class;
        System.out.println(user3);
        System.out.println(user3.hashCode());


        Class<?> user = Class.forName("com.jikaigg.note.base.annotation.User");
        // 通过反射获取user的类加载器
        ClassLoader classLoader = user.getClassLoader();
        System.out.println(classLoader); //sun.misc.Launcher$AppClassLoader@18b4aac2
        // 通过反射获取user的类加载器的父类加载器
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);  //sun.misc.Launcher$ExtClassLoader@246b179d
        // 通过反射获取user的类加载器的父类加载器的父类加载器
        ClassLoader grandParent = parent.getParent();
        System.out.println(grandParent);  //null BootStrapClassLoader通过java程序无法直接获取到，使用C++编写的
        // 通过反射获取user的public属性包括父类的
        for (Field field : user.getFields()) {
//            System.out.println("user的public属性有===" + field);
        }
        // 通过反射获取user的private属性。不包括父类的所有属性
        for (Field field : user.getDeclaredFields()) {
//            System.out.println("user的private属性有===" + field);
        }
        // 通过反射获取user的所有public方法包括父类的
        for (Method method : user.getMethods()) {
//            System.out.println("user的所有public方法===" + method);
        }
        // 通过反射获取user的所有方法（包括private）不包括继承来的方法
        for (Method method : user.getDeclaredMethods()) {
//            System.out.println("user的所有private方法===" + method);
        }
        // 通过反射获取user的
        User userObject = (User) user.newInstance();
        userObject.setName("yaojikai");
        System.out.println(userObject);
        // 通过反射获取user的
        Method setName = user.getMethod("setName", String.class);
        setName.invoke(userObject,"jikaigg");
        Method getName = user.getMethod("getName");
        System.out.println(getName.invoke(userObject));
        // 通过反射获取user的
        // 通过反射获取user的
        // 通过反射获取user的


    }
}

class User extends Person implements Serializable {
    private Integer id;
    private String name;
    private List<String> cause;
    public Long phone;

    public User() {
    }

    public User(Integer id, String name, List<String> cause, Long phone) {
        this.id = id;
        this.name = name;
        this.cause = cause;
        this.phone = phone;
    }

    private Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCause() {
        return cause;
    }

    public void setCause(List<String> cause) {
        this.cause = cause;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cause=" + cause +
                ", phone=" + phone +
                '}';
    }
}

class Person {
    private String tname;
    public Integer tage;

    public Person() {
    }

    public Person(String tname, Integer tage) {
        this.tname = tname;
        this.tage = tage;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getTage() {
        return tage;
    }

    public void setTage(Integer tage) {
        this.tage = tage;
    }

    @Override
    public String toString() {
        return "Person{" +
                "tname='" + tname + '\'' +
                ", tage=" + tage +
                '}';
    }
}
