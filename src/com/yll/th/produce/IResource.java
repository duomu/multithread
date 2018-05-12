package com.yll.th.produce;

/**
 * @author：linlin.yang
 * @date：2018/4/9 19:43
 */
public interface IResource {
    /**
     * 生产资源
     */
    void put();

    /**
     * 消费资源
     */
    void take();
}
