package com.yll.th.produce.lock;

import com.yll.th.produce.IResource;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 使用Lock实现缓冲区
 * @author：linlin.yang
 * @date：2018/4/9 18:48
 */
public class Resource implements IResource{
    /**
     * 当前资源数目
     */
    private int num;
    /**
     * 最多可存放的资源数目
     */
    private final int size = 10;

    private Lock lock;

    private Condition producerCondition;

    private Condition consumerCondition;

    public Resource(Lock lock, Condition producerCondition, Condition consumerCondition) {
        this.lock = lock;
        this.producerCondition = producerCondition;
        this.consumerCondition = consumerCondition;
    }

    /**
     * 生产资源
     */
    @Override
    public void put() {
        lock.lock();
        try {
            if (num < size) {//缓冲区没满
                num++;
                System.out.println(Thread.currentThread().getName() + "生产一个资源，当前资源数=" + num);
                consumerCondition.signalAll();//唤醒等待的消费者
            } else {//缓冲区满了
                try {
                    producerCondition.await();//让生成者线程等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();//生产者释放锁
        }
    }

    /**
     * 消费资源
     */
    @Override
    public void take() {
        lock.lock();
        try {
            if (num > 0) {//缓冲区不空
                num--;
                System.out.println(Thread.currentThread().getName() + "消费一个资源，当前资源数=" + num);
                producerCondition.signalAll();//唤醒生产者线程
            } else {//缓冲区空了
                try {
                    consumerCondition.await();//让消费者线程等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();//消费者释放锁
        }
    }
}
