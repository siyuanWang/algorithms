package com.wsy.learn.jvm.collection.queue;

/**
 * 基于阻塞队列消费者
 */
public class Consumer {
    void cusume() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("消费:" + Productor.blockQueue.take());
        }
    }
}
