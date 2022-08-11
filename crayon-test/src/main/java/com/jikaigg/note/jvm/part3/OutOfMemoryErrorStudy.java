package com.jikaigg.note.jvm.part3;

import java.util.Random;

public class OutOfMemoryErrorStudy {
    public static void main(String[] args) throws InterruptedException {
        String s = new Random(1024 * 20).toString();
//        Byte[] aByte = new Byte[new Random().nextInt(1024*20)];
        String str = "yaojikaiaishimengyu";
        String result = null;
        while (true){
            result+=str;
            Thread.sleep(10);
        }
    }
}
