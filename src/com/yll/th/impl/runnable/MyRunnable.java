package com.yll.th.impl.runnable;

/**
 * 实现Runnable接口实现多线程
 * @author：linlin.yang
 * @date：2018/4/9 11:17
 */
public class MyRunnable implements Runnable {
    private int count;

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            count++;
        }

        System.out.println(Thread.currentThread().getName() + " run...count=" + count);
    }

    public static void main(String[] args) {
//        safeThreadCall();
        unSafeThreadCall();
    }

    /**
     * 无共享数据，线程安全
     */
    private static void safeThreadCall() {
        for (int i=0;i<10;i++){
            MyRunnable runnable = new MyRunnable();
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

    /**
     * 有共享数据，线程不安全
     */
    private static void unSafeThreadCall() {
        MyRunnable runnable = new MyRunnable();
        for (int i=0;i<10;i++){
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
