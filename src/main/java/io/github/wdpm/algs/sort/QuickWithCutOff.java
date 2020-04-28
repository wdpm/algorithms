package io.github.wdpm.algs.sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 切换到插入排序的快速排序
 *
 * @Created evan
 * @Date 2020/3/16
 */
public class QuickWithCutOff {
    private static final int CUTOFF = 15;

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a); // 消除对输入的依赖
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + CUTOFF) {
            Insertion.sort(a);
            return;
        }
        int j = partition(a, lo, hi); // 切分
        sort(a, lo, j - 1); // 将左半部分a[lo .. j-1]排序
        sort(a, j + 1, hi); // 将右半部分a[j+1 .. hi]排序
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // 将数组切分为a[lo..i-1], a[i], a[i+1..hi]
        int i = lo, j = hi + 1; // 左右扫描指针
        Comparable v = a[lo]; // 切分元素
        while (true) { // 扫描左右，检查扫描是否结束并交换元素
            while (SortUtils.less(a[++i], v)) if (i == hi) break;
            while (SortUtils.less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            SortUtils.exchange(a, i, j);
        }
        SortUtils.exchange(a, lo, j); // 将v = a[j]放入正确的位置，a[j]为切分值
        return j; // a[lo..j-1] <= a[j] <= a[j+1..hi] 达成
    }

    public static void main(String[] args) {
        int N = 100;
        Comparable[] a = new Comparable[N];
        for (int i = 0; i < N; i++) {
            a[i] = Math.random();
        }
        sort(a);
        SortUtils.show(a);
    }
}
