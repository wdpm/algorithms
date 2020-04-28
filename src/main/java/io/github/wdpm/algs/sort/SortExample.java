package io.github.wdpm.algs.sort;

/**
 * @Created evan
 * @Date 2020/3/14
 */
public abstract class SortExample {
    public static void sort(Comparable[] a) {
        // call certain sort implementation
//        Selection.sort(a);
//        Insertion.sort(a);
        Shell.sort(a);
    }

    // 读取字符串，将它们排序并输出
    public static void main(String[] args) {
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        assert SortUtils.isSorted(a);
        SortUtils.show(a);
    }
}
