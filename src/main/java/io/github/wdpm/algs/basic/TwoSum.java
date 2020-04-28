package io.github.wdpm.algs.basic;

/**
 * @Created evan
 * @Date 2020/3/13
 */
public class TwoSum {
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] + a[j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, -1, 2, -2,-3, 5, 9, -9};
        int count = count(a);
        System.out.println(count);
    }
}
