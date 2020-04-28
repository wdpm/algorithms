package io.github.wdpm.algs.search;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import io.github.wdpm.algs.basic.Queue;

import java.util.NoSuchElementException;

/**
 * 二叉查找树。(unbalanced) binary search tree.
 *
 * <p>不需要扩容，因为使用二叉树表示数据。
 *
 * @author evan
 * @date 2020/4/24
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root; // 二叉查找树的根结点

    private class Node {
        private Key   key; // 键
        private Value val; // 值
        private Node  left;
        private Node  right;
        private int   size; // 以该结点为根的子树中的结点总数

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    // init empty BST
    public BST() {
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) { // 在以x为根结点的子树中查找并返回key所对应的值；
        // 如果找不到则返回null
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) { // 查找key，找到则更新它的值，否则为它创建一个新的结点
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        assert check();
    }

    private Node put(Node x, Key key, Value val) {
        // 如果key存在于以x为根结点的子树中则更新它的值；
        // 否则将以key和val为键值对的新结点插入到该子树中
        // 返回值永远是入参x节点
        if (x == null) return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }

        // 更新x结点中的计数器的值
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    /**
     * 递归寻找树中最左边的子节点，就是最小的子节点。
     *
     * @param x
     * @return
     */
    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) throw new NoSuchElementException("argument to floor() is too small");
        else return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);//key<根节点的key，继续从根节点的左子树找

        Node t = floor(x.right, key);//key>根节点的key，继续从根节点的右子树找
        if (t != null) return t;//如果能找到，返回该节点
        else return x;//否则，返回根节点
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException("argument to floor() is too large");
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;

        if (cmp < 0) {//key<根节点的key
            Node t = ceiling(x.left, key);//继续从根节点的左子树找
            if (t != null) return t;//如果能找到，返回该节点
            else return x;//否则，返回根节点
        }

        // cmp>0 , 代表key>根节点的key，继续从根节点的右子树找
        return ceiling(x.right, key);
    }

    public Key select(int rank) {
        // force 0<= rank <= size()-1
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    // Return key in BST rooted at x of given rank.
    // Precondition: rank is in legal range.
    private Key select(Node x, int rank) {
        if (x == null) return null;
        int leftSize = size(x.left);

        if (leftSize > rank) {//如果x左子树的节点数>rank,代表还需要继续从x的左子树找
            return select(x.left, rank);
        } else if (leftSize < rank) {//如果x左子树的节点数<rank，代表需要从右子树找
            return select(x.right, (rank - 1) - leftSize);// -1是因为当前x节点也占一个位置，于是为(rank - 1) - leftSize
        } else {//leftSize == rank
            return x.key;
        }
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    private int rank(Key key, Node x) { // 返回以x为根结点的子树中小于x.key的键的数量
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);

        if (cmp < 0) return rank(key, x.left);//递归左子树
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);//左子树节点数size(x.left)+根节点1+右边排名
        else return size(x.left);//直接返回左子树节点数
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
        assert check();
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);//更新x的左子树后，让x.left指向更新后的这个左子树
        x.size = size(x.left) + size(x.right) + 1;//更新节点计数器
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
        assert check();
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);//在左子树中递归
        else if (cmp > 0) x.right = delete(x.right, key);//在右子树中递归
        else {//找到删除的节点x了
            if (x.right == null) return x.left;//x右子树为空，那么x左子树补位
            if (x.left == null) return x.right;//x左子树为空，那么x右子树补位

            //重点：使用x的后继节点补位。后继节点就是x右子树中最小的节点

            Node t = x;//临时保存x的引用到t
            x = min(t.right);//现在x就是后继节点
            x.right = deleteMin(t.right);//后继节点的右子树=t节点的右子树更新后（删除后继节点）
            x.left = t.left;//后继节点的左子树=t节点的左子树
        }
        x.size = size(x.left) + size(x.right) + 1;//更新节点计数器
        return x;//递归调用后会修正被删除的结点的父结点的链接
    }

    /**
     * @return the height of the BST (a 1-node tree has height 0, a null tree has height -1)
     */
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /**
     * 层级顺序，从上往下，同一层从左往右
     * Returns the keys in the BST in level order (for debugging).
     *
     * @return the keys in the BST in level order traversal
     */
    public Iterable<Key> levelOrder() {
        Queue<Key>  keys  = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    /*************************************************************************
     *  Check integrity of BST data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST()) StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;//根节点x的计数检验
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);//根节点x的左子树、右子树的计数检验
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;//检验 i == rank(select(i))
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;// 检验key==(select(rank(key)))
        return true;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    /**
     * 将key位于[lo,hi]范围的元素放于queue
     *
     * @param x
     * @param queue
     * @param lo    inclusive
     * @param hi    inclusive
     */
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi); // lo < x.key, 而且x左子树 < x.key,于是递归x左子树
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);// lo <= x.key <= hi,那么x.key合适，入列
        if (cmphi > 0) keys(x.right, queue, lo, hi);// x.key < hi, 而且x.key< x右子树，于是递归x右子树
    }

    /**
     * Unit tests the {@code BST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // 层级顺序
        for (String s : st.levelOrder())
            StdOut.println(s + " " + st.get(s));

        StdOut.println();

        // 从小到大的顺序
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }

}
