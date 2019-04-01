package com.wsy.learn.jvm.collection.queue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Girl {

    private LinkedList<Boy> loveQueue = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition house = lock.newCondition();
    private Condition car = lock.newCondition();
    private Condition single = lock.newCondition();

    void boyComes(Boy boy) throws InterruptedException {
        try {
            lock.lockInterruptibly();
            loveQueue.offer(boy);
            System.out.println("comes boy." + boy.toString());
            if(boy.getHouse() == 1 && boy.getCar() == 1 && boy.getSingle() == 1) {
                house.signalAll();
                car.signalAll();
                single.signalAll();
                System.out.println("boy pass.");
            }
        } finally {
            lock.unlock();
        }

    }

    void waitLove() throws InterruptedException {
        try {
            System.out.println("where girl's love?");
            lock.lockInterruptibly();
            while (!Thread.currentThread().isInterrupted()) {
                if(loveQueue.size() == 0) {
                    continueWait();
                }
                System.out.println("good boy!!" + loveQueue.poll().toString());
            }
        } finally {
            lock.unlock();
        }

    }

    void continueWait() throws InterruptedException {
        System.out.println("girl waiting....");
        house.await();
        car.await();
        single.await();

    }
}
