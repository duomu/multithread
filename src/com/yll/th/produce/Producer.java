package com.yll.th.produce;

/**
 * @author：linlin.yang
 * @date：2018/4/9 18:12
 */
public class Producer implements Runnable{
    private IResource resource;

    public Producer(IResource resource) {
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
