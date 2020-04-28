package io.github.wdpm.algs.basic;

/**
 * 定容栈
 *
 * @Created evan
 * @Date 2020/3/10
 */
public class FixedCapacityStackOfStrings {
    private String[] a; // stack entries
    private int N; // size

    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean isFull() {
        return N == a.length;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        a[N++] = item;
    }

    public String pop() {
        return a[--N];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : a) {
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(3);
        stack.push("1");
        stack.push("2");
        stack.push("3");
        String pop3 = stack.pop();
        String pop2 = stack.pop();
        String pop1 = stack.pop();
        System.out.println(pop3);
        System.out.println(pop2);
        System.out.println(pop1);
    }
}
