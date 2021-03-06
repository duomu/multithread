package com.yll.th.produce.bq;

import com.yll.th.produce.Consumer;
import com.yll.th.produce.Producer;
import com.yll.th.produce.lock.Resource;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BlockingQueue
 * @author：linlin.yang
 * @date：2018/4/9 18:58
 */
public class Test {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition producerCondition = lock.newCondition();
        Condition consumerCondition = lock.newCondition();
        com.yll.th.produce.lock.Resource resource = new Resource(lock, producerCondition, consumerCondition);
        for (int i = 0; i < 10; i++) {
            Thread producer = new Thread(new Producer(resource));
            producer.start();
        }

        for (int i=0;i<20;i++) {
            Thread consumer = new Thread(new Consumer(resource));
            consumer.start();
        }
    }
}
