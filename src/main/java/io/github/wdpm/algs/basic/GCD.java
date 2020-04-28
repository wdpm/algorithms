package io.github.wdpm.algs.basic;

public class GCD {
    public static void main(String[] args) {
        int gcd = gcd(12, 8);
        System.out.println(gcd);
    }

    /**
     * 欧几里得算法。计算两个非负整数 p 和 q 的最大公约数。
     *
     * 步骤：
     * 若 q 是 0，则最大公约数为 p。
     * 否则，将 p 除以 q 得到余数 r ，p 和 q 的最大公约数即为 q 和 r 的最大公约数。
     * @param p 非负整数
     * @param q 非负整数
     * @return 最大公约数
     */
    private static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }
}
