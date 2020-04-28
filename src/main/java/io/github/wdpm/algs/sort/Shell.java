package io.github.wdpm.algs.sort;

/**
 * @Created evan
 * @Date 2020/3/16
 */
public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;

        // 选择初始 h
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1; //1,4,13,40,...
        }

        // 迭代递减 h到 1
        while (h >= 1) {
            // 将数组变为h有序，使用插入排序算法
            for (int i = h; i < N; i++) {
                // 将a[i]插入到 ..,a[i-3*h],a[i-2*h],a[i-h] 之中
                for (int j = i; j >= h && SortUtils.less(a[j], a[j - h]); j = j - h) {
                    SortUtils.exchange(a, j, j - h);
                }
            }
            h = h / 3;// ..,40,13,4,1,0
        }

        // 只要 h经过 1，排序完成。
    }

}
