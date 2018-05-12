package com.yll.th.impl.thread;

/**
 * 继承Thread类实现多线程
 * @author：linlin.yang
 * @date：2018/4/9 11:15
 */
public class MyThread extends Thread {
    private int count;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        
        System.out.println(Thread.currentThread().getName() + " run...count=" + count);
    }

    public static void main(String[] args) {
        //无共享数据，线程安全
        for (int i = 0; i < 10; i++) {
            MyThread thread = new MyThread();
            thread.start();
        }
    }
}
