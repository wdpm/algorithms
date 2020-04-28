package io.github.wdpm.algs.sort;

/**
 * 归并排序（自底向上）
 *
 * @Created evan
 * @Date 2020/3/16
 */
public class MergeBottomUp {
    private MergeBottomUp() {
    }

    // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];  // this copying is unneccessary
            else if (j > hi)               a[k] = aux[i++];
            else if (SortUtils.less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        for (int len = 1; len < n; len *= 2) {//len=1,2,4,8,...
            for (int lo = 0; lo < n-len; lo += len+len) {//lo+=2,4,8,16,...
                int mid  = lo+len-1;//[lo+(lo+len+len-1)]/2=lo+len-1
                int hi = Math.min(lo+len+len-1, n-1);//因为 lo += len+len，所以 hi=lo+len+len-1
                merge(a, aux, lo, mid, hi);
            }
        }
        assert SortUtils.isSorted(a);
    }

    public static void main(String[] args) {
        int N = 10;
        Comparable[] a = new Comparable[N];
        for (int i = 0; i < N; i++) {
            a[i] = Math.random();
        }
        MergeBottomUp.sort(a);
        SortUtils.show(a);
    }
}
