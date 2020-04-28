package io.github.wdpm.algs.sort;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * 基于堆的优先队列（最大堆）,二叉树表示
 *
 * Key extends Comparable<Key> 是为了让 Key 对象支持 compareTo() 方法
 *
 * @Created evan
 * @Date 2020/3/17
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq; // 基于堆的完全二叉树,存储于pq[1..N]中，pq[0]没有使用
    private int N = 0; // 优先队列中元素的个数
    private Comparator<Key> comparator;  // optional comparator

    // 创建一个优先队列
    public MaxPQ() {
        this(1);//init queue length is 1
    }

    // 创建一个初始容量为max 的优先队列
    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Comparable[initCapacity + 1];
        N = 0;
    }

    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);//init queue length is 1
    }

    // 用 keys[] 创建一个优先队列
    public MaxPQ(Key[] keys) {
        N = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < N; i++) {
            pq[i + 1] = keys[i];
        }
        for (int k = N / 2; k >= 1; k--) {
            sink(k);
        }
//        assert isMaxHeap(); //todo
    }

    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        N = 0;
    }

    // 向优先队列中插入一个元素
    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    // 返回最大元素
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }

    // 删除并返回最大元素
    public Key delMax() {
        Key max = pq[1];//从根结点得到最大元素
        exchange(1, N);//将其和最后一个结点交换,
        pq[N] = null;//防止对象游离
        N--;//同时队列长度减1
        sink(1);//下沉，恢复堆的有序性
        return max;
    }

    // 返回队列是否为空
    private boolean isEmpty() {
        return N == 0;
    }

    // 返回优先队列中的元素个数
    public int size() {
        return N;
    }

    // helper function

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exchange(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++; // j最后的值表示两个子节点中最大的那个的索引。j<N是为了避免索引越界
            if (!less(k, j)) break;// 如果 k位置的值大于等于子节点中最大的那个，表示堆调整完成，退出循环。
            exchange(k, j); // 将父节点和子节点中最大的那个交换值。
            k = j;//此时j是上一轮三者（父节点，两个子节点）中最小的节点的位置，赋值给k继续下一轮循环。
        }
    }

    private void exchange(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean less(int i, int j) {
        if (null == comparator) {
            return pq[i].compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }
}
