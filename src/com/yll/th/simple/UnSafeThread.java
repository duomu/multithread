package com.yll.th.simple;

/**
 * @author：linlin.yang
 * @date：2018/4/4 11:40
 */
public class UnSafeThread extends Thread {
    private Counter counter;

    public UnSafeThread(Counter counter) {
//        this.counter = new Counter();
        this.counter = counter;
    }

    @Override
    public void run() {
        synchronized (counter) {
            for (int i=0;i<10000;i++) {
                this.counter.value++;
            }
            System.out.println("UnSafeThread-" + Thread.currentThread().getName() + " count：" + counter.value);
        }

    }

    static class Counter {
        int value;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        UnSafeThread thread1 = new UnSafeThread(counter);
        UnSafeThread thread2 = new UnSafeThread(counter);
        UnSafeThread thread3 = new UnSafeThread(counter);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
