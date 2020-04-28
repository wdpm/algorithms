package io.github.wdpm.algs.sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * IndexMinPQ用例：使用优先队列的多向归并。将多个有序的输入流归并成一个有序的输出流。
 *
 * @Created evan
 * @Date 2020/3/18
 */
public class Multiway {
    private static void merge(In[] streams) {
        int N = streams.length;
        IndexMinPQ<String> pq = new IndexMinPQ<>(N);
        // N个流，每个流都取下一个元素放入队列，位置为i。
        for (int i = 0; i < N; i++) {
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }

        // 弹出当前队列中最小的元素，然后在该位置继续补充元素，如此反复，直到队列为空。
        while (!pq.isEmpty()) {
            System.out.print(pq.minKey()+" ");
            int i = pq.delMin();
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }
    }

    /**
     * Usage example: java Multiway m1.txt m2.txt m3.txt.
     * <br>
     * Dependent File path: src/main/resources/
     * <br>
     * Result : A A B B B C D E F F G H I I J N P Q Q Z
     * @param args
     */
    public static void main(String[] args) {
        int N = args.length;
        In[] streams = new In[N];
        for (int i = 0; i < N; i++) {
            streams[i] = new In(args[i]);
        }
        merge(streams);
    }
}
