package io.github.wdpm.algs.basic;

/**
 *
 * quick-find
 *
 * 时间复杂度分析：
 * 假设我们使用quick-find 算法来解决动态连通性问题并且最后只得到了一个连通分量，
 * 那么这至少需要调用N-1 次union()，每次union时间复杂度为N，一共 N*(N-1)。因此 quick-find 算法是平方级别的。
 *
 * @Created evan
 * @Date 2020/3/14
 */
public class QuickFindUF {
    private int[] id;//分量id，以触点作为索引
    private int count;// 分量的数量

    // 以整数标识（0 到N-1）初始化N 个触点
    public QuickFindUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // 在 p 和 q 之间添加一条连接
    void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
        count--;
    }

    // p（0 到 N-1）所在的分量的标识符
    // 对于处在同一个连通分量中的触点均返回相同的整数值
    private int find(int p) {
        return id[p];
    }

    boolean connected(int p, int q) {
        return find(q) == find(q);
    }

    // 连通分量的数量
    int count() {
        return count;
    }
}
