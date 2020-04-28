# 算法分析

## sum运行时间的总结

| 算法         | 运行时间增长数量级 |
| ------------ | ------------------ |
| TwoSum       | $N ^ 2$            |
| TwoSumFast   | $NlogN$            |
| ThreeSum     | $N^3$              |
| ThreeSumFast | $N^2logN$          |

## 增长数量级的常见假设的总结

| 描述         | 增长数量级 | 说明        | 举例               |
| ------------ | ---------- | ----------- | ------------------ |
| 常数级别     | 1          | 普通语句    | 赋值a=1;           |
| 对数级别     | logN       | 二分策略    | 二分查找           |
| 线性级别     | N          | 一重for循坏 | 找出最大元素       |
| 线性对数级别 | NlogN      | 分治        | 归并排序，快速排序 |
| 平方级别     | $N^2$      | 二重for循坏 | 检查所有元素对     |
| 立方级别     | $N^3$      | 三重for循坏 | 检查所有三元组     |
| 指数级别     | $2^N$      | 穷举查找    | 检查所有子集       |



## 算法分析中的常见函数

| 描述              | 记号 | 定义 |
| ----------------- | ---- | ---- |
| 向下取整（floor） | $\lfloor x \rfloor$ | 不大于x的最大整数 |
| 向上取整（ceil）  | $\lceil x \rceil$ | 不小于x的最小整数 |
| 自然对数 | lnN | $log_eN(e^x=N)$ |
| 以 2 为底的对数 | lgN | $log_2N(2^x=N)$ |
| 以2 为底的整型对数 | $\lfloor lgN \rfloor$ | 不大于lgN 的最大整数。(N 的二进制表示的位数) － 1 |
| 调和级数 | $H_N$ | 1+1/2+1/3+1/4+...+1/N |
| 阶乘 | N！ | 1×2×3×4×...×N |



## 算法分析中的常见近似函数

| 描述         | 近似函数                                 |
| ------------ | ---------------------------------------- |
| 调和级数求和 | $H_N=1+1/2+1/3+1/4+…+1/N$ ~ $ lnN$       |
| 等差数列求和 | $1+2+3+4+…+N$ ～ $N^2/2$                 |
| 等比数列求和 | $1+2+4+8+…+N=2N-1$ ～ 2N，其中$N=2^n$    |
| 斯特灵公式   | $lgN!=lg1+lg2+lg3+lg4+…+lgN ～ NlgN$     |
| 二项式系数   | $\binom N k$～ $N^k/k!$，其中 k 为小常数 |
| 指数函数     | $(1-1/x)^x ～ 1/e$                       |
