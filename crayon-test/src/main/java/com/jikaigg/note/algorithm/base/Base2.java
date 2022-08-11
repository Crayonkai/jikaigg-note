package com.jikaigg.note.algorithm.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 */
public class Base2 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};// -4,-1,-1,0,1,2
        List<List<Integer>> list = threeSum(nums);
        System.out.println(list);

    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> arrayList = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (nums[i] == nums[j]) break;
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[k] == nums[j]) break;
                    List<Integer> integers = new ArrayList<>();
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        integers.add(nums[i]);
                        integers.add(nums[j]);
                        integers.add(nums[k]);
                        arrayList.add(integers);
                    }
                    System.out.print(" 第一个元素是" + nums[i]);
                    System.out.print(",第一个元素是" + nums[j]);
                    System.out.println(",第一个元素是" + nums[k]);
                }
            }
        }
        return arrayList;
    }

    public List<List<Integer>> threesum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}



