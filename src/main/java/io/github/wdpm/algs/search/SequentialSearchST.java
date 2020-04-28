package io.github.wdpm.algs.search;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import io.github.wdpm.algs.basic.Queue;

/**
 * SequentialSearchST是顺序查找的符号表(无序），使用链表实现。
 *
 * 注意：基于链表的实现以及顺序查找非常低效。不需要扩容，因为是链表。
 *
 * @Created evan
 * @Date 2020/3/19
 */
public class SequentialSearchST<Key, Value> {
    private int n;           // number of key-value pairs
    private Node first;      // the linked list of key-value pairs

    // a helper linked list data type
    private class Node {
        private Key key;
        private Value val;
        private Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SequentialSearchST() {
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }

        // when contains key, update its value and return
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }

        // 插入操作需要 N次比较，放于链表头部
        // when not contains key,insert the new Node to the head
        first = new Node(key, val, first);
        n++;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        // 未命中的查找需要 N次比较
        return null;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        first = delete(first, key);
    }

    // delete key in linked list beginning at Node x
    // warning: function call stack too large if table is large
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }


    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    // use queue to store keys
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }

    /**
     * In IDEA, you should click:
     * <p>
     * Run/Debug Configurations -> SequentialSearchST -> redirect input from , select file path like this(for example):
     * <br>
     * C:\IdeaProjects\algorithm\java-algorithm\src\main\resources\tinyST.txt
     *
     * <p>
     * output:
     * L 11
     * P 10
     * M 9
     * X 7
     * H 5
     * C 4
     * R 3
     * A 8
     * E 12
     * S 0
     * @param args
     */
    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
