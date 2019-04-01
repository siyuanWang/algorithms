package com.wsy.learn.jvm.thread;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("hello MyRunnable");
    }
}
