package com.yll.th.produce.sync.role;

import com.yll.th.produce.sync.Resource;

/**
 * @author：linlin.yang
 * @date：2018/4/9 18:36
 */
public class Consumer implements Runnable {
    private Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {//不断处理资源
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.take();
        }
    }
}
