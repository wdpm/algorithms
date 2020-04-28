package io.github.wdpm.algs.sort;

import edu.princeton.cs.algs4.StdDraw;

/**
 * 选择排序
 *
 * @Created evan
 * @Date 2020/3/14
 */
public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            // 将a[i]和a[i+1..N]中最小的元素交换
            for (int j = i + 1; j < N; j++) {
                if (SortUtils.less(a[j], a[min])) {
                    min = j;
                }
            }
            SortUtils.exchange(a, i, min);
        }
    }

    private static void sortWithDraw(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            // 将a[i]和a[i+1..N]中最小的元素交换
            for (int j = i + 1; j < N; j++) {
                if (SortUtils.less(a[j], a[min])) {
                    min = j;
                }
            }
            SortUtils.exchange(a, i, min);
            SortDrawUtils.draw(a);
            StdDraw.pause(1000);
            SortDrawUtils.clear();
        }
        SortDrawUtils.draw(a);
    }

    public static void main(String[] args) {
        int N = 10;
        Comparable[] a = new Comparable[N];
        for (int i = 0; i < N; i++) {
            a[i] = Math.random();
        }
        sortWithDraw(a);
    }
}
