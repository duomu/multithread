package com.yll.th.deadlock;

/**
 * @author：linlin.yang
 * @date：2018/4/9 17:42
 */
public class DeadLock implements Runnable {
    public int flag = 1;

    /**
     * 静态变量被类的所有对象共享
     */
    private static Object o1 = new Object();
    private static Object o2 = new Object();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+", flag=" + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName()+", flag=" + flag);
                }
            }
        }

        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName()+", flag=" + flag);
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock thread1 = new DeadLock();
        DeadLock thread2 = new DeadLock();
        thread1.flag = 1;
        thread2.flag = 1;
        new Thread(thread1).start();
        new Thread(thread2).start();
    }
}
