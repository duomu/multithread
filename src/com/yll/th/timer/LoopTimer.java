package com.yll.th.timer;

import java.util.Timer;

/**
 * 定时执行任务
 * @author：linlin.yang
 * @date：2018/4/10 14:20
 */
public class LoopTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        //每隔1000ms执行一次任务
        timer.schedule(new MyTimerTask(), 0, 1000);
    }
}
