package com.wsy.learn.jvm.collection.queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 用阻塞队列实现生产-消费者模式
 */
public class Productor {
    static LinkedBlockingQueue<Integer> blockQueue = new LinkedBlockingQueue<>(10);

    void provide() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            blockQueue.offer(i);
            System.out.println("生产:" + i);
            Thread.sleep(100);
        }
    }
}
