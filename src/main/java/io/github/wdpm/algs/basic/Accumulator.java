package io.github.wdpm.algs.basic;

import java.util.StringJoiner;

/**
 * @Created evan
 * @Date 2020/3/9
 */
public class Accumulator {
    private int N;
    private double total;

    public Accumulator() {

    }

    public void addDataValue(double value) {
        N++;
        this.total += value;
    }

    public double mean() {
        return this.total / N;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Accumulator.class.getSimpleName() + "[", "]")
                .add("N=" + N)
                .add("total=" + total)
                .toString();
    }
}
