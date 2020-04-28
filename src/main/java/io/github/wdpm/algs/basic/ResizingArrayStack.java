package io.github.wdpm.algs.basic;

import java.util.Iterator;

/**
 * 泛型扩容栈（使用数组结构实现）
 *
 * @Created evan
 * @Date 2020/3/10
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] a; // stack entries
    private int N; // size

    public ResizingArrayStack(int cap) {
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
            resize(2 * a.length);
        }

        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null;// force GC tails element
        // if N equals to array.length/4, then resize capability to a.length/2 to avoid memory waste
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
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
        ResizingArrayStack stack = new ResizingArrayStack(1);
        stack.push("to");
        stack.push("be");
        stack.push("or");
        stack.push("not");
        stack.push("to");
        stack.pop();//to
        stack.push("be");
        stack.pop();//be
        stack.pop();//not
        stack.push("that");
        stack.pop();//that
        stack.pop();//or
        stack.pop();//be
        stack.push("is");
        System.out.println(stack.toString());

        Iterator it = stack.iterator();
        while (it.hasNext()) {
            String next = (String) it.next();
            System.out.println(next);
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * 支持后进先出的迭代
     */
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return a[--i];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
