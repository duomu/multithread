package com.yll.th.produce.semaphore;

import com.yll.th.produce.IResource;

import java.util.concurrent.Semaphore;

/**
 * 使用信号量实现有界缓冲区
 * @author：linlin.yang
 * @date：2018/5/12 15:39
 */
public class Resource implements IResource {
    /**
     * 当前资源数目
     */
    private int num;
    /**
     * 最多可存放的资源数目
     */
    private final int size = 10;

    private final Semaphore semaphore;

    public Resource(int bound) {
        semaphore = new Semaphore(bound);
    }

    @Override
    public void put() {

    }

    @Override
    public void take() {

    }
}
