package com.jikaigg.note.feign.test;


// 目标接口
interface Duck {  // 鸭子接口
    void quack();  // 鸭子会呱呱叫

    void fly();  // 鸭子会飞
}

// 目标接口的正常实现（鸭子实现）
class PsyDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("Psyuck is quack !");
    }

    @Override
    public void fly() {
        System.out.println("Psyuck is fly !");
    }
}

// 现有接口
interface Turkey {   // 火鸡接口
    void gobble();  // 火鸡会咯咯叫

    void fly();  // 火鸡会飞
}

// 一般实现
class BigTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("BigTurkey is gobble ！");
    }

    @Override
    public void fly() {
        System.out.println("BigTurkey is fly ！");
    }
}

class TurkeyAdapter implements Duck {
    private BigTurkey bigTurkey;

    public TurkeyAdapter(BigTurkey bigTurkey) {
        this.bigTurkey = bigTurkey;
    }

    @Override
    public void quack() {
        bigTurkey.gobble();
        System.out.println("适配出来的火鸡鸭");
    }

    @Override
    public void fly() {
        bigTurkey.fly();
    }
}

public class adapter {
    public static void main(String[] args) {
        System.out.println();
    }
}


