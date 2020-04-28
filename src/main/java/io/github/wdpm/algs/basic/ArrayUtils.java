package io.github.wdpm.algs.basic;

public class ArrayUtils {

    private double findBiggestElement(double[] a) {
        double max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    private double computeAverageValue(double[] a) {
        int N = a.length;
        double sum = computeSum(a);
        return sum / N;
    }

    private double computeSum(double[] a) {
        double sum = 0.0;
        for (double value : a) {
            sum += value;
        }
        return sum;
    }

    private double[] copyArray(double[] a) {
        int N = a.length;
        double[] copy = new double[N];
        for (int i = 0; i < a.length; i++) {
            copy[i] = a[i];
        }
        return copy;
    }

    private void reverse(double[] a) {
        int N = a.length;
        //取中间点，左右分别交换值
        for (int i = 0; i < N / 2; i++) {
            double temp = a[i];
            a[i] = a[N - 1 - i];
            a[N - 1 - i] = temp;
        }
    }

    /**
     * 矩阵相乘
     *
     * TODO
     * 1. 考虑二维数组的第二维长度，优化 new double[N][N]
     * 2. 检测 a[M][N] * b[N][K] 中 N 限制，否则无法相乘
     * @param a
     * @param b
     * @return
     */
    private double[][] multiply(double[][] a, double[][] b) {
        int N = a.length;
        double[][] c = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    c[i][j] += a[i][k] + b[k][j];
                }
            }
        }
        return c;
    }
}
