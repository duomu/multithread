package com.yll.th.produce.sync;

import com.yll.th.produce.Consumer;
import com.yll.th.produce.IResource;
import com.yll.th.produce.Producer;

/**
 * synchronized、wait和notify
 * @author：linlin.yang
 * @date：2018/4/9 18:39
 */
public class Test {
    public static void main(String[] args) {
        IResource resource = new Resource();
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
