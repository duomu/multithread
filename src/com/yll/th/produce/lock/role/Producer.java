package com.yll.th.produce.lock.role;

import com.yll.th.produce.lock.Resource;

/**
 * @author：linlin.yang
 * @date：2018/4/9 18:12
 */
public class Producer implements Runnable{
    private Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {//不断生产资源
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            resource.put();
        }
    }
}
