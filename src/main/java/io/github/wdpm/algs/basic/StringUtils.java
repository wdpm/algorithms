package io.github.wdpm.algs.basic;

/**
 * @Created evan
 * @Date 2020/3/9
 */
public class StringUtils {
    public static boolean isPalindrome(String s) {
        int N = s.length();
        for (int i = 0; i < N / 2; i++) {
            if (s.charAt(i) != s.charAt(N - 1 - i)) {
                return false;
            }
        }
        return true;

    }

    public static boolean isDictSorted(String[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1].compareTo(a[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}
