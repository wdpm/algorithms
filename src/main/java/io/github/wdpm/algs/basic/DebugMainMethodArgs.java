package io.github.wdpm.algs.basic;

import java.util.Arrays;

/**
 * Usage:Edit Configurations -> Application -> Program arguments: 123 456
 *
 * @Created evan
 * @Date 2020/3/9
 */
public class DebugMainMethodArgs {
    public static void main(String[] args) {
        System.out.println(args.length);
        System.out.println(Arrays.toString(args));
    }
}
