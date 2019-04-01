package com.wsy.learn.jvm.thread;

public class MyThread extends Thread {
    private Object lock;
    private String name;

    public MyThread(Object lock, String name) {
        this.lock = lock;
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                System.out.println(name + " get lock,interrupt =" + Thread.currentThread().isInterrupted());

                lock.wait();

                //Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(name + " is interrupt. notify all interrupt =" + Thread.currentThread().isInterrupted());
                lock.notifyAll();
            }
            System.out.println(name + ":notified...");
        }
    }
}
