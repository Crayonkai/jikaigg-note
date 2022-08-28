package com.jikaigg.note.algorithm.base;

public class Base10 {
    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] list = new int[len];
        int a = 0, b = 0;
        for (int i = 0; i < len; i++) {
            if (a < nums1.length && b < nums2.length && nums1[a] <= nums2[b]) {
                list[i] = nums1[a];
                a++;
            } else if (b == nums2.length){
                list[i] = nums1[a];
                a++;
            } else {
                list[i] = nums2[b];
                b++;
            }

        }
        if (list.length % 2 == 0) {
            return (list[list.length / 2] + list[list.length / 2 - 1]) / 2.0;
        }
        return list[list.length / 2];
    }
}
