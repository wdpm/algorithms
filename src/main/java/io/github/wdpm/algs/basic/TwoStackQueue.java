package io.github.wdpm.algs.basic;

/**
 * 使用两个栈实现队列。
 *
 * <p>
 * 一个为写入栈，一个为输入栈。输入栈为空时，将写入栈pop元素过来，再出栈。
 * </p>
 *
 * @author evan
 * @date 2020/5/6
 */
public class TwoStackQueue<T> {
    private Stack<T> inStack  = new Stack();
    private Stack<T> outStack = new Stack();

    public void enqueue(T value) {
        inStack.push(value);
    }

    public T dequeue() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }

        return outStack.pop();
    }

    public int size() {
        return inStack.size() + outStack.size();
    }

    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public static void main(String[] args) {
        TwoStackQueue<Integer> queue = new TwoStackQueue<>();
        queue.enqueue(4);
        queue.enqueue(7);
        queue.enqueue(5);
        queue.enqueue(2);
        queue.enqueue(8);

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }

    }
}

