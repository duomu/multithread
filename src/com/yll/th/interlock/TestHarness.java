package com.yll.th.interlock;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁应用1：统计多个线程并发执行消耗的时间
 * @author：linlin.yang
 * @date：2018/4/10 10:22
 */
public class TestHarness {
    public long timeTasks(int nThreads) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);//起始门，初值为1，每个工作线程都在起始门上等待，直到Main线程就绪才开始执行
        final CountDownLatch endGate = new CountDownLatch(nThreads);//结束门，初值为工作线程数，Main线程在结束门上等待，直到所有工作线程执行完毕才继续后面的操作

        for (int i = 0; i < nThreads; i++) {
            Thread thread=new Thread() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            Thread.sleep(1000);
                        }finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };

            thread.start();
        }

        //Main线程
        long start = System.nanoTime();
        startGate.countDown();//工作线程开始执行
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }


    public static void main(String[] args) throws InterruptedException {
        TestHarness test = new TestHarness();
        long spendTime = test.timeTasks(2);
        System.out.println("spendTime：" + spendTime + "ns");
    }
}

