package com.wsy.learn.jvm.sync;

public class Test {
    private volatile int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value, String name) {
        System.out.println(name + " start setValue = " + 1000);
        this.value = value;
        System.out.println(name + " end setValue = " + 1000);
    }
}
