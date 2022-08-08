package com.jikaigg.note.test.Algotithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * 百度面试题：
 * 假设有一个大水池，其容积为poolSize，还有n个彼此大小不同的水桶，分别是B1,B2,B3,B4,B5..Bn，放在一个数组中
 * 请给出水桶的所有组合，使得他们的容积之和刚好可以灌满整个水池poolSize
 *
 * @author charles.wang
 *
 */
public class Baidu1 {
    // 放置可能的解
    private static List<Integer> list = new ArrayList<Integer>();
    /**
     * 这是一个wrapper方法，用于做一些参数排序处理，和排除所有绝对不可能作为候选列表的桶，以免减少运行时间开销
     *
     * @param poolSize 池的大小
     * @param buckets 所有候选桶的数组
     */
    public static void fillPoolWithBuckets(int poolSize, int[] buckets) {
        // 局部变量，作为真正参与运算的buckets
        int[] actualBuckets = buckets;
        // 首先，吧所有的水桶按照升序排序，这样方便我们找到最大的桶
        Arrays.sort(actualBuckets);
        // 判断如果某个buckets的大小超过了池子，那么从这个bucket开始，比它大的buckets都不考虑
        // 于是缩减原来的buckets数组为新的buckets数组，并且只有不超过poolSize的buckets会列在其中
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] > poolSize) {
                actualBuckets = Arrays.copyOf(actualBuckets, i);
                break;
            }
        }
        // 递归的调用方法来输出结果
        findSolution(poolSize, actualBuckets);
    }
    /**
     * 递归方法，用当前的可选已经排过序的桶的列表来填充一个池子poolSize
     *
     * @param poolSize
     * @param sortedBuckets
     */
    private static void findSolution(int poolSize, int[] sortedBuckets) {
        // 递归的出口，是当前没有可用的桶或者需要凑容积的池子尺寸已经为0了
        if ((sortedBuckets.length == 0) || (poolSize <= 0))
            return;
        // 输出结果
        //输出结果的条件是当前最大桶（已经排序过)直接满足条件，那么当前最大桶就作为可用解的一个值
        //比如用桶{1,2,3}去凑水池容积为3, 那么一旦找到3,那么就直接输出3
        //而解访问两部分，一个是已有的在计算子匹配之前的已经选出的桶的列表，在list列表中存着呢
        //另一个是找到的当前的解，所以依次输出已选桶的列表(来自list)和当前桶
        if (poolSize == sortedBuckets[sortedBuckets.length - 1]) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + ",");
            }
            System.out.println(poolSize);
        }

        //如果最大桶无法直接满足池子的容量，那么就必须用若干小桶来凑池子的容量，为此，先选出小桶中最大的那个。

        // 最大的水桶
        int largestBucket = sortedBuckets[sortedBuckets.length - 1];
        //现在解决方案分2部分：
        //(1)放入可用桶桶列表中最大的这个到可用列表中，并且这个桶是有解的，
        //   则用桶列表剔除最大桶的子桶列表来凑 剩余池子容量-最大桶容量，这是个递归调用
        //(2)放入最大桶进去之后，是无解的，那么从可用列表中剔除这个最大桶
        //   并且用桶列表剔除最大桶的子桶列表来凑 剩余池子容量，这是也个递归调用

        //放入最大的水桶到可用列表，递归解决其他的水桶来凑 池容量-最大桶容量
        list.add(largestBucket);
        findSolution(poolSize - largestBucket,
                Arrays.copyOf(sortedBuckets, sortedBuckets.length - 1));
        //不放入最大的水桶，递归的解决其他的水桶来凑 池容量
        list.remove(list.size() - 1);
        findSolution(poolSize,
                Arrays.copyOf(sortedBuckets, sortedBuckets.length - 1));
    }
    public static void main(String[] args) {
        int poolSize = 31;
        int[] buckets = { 6, 8, 7, 12, 17, 28, 23, 1, 3, 16 };

        fillPoolWithBuckets(poolSize, buckets);
    }
}
