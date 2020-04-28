package io.github.wdpm.algs.sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 三向切分的快速排序
 *
 * @Created evan
 * @Date 2020/3/17
 */
public class Quick3way {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert SortUtils.isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) SortUtils.exchange(a, lt++, i++);
            else if (cmp > 0) SortUtils.exchange(a, i, gt--);
            else i++;
        }
        // 此时 i==gt
        // 现在 a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]成立
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        Comparable[] ints = {5, 6, 8, 9, 9, 5, 6, 6, 1, 2};
        sort(ints);
        SortUtils.show(ints);
    }

}
