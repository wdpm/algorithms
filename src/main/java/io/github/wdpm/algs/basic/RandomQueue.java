package io.github.wdpm.algs.basic;

import java.util.Iterator;

/**
 * TODO
 *
 * 提示：使用（能够动态调整大小的）数组表示
 * 数据。删除一个元素时，随机交换某个元素（索引在0 和N-1 之间）和末位元素（索引为
 * N-1）的位置，然后像ResizingArrayStack 一样删除并返回末位元素。编写一个用例，使用
 * RandomQueue<Card> 在桥牌中发牌（每人13 张）
 * @Created evan
 * @Date 2020/3/12
 */
public class RandomQueue<Item> implements Iterable<Item>{

    //创建一条空的随机队列
    RandomQueue(){

    }

    boolean isEmpty(){
        return true;
    }

    //添加一个元素
    void enqueue(Item item){

    }

    //删除并随机返回一个元素（取样且不放回）
    Item dequeue(){
       return null;
    }

    //随机返回一个元素但不删除它（取样且放回）
    Item sample(){
        return null;
    }

    @Override
    public Iterator<Item> iterator() {
        // 实现随机迭代器，随机返回队列中的所有元素
        return null;
    }
}
