package com.wsy.learn.jvm.collection.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 用阻塞队列实现生产-消费者模式
 */
public class Productor1 {
    static MyBlockQueue<Integer> blockQueue = new MyBlockQueue<>();

    void provide() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            blockQueue.offer1(i);
            System.out.println("自定义生产:" + i);
            Thread.sleep(100);
        }
    }
}
