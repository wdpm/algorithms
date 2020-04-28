package io.github.wdpm.algs.sort;

import io.github.wdpm.algs.basic.Date;

/**
 * @Created evan
 * @Date 2020/3/14
 */
public class ComparableDate extends Date {

    public ComparableDate(int m, int d, int y) {
        super(m, d, y);
    }

    public static void main(String[] args) {
        ComparableDate comparableDate1 = new ComparableDate(2020,3,15);
        ComparableDate comparableDate2 = new ComparableDate(2020,3,15);
        int isSameDate = comparableDate1.compareTo(comparableDate2);
        System.out.println(isSameDate);//0
    }
}
