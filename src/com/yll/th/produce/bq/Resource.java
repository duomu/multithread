package com.yll.th.produce.bq;

import com.yll.th.produce.IResource;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 使用BlockingQueue实现缓冲区
 * @author：linlin.yang
 * @date：2018/4/9 19:35
 */
public class Resource implements IResource{
    private BlockingQueue resourceQueue = new LinkedBlockingQueue(10);

    /**
     * 生产资源
     */
    @Override
    public void put() {
        try {
            resourceQueue.put(1);
            System.out.println(Thread.currentThread().getName() + "生成一个资源，当前资源数=" + resourceQueue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费资源
     */
    @Override
    public void take() {
        try {
            resourceQueue.take();
            System.out.println(Thread.currentThread().getName() + "消费一个资源，当前资源数=" + resourceQueue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
