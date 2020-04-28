package io.github.wdpm.algs.basic;

/**
 * 泛型定容栈
 *
 * @Created evan
 * @Date 2020/3/10
 */
public class FixedCapacityStack<Item> {
    private Item[] a; // stack entries
    private int N; // size

    public FixedCapacityStack(int cap) {
        a = (Item[]) new Object[cap];
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

    public void push(Item item) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException(N);
        }

        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;// force GC tails element
        return item;
    }

    public void resize(int newSize) {
        Item[] newItems = (Item[]) new Object[newSize];
        for (int i = 0; i < N; i++) {
            newItems[i] = a[i];
        }
        a = newItems;//reset reference
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item s : a) {
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        FixedCapacityStack stack = new FixedCapacityStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Integer pop3 = (Integer) stack.pop();
        Integer pop2 = (Integer) stack.pop();
        Integer pop1 = (Integer) stack.pop();
        System.out.println(pop3);
        System.out.println(pop2);
        System.out.println(pop1);
    }
}
