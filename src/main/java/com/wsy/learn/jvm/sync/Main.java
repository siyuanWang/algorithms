package com.wsy.learn.jvm.sync;

public class Main {
    public static void main(String[] args) {
        Test test = new Test();

        MyThread t1 = new MyThread(test, "t1");
        MyThread t2 = new MyThread(test, "t2");
        Thread tt1 = new Thread(t1);
        Thread tt2 = new Thread(t2);

        tt2.start();
        tt1.start();


    }
}
