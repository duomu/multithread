package com.yll.th.timer;

import java.util.Timer;

/**
 * 延迟执行任务
 * @author：linlin.yang
 * @date：2018/4/10 14:15
 */
public class OneTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        //延迟1000ms执行程序，1s之后执行操作
        timer.schedule(new MyTimerTask(), 1000);
        timer.schedule(new MyTimerTask(), 2000);
    }
}
