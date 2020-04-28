package io.github.wdpm.algs.basic;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 算数表达式计算值。
 *
 * <p>实现步骤：
 * <li> 1. 将操作数压入操作数栈；</li>
 * <li> 2. 将运算符压入运算符栈；</li>
 * <li> 3. 忽略左括号；</li>
 * <li> 4. 在遇到右括号时，弹出一个运算符，弹出所需数量的操作数，并将运算符和操作数的运算结果压入操作数栈</li>
 * </p>
 * <br>
 *
 * <p>运行步骤：
 * <li>1. 控制台输入 ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) 后按 enter 键</li>
 * <pre>
 * 5.0 1.0
 * 20.0 5.0 1.0
 * 100.0 1.0
 * 101.0
 * </pre>
 * <li>2. windows平台下按Ctrl D 结束输入</li>
 * <pre>
 * ^D
 * 101.0
 * </pre>
 * </p>
 *
 * @Created evan
 * @Date 2020/3/10
 */
public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!StdIn.isEmpty()) { // 读取字符，如果是运算符则压入栈
            String s = StdIn.readString();
            switch (s) {
                case "(":
                    break;
                case "+":
                    ops.push(s);
                    break;
                case "-":
                    ops.push(s);
                    break;
                case "*":
                    ops.push(s);
                    break;
                case "/":
                    ops.push(s);
                    break;
                case "sqrt":
                    ops.push(s);
                    break;
                case ")":  // 如果字符为")"，弹出运算符和操作数，计算结果并压入栈
                    String op = ops.pop();
                    double v = vals.pop();
                    switch (op) {
                        case "+":
                            v = vals.pop() + v;
                            break;
                        case "-":
                            v = vals.pop() - v;
                            break;
                        case "*":
                            v = vals.pop() * v;
                            break;
                        case "/":
                            v = vals.pop() / v;
                            break;
                        case "sqrt":
                            v = Math.sqrt(v);
                            break;
                    }
                    vals.push(v);
                    System.out.println(vals.toString());
                    break;
                default:
                    vals.push(Double.parseDouble(s));
                    break;
            }
        }
        StdOut.println(vals.pop());
    }
}
