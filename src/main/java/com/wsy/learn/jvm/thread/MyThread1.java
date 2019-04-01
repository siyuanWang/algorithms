package com.wsy.learn.jvm.thread;

public class MyThread1 extends Thread {
    private Object lock;
    private String name;

    public MyThread1(Object lock, String name) {
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(name + "=" + i++);
        }
        System.out.println(name + ":notified...");
    }
}
