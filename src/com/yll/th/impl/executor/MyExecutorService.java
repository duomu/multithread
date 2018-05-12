package com.yll.th.impl.executor;

import com.yll.th.impl.callable.MyCallable;
import com.yll.th.impl.runnable.MyRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用线程池ExecutorService、Callable、Future实现多线程，可获得返回结果
 * ExecutorService可执行Callable和Runnable任务，但是只有Callable可以返回结果
 * @author：linlin.yang
 * @date：2018/4/9 11:48
 */
public class MyExecutorService {
    public static void main(String[] args) throws Throwable {
        testFixedThreadPool();
    }

    public static void testFixedThreadPool() throws ExecutionException, InterruptedException {
        int taskSize = 10;
        //创建一个固定线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        //创建多个有返回值的任务
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        for (int i = 0; i < taskSize; i++) {
            //执行任务并获取Future对象
            MyCallable callable = new MyCallable();
            Future future = pool.submit(callable);
            //MyRunnable runnable = new MyRunnable();
            //Future future = pool.submit(runnable);
            futureList.add(future);
        }

        //关闭线程池
        pool.shutdown();

        for (Future f : futureList) {
            System.out.println(">>>" + f.get());
        }
    }

    /**
     * TODO 可缓存的线程池、可调度的线程池、单线程的线程池
     */
}
