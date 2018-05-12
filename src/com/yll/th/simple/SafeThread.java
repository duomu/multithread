package com.yll.th.simple;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：linlin.yang
 * @date：2018/4/4 20:21
 */
public class SafeThread extends Thread{
    private AtomicInteger counter;

    public SafeThread(AtomicInteger counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i=0;i<10000;i++) {
            this.counter.getAndIncrement();
        }

        System.out.println("SafeThread-" + Thread.currentThread().getName() + " count：" + counter.intValue());
    }

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);
        SafeThread thread1 = new SafeThread(counter);
        SafeThread thread2 = new SafeThread(counter);
        SafeThread thread3 = new SafeThread(counter);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
