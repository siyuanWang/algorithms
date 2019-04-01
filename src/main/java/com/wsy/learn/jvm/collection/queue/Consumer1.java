package com.wsy.learn.jvm.collection.queue;

public class Consumer1 {
    void cusume() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("自定义消费:" + Productor1.blockQueue.take1());
        }
    }
}
