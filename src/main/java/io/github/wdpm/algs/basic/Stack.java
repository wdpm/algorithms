package io.github.wdpm.algs.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 泛型扩容栈（使用链表结构实现）
 *
 * TODO fail-fast iterator
 * 快速出错的迭代器。修改Stack 的迭代器代码， 确保一旦用例在迭代器中（ 通过push()
 * 或pop() 操作）修改集合数据就抛出一个java.util.ConcurrentModificationException 异常。
 * 解答：用一个计数器记录push() 和pop() 操作的次数。在创建迭代器时，将该值记录到
 * Iterator 的一个实例变量中。在每次调用hasNext() 和next() 之前，检查该值是否发生了变化，
 * 如果变化则抛出异常
 *
 * @Created evan
 * @Date 2020/3/9
 */
public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first; // top element of stack
    private int n;// size of the stack

    public Stack() {
        first = null;
        n = 0;
    }

    public void push(Item item) {
        Node<Item> oldFirst = this.first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;

        n++;
    }

    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item oldFirstItem = this.first.item;
        first = first.next;
        n--;

        return oldFirstItem;
    }

    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return this.first.item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(" ");
        }
        return s.toString();
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
                throw new NoSuchElementException("Stack underflow");
            }
            Item oldFirstItem = this.current.item;
            current = current.next;
            return oldFirstItem;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
