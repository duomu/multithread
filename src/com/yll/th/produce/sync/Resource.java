package com.yll.th.produce.sync;

import com.yll.th.produce.IResource;

/**
 * 使用内置锁Synchronized实现缓冲区
 * @author：linlin.yang
 * @date：2018/4/9 18:24
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

    /**
     * 生产资源
     */
    @Override
    public synchronized void put() {
        if (num < size) {//缓冲区未满
            num++;
            System.out.println(Thread.currentThread().getName() + "生产一个资源，当前资源数=" + num);
            notifyAll();//唤醒消费者线程
        } else {//缓存区满了
            try {
                wait();//生产者进入等待状态，并释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费资源
     */
    @Override
    public synchronized void take() {
        if (num > 0) {//缓冲区不空
            num--;
            System.out.println(Thread.currentThread().getName() + "消费一个资源，当前资源数=" + num);
            notifyAll();//唤醒生产者线程
        } else {//缓冲区空了
            try {
                wait();//消费者线程进入等待，并释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
