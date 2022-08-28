package com.jikaigg.note.algorithm.base;

import java.util.HashMap;

public class Base8 {
    public static void main(String[] args) {
        String str = "a a";
        int i = lengthOfLongestSubstring(str);
        System.out.println(i);
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (s.charAt(left) == s.charAt(i)) {
                    left += 1;
                } else if (map.get(s.charAt(i))>left){
                    left=map.get(s.charAt(i))+1;
                }
                map.remove(s.charAt(i));
            }
            map.put(s.charAt(i), i);
            max = max > i - left + 1 ? max : i - left + 1;
        }
        return max;
    }
}
