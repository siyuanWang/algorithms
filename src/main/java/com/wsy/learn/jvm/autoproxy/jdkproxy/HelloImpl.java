package com.wsy.learn.jvm.autoproxy.jdkproxy;

public final class HelloImpl implements Hello {
    private int[] array;

    public HelloImpl() {
        this.array = new int[1024*10];
    }

    @Override
    public void sayHello(String str) {
        System.out.println("hello " + str);
    }
}
