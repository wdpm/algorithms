package io.github.wdpm.algs.basic;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 说明不可变性：double[] a 本身不可变，但是它的元素可以改变值
 *
 * @Created evan
 * @Date 2020/3/9
 */
public class Vector {
    private final double[] coords;

    public Vector(final double[] a) {
        coords = a;
    }

    public static void main(String[] args) {
        double[] a = {1.0, 2.0, 3.0};
        Vector vector = new Vector(a);
        a[0]=4.0;
        System.out.println(vector.toString());//Vector[coords=[4.0, 2.0, 3.0]]
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Vector.class.getSimpleName() + "[", "]")
                .add("coords=" + Arrays.toString(coords))
                .toString();
    }
}