package com.jikaigg.note.algorithm.base;

import java.util.*;

/**
 * 输入:
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * 输出: "ball"
 * 解释:
 * "hit" 出现了3次，但它是一个禁用的单词。
 * "ball" 出现了2次 (同时没有其他单词出现2次)，所以它是段落里出现次数最多的，且不在禁用列表中的单词。
 * 注意，所有这些单词在段落里不区分大小写，标点符号需要忽略（即使是紧挨着单词也忽略， 比如 "ball,"），
 * "hit"不是最终的答案，虽然它出现次数更多，但它在禁用单词列表中。
 */
public class Base6 {
    public static void main(String[] args) {
//        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String paragraph = "a.";
        String[] hit = {"hit"};
        String s = muchString(paragraph, hit);
        System.out.println(s);
    }

    public static String muchString(String str, String[] list) {
        List<String> strings = Arrays.asList(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c))
                stringBuilder.append(c);
            else if (" ".equals((String.valueOf(c))))
                stringBuilder.append(c);
        }
        String lowerCase = stringBuilder.toString().toLowerCase();
        String[] s = lowerCase.split(" ");
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        for (String s1 : s) {
            if (!strings.contains(s1)) {
                if (stringIntegerHashMap.containsKey(s1)) {
                    stringIntegerHashMap.put(s1, stringIntegerHashMap.get(s1) + 1);
                }else {
                stringIntegerHashMap.put(s1, 1);}
            }
        }
        String mostKey = "";
        int most = 0;
        for (Map.Entry<String, Integer> stringIntegerEntry : stringIntegerHashMap.entrySet()) {
            if (stringIntegerEntry.getValue() > most) {
                most = stringIntegerEntry.getValue();
                mostKey = stringIntegerEntry.getKey();
            }
        }
        return mostKey;
    }


    public String mostCommonWord(String paragraph, String[] banned) {
        // 第二次提交，估计第一次是split用时过多16ms，这次直接遍历字符串，成功（用时+内存：100%，81%）
        String ans = "";                                // 出现最多次的单词
        int max = 0,len = paragraph.length();           // max：出现最多单词的次数；len：字符串长度
        paragraph = paragraph.toLowerCase();            // 先变小写
        Set<String> set = new HashSet();                // 存放需排除的串，后续判断是否跳过
        HashMap<String,Integer> map = new HashMap();    // 存放已遍历的单词和出现次数
        char[] arr = paragraph.toCharArray();           // 字符串转字符数组
        for(String s:banned)set.add(s.toLowerCase());   // 添加需排除的值
        for(int i=0,j=0;j<=len;j++){                    // i和j用于记录遍历位置，即分割字符串的左右两端，注意这里结束条件j<=len
            if(j<len && arr[j]>='a' && arr[j]<='z')continue;    // 找到的是字母，直接跳过
            if(i<j){                                    // 找到 i<j 使得 i与j 之间成单词字符串
                String x = paragraph.substring(i,j);    // 分割字符串成 x
                if(!set.contains(x)){                   // 跳过判断
                    int count = map.getOrDefault(x,0)+1;// 字符串 x 的出现次数
                    map.put(x,count);                   // 记录单词和个数
                    if(count>max)ans = x;               // 当前单词重复次数更多，替换ans
                    max = Math.max(count,max);          // 单词最大重复次数
                }
            }
            i = j+1;        // 处理完i到j的字符串后 重置i
        }
        return ans;
    }
}
