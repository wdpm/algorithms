package io.github.wdpm.algs.basic;

public class IsPrime {
    public static void main(String[] args) {
        boolean isPrime767 = isPrime(767);
        System.out.println(isPrime767);
    }

    private static boolean isPrime(int N) {
        if (N < 2) return false;
        for (int i = 2; i * i <= N; i++) {
            if (N % i == 0) return false;
        }
        return true;
    }
}
