# API
## Java 接口

| 分类 | 接口                                           | 方法                                       |
| ---- | ---------------------------------------------- | ------------------------------------------ |
| 比较 | java.lang.Comparable<br />java.util.Comparator | compareTo()<br />compare()                 |
| 迭代 | java.lang.Iterable<br />java.util.Iterator     | iterator() <br />hasNext() next() remove() |


## Java 数据类型

| Java 数据类型  | 描述               |
| -------------- | ------------------ |
| Integer        | int 封装           |
| Double         | double 封装        |
| String         | 索引的 char 值序列 |
| StringBuilder  | 字符串构造类       |
| java.awt.Color | 颜色               |
| java.awt.Font  | 字体               |
| java.net.URL   | URL                |
| java.io.File   | 文件               |

## 自定义I/O类型

| 类型 | 描述   |
| ---- | ------ |
| In   | 输入流 |
| Out  | 输出流 |
| Draw | 绘图类 |

## 用于用例的面向数据的数据类型

| 类型        | 描述       |
| ----------- | ---------- |
| Point2D     | 平面上的点 |
| Internal1D  | 一维间隔   |
| Internal2D  | 二维间隔   |
| Date        | 日期       |
| Transaction | 交易       |

## 用于算法分析的数据类型

| 类型              | 描述         |
| ----------------- | ------------ |
| Counter           | 计数器       |
| Accumulator       | 累加器       |
| VisualAccumulator | 可视的累加器 |
| Stopwatch         | 计时器       |

## 集合类数据类型

| 类型                  | 描述                 |
| --------------------- | -------------------- |
| Stack                 | 栈，FILO             |
| Queue                 | 队列，FIFO           |
| Bag                   | 背包                 |
| MinPQ,MaxPQ           | 优先队列             |
| IndexMinPQ,IndexMaxPQ | 索引优先队列         |
| ST                    | 符号表               |
| SET                   | 集合                 |
| StringST              | 符号表（字符串为键） |

## 面向数据的图数据类型

| 类型                | 描述             |
| ------------------- | ---------------- |
| Graph               | 无向图           |
| Digraph             | 有向图           |
| Edge                | 边（加权）       |
| EdgeWeightedGraph   | 无向图（加权）   |
| DirectedEdge        | 边（有向，加权） |
| EdgeWeightedDigraph | 图（有向，加权） |

## 面向操作的图数据类型

| 类型              | 描述                         |
| ----------------- | ---------------------------- |
| UF                | 动态连通性                   |
| DepthFirstPaths   | 深度优先路径                 |
| CC                | 连通分量                     |
| BreadthFirstPaths | 广度优先路径                 |
| DirectedDFS       | 有向图路径的深度优先搜索     |
| DirectedBFS       | 有向图路径的广度优先搜索     |
| TransitiveClosure | 所有路径                     |
| Topological       | 拓扑排序                     |
| DepthFirstOrder   | 深度优先搜索顶点被访问的顺序 |
| DirectedCycle     | 环的搜索                     |
| SCC               | 强连通分量                   |
| MST               | 最小生成树                   |
| SP                | 最短路径                     |

## 数据结构举例

| 数据结构             | 抽象数据类型           | 数据表示           |
| -------------------- | ---------------------- | ------------------ |
| 父链接树             | UnionFind              | 整形数组           |
| 二分查找树           | BST                    | 含有两个链接的结点 |
| 字符串               | String                 | 数组、偏移量和长度 |
| 二叉堆               | PQ                     | 对象数组           |
| 散列表（拉链法）     | SeparateChainingHashST | 链表数组           |
| 散列表（线性探测法） | LinearProbingHashST    | 两个对象数组       |
| 图的邻接链表         | Graph                  | Bag对象的数组      |
| 单词查找树           | TrieST                 | 含有链接数组的结点 |
| 三向单词查找树       | TST                    | 含有三个链接的结点 |

