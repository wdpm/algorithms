package io.github.wdpm.algs.sort;

import edu.princeton.cs.algs4.StdRandom;
import io.github.wdpm.algs.basic.Stopwatch;

/**
 * 比较不同排序算法的性能
 *
 * @Created evan
 * @Date 2020/3/15
 */
public class SortCompare {
    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        if (alg.equals("Selection")) Selection.sort(a);
        if (alg.equals("Shell")) Shell.sort(a);
        if (alg.equals("MergeTopDown")) MergeTopDown.sort(a);
        if (alg.equals("Quick")) Quick.sort(a);
        if (alg.equals("Heap")) Heap.sort(a);
        return timer.elapsedTime();
    }

    // 使用算法alg将T个长度为N的数组排序
    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) { // 进行一次测试（生成一个数组并排序)
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }

    /**
     * Usage example:
     * <p>
     * java SortCompare Insertion Selection 1000 100
     * java SortCompare Shell Insertion 100000 100
     * </p>
     *
     * Result example:
     * <p>
     * For  1000 random Doubles
     * <br>
     * Insertion is 1.2 times faster than Selection
     * <br>
     * Shell is 5.7 times faster than Selection
     * </p>
     * @param args
     */
    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T); // 算法1的总时间
        double t2 = timeRandomInput(alg2, N, T); // 算法2的总时间
        System.out.print(String.format("For % d random Doubles\n%s is ", N, alg1));
        System.out.println(String.format("%.1f times faster than %s\n", t2 / t1, alg2));
    }
}
