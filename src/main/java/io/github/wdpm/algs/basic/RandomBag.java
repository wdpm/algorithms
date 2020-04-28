package io.github.wdpm.algs.basic;

import java.util.Iterator;

/**
 * 随机背包：能够存储一组元素
 *
 * TODO
 * 除了形容词随机之外，这份API 和Bag 的API是相同的，
 * 这意味着迭代应该随机访问背包中的所有元素（对于每次迭代，所有的N! 种排列出
 * 现的可能性均相同）。提示：用数组保存所有元素并在迭代器的构造函数中随机打乱它们的顺序
 *
 * @Created evan
 * @Date 2020/3/12
 */
public class RandomBag<Item> implements Iterable<Item> {
    public RandomBag() {
    }

    boolean isEmpty() {
        return true;
    }

    public int size() {
        return 0;
    }

    public void add(Item item) {

    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
