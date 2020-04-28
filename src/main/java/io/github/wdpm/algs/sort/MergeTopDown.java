package io.github.wdpm.algs.sort;

/**
 * 归并排序（自顶向下）
 * @Created evan
 * @Date 2020/3/16
 */
public class MergeTopDown {
    private static Comparable[] aux; // 归并所需的辅助数组

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length]; // 一次性分配空间
        sort(a, 0, a.length - 1);
        assert SortUtils.isSorted(a);
    }

    // 将数组a[lo..hi]排序
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid); // 将左半边排序
        sort(a, mid + 1, hi); // 将右半边排序
        merge(a, lo, mid, hi); // 归并结果
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        // 将a[lo..mid] 和 a[mid+1..hi] 归并
        int i = lo, j = mid + 1;//i,j分别是两个子数组的开头索引

        // 将a[lo..hi]复制到aux[lo..hi]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        /*
          归并回到a[lo..hi]
          1.左半边用尽（取右半边的元素）
          2.右半边用尽（取左半边的元素）
          3.右半边的当前元素小于左半边的当前元素（取右半边的元素）
          4.右半边的当前元素大于等于左半边的当前元素（取左半边的元素）
         */
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (SortUtils.less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
}
