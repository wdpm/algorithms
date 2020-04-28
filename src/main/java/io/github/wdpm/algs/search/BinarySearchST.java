package io.github.wdpm.algs.search;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import io.github.wdpm.algs.basic.Queue;

import java.util.NoSuchElementException;

/**
 * BinarySearchST有序查找表
 *
 * <p>实现：使用两个平行数组，一个保存键，一个保存值。需要扩容，因为使用的是数组表示。
 *
 * @Created evan
 * @Date 2020/3/21
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private static final int     INIT_CAPACITY = 2;
    private              Key[]   keys;
    private              Value[] vals;
    private              int     n             = 0;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return n;
    }

    /**
     * @param lo inclusive endpoint
     * @param hi inclusive endpoint
     * @return
     */
    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    // resize the underlying arrays
    private void resize(int capacity) {
        assert capacity >= n;
        Key[]   tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        if (isEmpty()) return null;

        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return vals[i];

        return null;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 二分查找
     *
     * <p>
     * 如果表中存在该键，rank()应该返回该键的位置，也就是表中小于它的键的数量
     * <p>
     * 如果表中不存在该键，rank()还是应该返回表中小于它的键的数量
     *
     * @param key
     * @return
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");

        int lo = 0;
        int hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        // lo 的初始值为0，且永远不会变小
        // 假设key是一个很小的数，比keys中所有元素都要小，那么应该返回0，此时lo也是0
        // 假设key是一个很大的数，比keys中所有元素都要大，那么应该返回N，此时lo也是N。注意不是N-1
        // 所以这里返回的是lo
        return lo;
    }

    // 查找键，找到则更新值，否则创建新的元素
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        // update
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        // do resize before inserting new key-value pair
        if (n == keys.length) {
            resize(2 * keys.length);
        }

        // insert
        // 在插入新元素前将所有较大的键向后移动一格
        // j range: [n...i+1]
        // j-1 range: [n-1...i]
        // keys[n-1...i] -> keys[n...i+1]
        // vals[n-1...i] -> vals[n...i+1]
        // 腾出keys[i]和vals[i]的空位
        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0];
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[n - 1];
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(min());
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow error");
        delete(max());
    }

    public Key select(int k) {
        // make sure 0 <= k <= n-1
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        int i = rank(key);
        //表示入参key比keys所有元素都大
        if (i == n) return null;
        else return keys[i];
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) return keys[i];
        // 表示入参key比keys所有元素都小
        if (i == 0) return null;
            // 否则返回i位置的左边一个元素
        else return keys[i - 1];
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (isEmpty()) return;

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }

        // j range: [i..n-2]
        // j+1 range: [i+1...n-1]
        // keys[i+1...n-1] -> keys[i...n-2]
        // vals[i+1...n-1] -> vals[i...n-2]
        // keys[n-1], vals[n-1]位置，即末尾的元素位置现在可以回收
        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }

        // size -1
        n--;

        // Now n is the position to GC
        keys[n] = null;  // to avoid loitering
        vals[n] = null;

        // resize if 1/4 full
        if (n > 0 && n == keys.length / 4) resize(keys.length / 2);

        assert check();
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++)
            queue.enqueue(keys[i]);

        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue;
    }

    /***************************************************************************
     *  Check internal invariants.
     ***************************************************************************/

    private boolean check() {
        return isSorted() && rankCheck();
    }

    // are the items in the array in ascending order?
    private boolean isSorted() {
        for (int i = 1; i < size(); i++) //i∈[1...n-1]
            if (keys[i].compareTo(keys[i - 1]) < 0) return false;
        return true;
    }

    // check that rank(select(i)) = i and key = select(rank(key))
    private boolean rankCheck() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
        return true;
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        //S E A R C H E X A M P L E
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);

            for (String s : st.keys())
                StdOut.print("["+s + " " + st.get(s)+"] ");

            System.out.println();
        }

    }
}
