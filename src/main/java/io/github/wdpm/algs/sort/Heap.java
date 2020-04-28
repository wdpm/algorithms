package io.github.wdpm.algs.sort;

public class Heap {

    private Heap() {
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param pq the array to be sorted
     */
    public static void sort(Comparable[] pq) {
        int n = pq.length;

        // for 循环构造堆
        // heapify phase
        for (int k = n / 2; k >= 1; k--) {
            sink(pq, k, n);
        }

        // while 循环将最大的元素a[1] 和a[N] 交换并修复堆，如此重复直到堆变空
        // sortdown phase
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }

    // 将 exch() 和less() 的实现中的索引减一即可得到和其他排序算法一致的实现（ 将a[0] 至a[N-1] 排序）
    /***************************************************************************
     * Helper functions for comparisons and swaps.
     * Indices are "off-by-one" to support 1-based indexing.
     ***************************************************************************/
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    private static void show(Comparable[] a) {
        for (Comparable comparable : a) {
            System.out.print(comparable + " ");
        }
    }

    public static void main(String[] args) {
        String[] a = new String[]{"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        Heap.sort(a);
        show(a);
    }
}

