package com.jikaigg.note.lambda;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 引用写法
 */
public class Lambda6 {
    public static void main(String[] args) {
        List<Dog> list = new ArrayList<>();
        list.add(new Dog(2,"aa"));
        list.add(new Dog(1,"bb"));
        list.add(new Dog(3,"cc"));
        list.add(new Dog(6,"dd"));
        list.add(new Dog(5,"ee"));
        System.out.println("lambda集合列表排序!");
        list.sort((a1,a2)->a1.getAge()- a2.getAge());
        System.out.println(list);

        System.out.println("lambda遍历集合列表");
        list.forEach(System.out::println);

    }


}
