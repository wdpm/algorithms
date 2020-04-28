package io.github.wdpm.algs.basic;

import java.util.Arrays;

/**
 * @Created evan
 * @Date 2020/3/12
 */
public class ThreeSumFast {
    // 限制：要求入参数组不包含重复元素。
    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) {
            throw new IllegalArgumentException("array contains duplicate integers");
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                if (k > j) count++;
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
}
