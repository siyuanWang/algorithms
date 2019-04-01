package com.wsy.learn.designmodel;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        SingletonLazy1.helloworld();
        Object lock = new Object();
        synchronized (lock) {
            lock.wait(1000);
        }
        System.out.println("分割线---------------");
        System.out.println(SingletonLazy1.getInstance());
    }
}
