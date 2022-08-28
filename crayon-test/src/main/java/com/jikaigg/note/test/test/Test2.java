package com.jikaigg.note.test.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test2 {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("yao", "jikai");
        map.put("age", 18);
        map.put("name", "蜡笔");
        map.put("OK", "ok");
        map.put("money", 3.14F);
        // 方式一:
        Set<String> strings = map.keySet();
        for (String string : strings) {
            System.out.println("方式一  key : " + string + ", value : " + map.get(string));
        }
        System.out.println("=============================");
        // 方式二:
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("方式二  key : " + entry.getKey() + ", value : " + entry.getValue());
        }
        // 方式三:
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getValue());
        }
    }
}
