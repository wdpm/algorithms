package io.github.wdpm.algs.basic;

import java.util.Arrays;

/**
 * 思路：从数组两端向中间逼近匹配
 * @Created evan
 * @Date 2020/3/13
 */
public class TwoSumFaster {
    public static int count(int[] a) {
        int n = a.length;
        Arrays.sort(a);
//        System.out.println(Arrays.toString(a));

        int count = 0;
        for (int head = 0, tail = n - 1; head < tail; ) {
            if (a[head] + a[tail] >= 0) {
                if (a[head] + a[tail] == 0) {
                    count++;
                }
                tail--;
            } else {
                head++;
            }

        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, -1, -2, 2,-3, 5, 9, -9};
        int count = count(a);
        System.out.println(count);
    }
}
