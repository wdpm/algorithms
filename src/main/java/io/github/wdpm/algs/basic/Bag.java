package io.github.wdpm.algs.basic;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 背包（链表实现）：支持插入元素，随机顺序迭代元素
 *
 * @Created evan
 * @Date 2020/3/9
 */
public class Bag<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Bag() {
        first = null;
        n = 0;
    }

    public void add(Item item) {
        Node<Item> oldFirst = this.first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }
    }

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        while(!StdIn.isEmpty()){
            String s = StdIn.readString();
            bag.add(s);
        }
        StdOut.println("size of bag= "+bag.size());
        for (String s : bag) {
            System.out.println(s);
        }
    }
}
