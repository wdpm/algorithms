package io.github.wdpm.algs.search;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import io.github.wdpm.algs.basic.Queue;
import io.github.wdpm.algs.util.Pair;

import java.util.NoSuchElementException;

/**
 * 左倾红黑树(LLRB)
 *
 * @author evan
 * @date 2020/4/27
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;
    private              Node    root;

    private class Node {
        private Key     key;
        private Value   value;
        private Node    left;
        private Node    right;
        private int     size;
        private boolean color;

        public Node(Key key, Value value, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }
    }

    // init public RBTree
    public RedBlackBST() {
    }

    /***************************************************************************
     *  Node helper methods.
     ***************************************************************************/
    // is node x red; false if x is null.
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

    /**
     * Is this symbol table empty?
     *
     * @return {@code true}if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /***************************************************************************
     *  Red-black tree insertion.
     ***************************************************************************/

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        // value = null时不删除
        // if (value == null) {
        //     delete(key);
        //     return;
        // }

        root = put(root, key, value);
        root.color = BLACK;
    }

    // insert the key-value pair in the subtree rooted at h
    private Node put(Node h, Key key, Value value) {
        if (h == null) return new Node(key, value, 1, RED);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, value);
        else if (cmp > 0) h.right = put(h.right, key, value);
        else h.value = value;

        // todo refactor below to balance()
        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        // update subtree nodes number
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    /***************************************************************************
     *  Standard BST search.
     ***************************************************************************/

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }

        //抵达底部空链接，也就是找不到，返回null
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    /***************************************************************************
     *  Red-black tree deletion.
     ***************************************************************************/
    // warning: delete operation is very difficult to understand
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;//将root染红

        root = deleteMin(root);

        // reset root to black
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        // h.left is black and h.left.left is black
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }

    /**
     * Removes the largest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the maximum key rooted at h
    private Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // refer comment: https://www.zhihu.com/question/340879955
    // delete the key-value pair with the given key rooted at h
    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))//此时：h为红 h.left黑 h.left.left黑
                h = moveRedLeft(h);//moveRedLeft把红色推到左子树以便调用下面的delete，#refer moveRedLeft
            h.left = delete(h.left, key);//然后从h的左子树删除
        } else {//key > h.key
            if (isRed(h.left))
                h = rotateRight(h);//如果h.left为红则右旋，使h.right变红，方便从右子树里删除

            // 此时h为红或者h.right为红。
            if (key.compareTo(h.key) == 0 && (h.right == null))
                // h.right == null所以上面的右旋没有发生
                // 右子树null，左子树不为红，只能也是null，不然黑高不平衡
                // return null;
                return null;

            // 执行到这里，说明要删的结点在h的右子树
            // 如果要删的是h，与h右子树的min对调key-value后删除右子树的min
            if (!isRed(h.right) && !isRed(h.right.left))
                // h.right黑，h.right.left黑，h必定为红
                // 与moveRedLeft相似，把红色的h推到右子树以便接下来在里面delete
                h = moveRedRight(h);

            // 常规的BST删除逻辑，目标是删除红色的h，此时h一定为红
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);//注意，这里的x是h的后继结点，也就是h.right中>h的最小值是x。
                h.key = x.key;//使用x结点替代h结点，这也是后继结点的作用
                h.value = x.value;

                // 此时有两个x结点，即重复，需要删除原来在h.right树中的x结点

                // 从h.right中删除x结点
                h.right = deleteMin(h.right);
                // 等同于
                // h.right = delete(h.right, x.key);
            } else {
                h.right = delete(h.right, key);
            }
        }
        // 修复moveRedLeft，moveRedRight副作用：h.right可能会变红
        return balance(h);//恢复平衡
    }

    /***************************************************************************
     *  Red-black tree helper functions.
     ***************************************************************************/

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        assert (h != null) && (h.left != null) && (h.right != null);
        assert (!isRed(h) && isRed(h.left) && isRed(h.right))
                || (isRed(h) && !isRed(h.left) && !isRed(h.right));

        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private Node rotateRight(Node h) {
        assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;

        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // make a right-leaning link to the left-leaning link
    private Node rotateLeft(Node h) {
        assert (h != null) && isRed(h.right);

        Node x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = RED;

        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // 注意：这段代码实现非常难理解，作者也没在书中说明清楚。决定抛弃
    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);// h.left变红
        if (isRed(h.right.left)) {
            // 如果右孩子出现双红矛盾?，则通过rotate+flip来解决
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        //最终h.left或者h.left.left为红。h.right可能为红，可以事后调用balance消除。
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        assert (h != null);

        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    /***************************************************************************
     *  Utility functions.
     ***************************************************************************/

    /**
     * Returns the height of the BST (for debugging).
     *
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public Iterable<Pair> levelOrderWithColor() {
        Queue<Pair> keys  = new Queue<Pair>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(new Pair<>(x.key.toString(), x.color));
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    /***************************************************************************
     *  Ordered symbol table methods.
     ***************************************************************************/
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) throw new NoSuchElementException("argument to floor() is too small");
        else return x.key;
    }

    // the largest key in the subtree rooted at x less than or equal to the given key
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);

        Node t = floor(x.right, key);//此时从x的右边找
        if (t != null) return t;//如果能找到，返回t
        else return x;//否则返回的是x
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException("argument to ceiling() is too large");
        else return x.key;
    }

    // the smallest key in the subtree rooted at x greater than or equal to the given key
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);

        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int rank) {
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
        if (leftSize > rank) return select(x.left, rank);
        else if (leftSize < rank) return select(x.right, rank - leftSize - 1);// rightSize+leftSize+1=rank
        else return x.key;
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    // number of keys less than key in the subtree rooted at x
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    /***************************************************************************
     *  Range count and range search.
     ***************************************************************************/

    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<Key>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

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

    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    /***************************************************************************
     *  Check integrity of red-black tree data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST()) StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        if (!is23()) StdOut.println("Not a 2-3 tree");
        if (!isBalanced()) StdOut.println("Not balanced");
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
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
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }

    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (x != root && isRed(x) && isRed(x.left))
            return false;
        return is23(x.left) && is23(x.right);
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
        int  black = 0;     // number of black links on path from root to min
        Node x     = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    public static void main(String[] args) {
        RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        // 层级顺序
        for (Pair pair : st.levelOrderWithColor())
            StdOut.println(pair.getKey() + " " + pair.getValue() + " " + st.get(pair.getKey().toString()));

        StdOut.println();

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));

        StdOut.println();
    }
}
