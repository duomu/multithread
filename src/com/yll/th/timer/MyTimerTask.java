package com.yll.th.timer;

import java.util.TimerTask;

/**
 * @author：linlin.yang
 * @date：2018/4/10 14:18
 */
public class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("当前时间：" + this.scheduledExecutionTime());
    }
}
