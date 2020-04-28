package io.github.wdpm.algs.sort;

/**
 * 排序工具类
 *
 * @Created evan
 * @Date 2020/3/16
 */
public class SortUtils {
    public static void show(Comparable[] a) {
        for (Comparable comparable : a) {
            System.out.println(comparable);
        }
    }

    public static void exchange(Comparable[] a, int i, int j) {
        Comparable tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // 测试数组元素是否有序(升序）
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

}
