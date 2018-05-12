package com.yll.th.impl.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable接口，通过FutureTask包装器来创建多线程
 * @author：linlin.yang
 * @date：2018/4/9 11:21
 */
public class MyCallable implements Callable<String> {
    private int count;

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 1000; i++) {
            count++;
        }

        System.out.println(Thread.currentThread().getName() + " run...count=" + count);

        return "ok";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //无共享数据，线程安全
        for (int i = 0; i < 10; i++) {
            MyCallable callable = new MyCallable();
            FutureTask<String> futureTask = new FutureTask<String>(callable);
            Thread thread = new Thread(futureTask);
            thread.start();

            //获取线程返回值
            String result = futureTask.get();
            System.out.println("result：" + result);
        }
    }
}

