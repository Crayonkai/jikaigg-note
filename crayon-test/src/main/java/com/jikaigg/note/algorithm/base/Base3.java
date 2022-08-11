package com.jikaigg.note.algorithm.base;

/*
 * /*
 * 脑筋急转弯
 * 猴子第一天摘下若干个桃子，当即吃了一半不过瘾又多吃了一个，第二天早上又将剩下的桃子吃了一半，又多吃了一个，以后每天早上都吃了前一天剩下的一半零一个
 * 到第十天早上想在吃时，见只剩下一个桃子，求第一天一共摘了多少桃子
 */
public class Base3 {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            Integer integer = monkeyEat(i);
            Integer integer1 = monkeyEatF(i);
            System.out.println("常规：第 "+(11-i)+" 天猴子有 "+integer+" 颗桃子；  递归：第 "+(11-i)+" 天猴子有 "+integer1+" 颗桃子");
        }
    }
    public static Integer monkeyEat(Integer day) {
        Integer count = 1;
        if (day == 1)
            return 1;
        for (int i = 0; i < day-1; i++) {
            count = (count + 1) * 2;
        }
        return count;
    }
    public static Integer monkeyEatF(Integer day) {
        if (day == 1)
            return 1;
        else
            return (monkeyEatF(day -1) + 1) * 2;
    }
}
