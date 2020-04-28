package io.github.wdpm.algs.basic;

public class Sqrt {
    public static void main(String[] args) {
        double sqrt9 = sqrt(2);
        System.out.println(sqrt9);
    }

    /**
     * 牛顿迭代法求平方根（正根）
     *
     * 参考：https://www.guokr.com/question/461510/
     * @param c
     * @return
     */
    private static double sqrt(double c) {
        if (c < 0) return Double.NaN;
        double err = 1e-15;//误差精度
        double t = c;
        while (Math.abs(t - c / t) > err * t) {
            t = (t + c / t) / 2.0;
        }
        return t;
    }
}
