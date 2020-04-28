package io.github.wdpm.algs.basic;

import java.util.Iterator;

/**
 * 一个双向队列（或者称为deque）和栈或队列类似，但它同时支持在两端添加或删除元素
 * TODO
 * 1.编写一个使用双向链表实现这份API 的 Deque
 * 2.一个使用动态数组调整实现这份API 的 ResizingArrayDeque
 * ResizingArrayDeque 类。
 * @Created evan
 * @Date 2020/3/10
 */
public class Deque<Item> implements Iterable<Item> {
    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return 0;
    }

    void pushLeft(Item item) {

    }

    void pushRight(Item item) {

    }

    Item popLeft() {
        return null;
    }

    Item popRight() {
        return null;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
