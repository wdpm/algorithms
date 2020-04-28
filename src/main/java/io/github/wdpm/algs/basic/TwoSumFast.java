package io.github.wdpm.algs.basic;

import java.util.Arrays;

/**
 * 注意：
 * 假设输入数组中含有重复元素：[-9, -3, -2, -1, 1, 2, 2, 3, 5, 9]
 * 那么二分查找-(-2)时，找到第一个a[5]=2时就返回了，因此会遗漏a[6]=2。
 * @Created evan
 * @Date 2020/3/13
 */
public class TwoSumFast {
    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        if (containsDuplicates(a)) {
            throw new IllegalArgumentException("array contains duplicate integers");
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            int k = Arrays.binarySearch(a, -a[i]);
            if (k > i) {
                count++;
            }
        }
        return count;
    }

    // returns true if the sorted array a[] contains any duplicated integers
    private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i - 1]) return true;
        return false;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, -1, 2, -2,-3, 5, 9, -9};
        int count = count(a);
        System.out.println(count);
    }
}
