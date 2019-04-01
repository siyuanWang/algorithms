package com.wsy.learn.jvm.sync;

public class MyThread extends Thread {
    private Test test;
    private String name;

    public MyThread(Test test, String name) {
        this.test = test;
        this.name = name;
    }

    @Override
    public void run() {
//        try {
//            Thread.sleep(10);
//        } catch (Exception e) {
//
//        }
        if(name.equals("t1"))
            test.setValue(1000, name);
        else{
            System.out.println(name + " get" + test.getValue());
        }

    }
}
