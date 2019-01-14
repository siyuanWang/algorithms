package com.wsy.learn.jvm.autoproxy.jdkproxy;

public class HelloImpl implements Hello {

    @Override
    public void sayHello(String str) {
        System.out.println("hello " + str);
    }
}
