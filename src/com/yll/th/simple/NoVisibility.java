package com.yll.th.simple;

/**
 * @author：linlin.yang
 * @date：2018/4/4 21:35
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {//若没有读到number的值，则当前线程转换成就绪状态
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
