package com.yll.th.interlock;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁应用2：控制线程执行的时机，让主线程Main在子线程运行完毕之后再执行
 * @author：linlin.yang
 * @date：2018/4/10 10:32
 */
public class MyThread extends Thread{
    private CountDownLatch latch;

    public MyThread(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
            Thread.sleep(1000);
            System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);
        MyThread thread1 = new MyThread(latch);
        MyThread thread2 = new MyThread(latch);
        thread1.start();
        thread2.start();
        System.out.println("等待2个子线程执行完毕...");
        System.out.println("count=" + latch.getCount());
        latch.await();//使当前线程阻塞等待，直到latch的count被减为0，或其他线程发生中断
        System.out.println("2个子线程已经执行完毕");
        System.out.println("count=" + latch.getCount());
        System.out.println("继续执行主线程..." + Thread.currentThread().getName());
    }
}
