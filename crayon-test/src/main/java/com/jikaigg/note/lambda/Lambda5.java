package com.jikaigg.note.lambda;

/**
 * 构造方法写法
 */
public class Lambda5 {
    public static void main(String[] args) {
        // 传统写法
        DogServiceA dogServiceA1 = ()->{
            return new Dog();
        };
        Dog dogA1 = dogServiceA1.getDog();
        System.out.println(dogA1);
        // 精简写法
        DogServiceA dogServiceA2 = ()->new Dog();
        Dog dogA2 = dogServiceA2.getDog();
        System.out.println(dogA2);
        // 构造方法写法
        DogServiceA dogServiceA3 = Dog::new;
        Dog dogA3 = dogServiceA2.getDog();
        System.out.println(dogA3);

        // 传统写法
        DogServiceB dogServiceB1 = (int a,String b)->{
            return new Dog(a,b);
        };
        Dog dogB1 = dogServiceB1.getDog(1,"aa");
        System.out.println(dogB1);
        // 精简写法
        DogServiceB dogServiceB2 = (int a,String b)->new Dog(a,b);
        Dog dogB2 = dogServiceB2.getDog(1,"aa");
        System.out.println(dogB2);
        // 构造方法写法
        DogServiceB dogServiceB3 = Dog::new;
        Dog dogB3 = dogServiceB2.getDog(1,"aa");
        System.out.println(dogB3);
    }

    interface DogServiceA {
        Dog getDog();
    }

    interface DogServiceB {
        Dog getDog(int age, String name);
    }

}

class Dog {
    int age;
    String name;

    public Dog() {
        System.out.println("无参构造方法");
    }

    public Dog(int age, String name) {
        System.out.println("有参构造方法");
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
