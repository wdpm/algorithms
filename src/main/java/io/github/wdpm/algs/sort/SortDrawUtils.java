package io.github.wdpm.algs.sort;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

/**
 * @Created evan
 * @Date 2020/3/16
 */
public class SortDrawUtils {
    // 绘制矩形
    public static void draw(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            double x = 1.0 * i / N;
            double y = (double) a[i] / 2.0;
            double halfWidth = 0.5 / N;
            double halfHeight = (double) a[i] / 2.0;
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.filledRectangle(x, y, halfWidth, halfHeight);
            StdDraw.setPenColor(Color.white);
            StdDraw.text(x, y, a[i].toString().substring(0,4));
        }
    }

    /**
     * 绘制线条
     * <p>
     * 当a[]数组长度变大时，线条高度会变得比较短，可手动调节yExpand参数使得画面空白减少。
     *
     * @param a
     */
    public static void showAnimation(Comparable[] a) {
        StdDraw.setXscale(0.0, a.length);
        StdDraw.setYscale(0.0, a.length);
        StdDraw.setPenRadius(0.01);
        StdDraw.pause(100);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.setPenColor(StdDraw.BLACK);
        int yExpand = 5;
        double yPadding = 0.5;
        for (int i = 0; i < a.length; i++) {
            //x0,y0,x1,y1
            StdDraw.line(i * 1.0, 0.0, i * 1.0, (Double) a[i] + yExpand);
            StdDraw.text(i * 1.0, (Double) a[i] + yExpand + yPadding, a[i].toString().substring(0, 4));
        }
    }

    // 清除
    public static void clear() {
        StdDraw.clear();
    }
}
