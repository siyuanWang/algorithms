package com.wsy.learn.jvm.collection.queue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockQueue<E> {
    private LinkedList<E> queue = new LinkedList<>();

    private Object lock = new Object();

    private ReentrantLock reentrantLock = new ReentrantLock();
    Condition con = reentrantLock.newCondition();

    void offer(E e) {
        queue.offer(e);
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    E take() throws InterruptedException {
        if (queue.size() == 0) {
            synchronized (lock) {
                lock.wait();
            }
        }
        return queue.poll();
    }

    void offer1(E e) throws InterruptedException {

        try {
            reentrantLock.lockInterruptibly();
            queue.offer(e);
            con.signalAll();

        } finally {
            reentrantLock.unlock();
        }
    }

    E take1() throws InterruptedException {
        if (queue.size() == 0) {
            try {
                reentrantLock.lockInterruptibly();
                con.await();

            } finally {
                reentrantLock.unlock();
            }
        }
        return queue.poll();
    }
}
