package io.github.wdpm.algs.basic;

/**
 * quick-union
 *
 * 缺点：union() 中随意将一棵树连接到另一棵树，可能会将较大的树连接到较小的树上。算法在这点上可以继续优化。
 *
 * @Created evan
 * @Date 2020/3/14
 */
public class QuickUnionUF {
    private int[] id;//分量id，以触点作为索引
    private int count;// 分量的数量

    // 以整数标识（0 到N-1）初始化N 个触点
    public QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // 在 p 和 q 之间添加一条连接
    void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        //如果根节点相等，不需要做任何事情
        if (pRoot == qRoot) return;

        id[pRoot] = qRoot;//根节点 qRoot 成为根节点 pRoot 的父节点。归并了两棵树
        count--;
    }

    // p（0 到 N-1）所在的分量的标识符
    // 对于处在同一个连通分量中的触点均返回相同的整数值
    private int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    boolean connected(int p, int q) {
        return find(q) == find(q);
    }

    // 连通分量的数量
    int count() {
        return count;
    }
}
