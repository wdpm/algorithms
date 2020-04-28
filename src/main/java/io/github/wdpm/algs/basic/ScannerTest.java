package io.github.wdpm.algs.basic;

import java.util.Scanner;

/**
 * @Created evan
 * @Date 2020/3/10
 */
public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()){
            System.out.println(scanner.next());
        }
    }
}
