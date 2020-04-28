package io.github.wdpm.algs.sort;

/**
 * 插入排序
 *
 * @Created evan
 * @Date 2020/3/14
 */
public class Insertion {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            // 将 a[i] 插入到 a[i-1]、a[i-2]、a[i-3]...a[0]之中,其中 a[i-1]...a[0] 已经有序
            for (int j = i; j > 0 && SortUtils.less(a[j], a[j - 1]); j--) {
                SortUtils.exchange(a, j, j - 1);
            }
        }
    }

    private static void sortWithDraw(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            // 将 a[i] 插入到 a[i-1]、a[i-2]、a[i-3]...a[0]之中,其中 a[i-1]...a[0] 已经有序
            for (int j = i; j > 0 && SortUtils.less(a[j], a[j - 1]); j--) {
                SortUtils.exchange(a, j, j - 1);
                SortDrawUtils.showAnimation(a);
            }
        }
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
