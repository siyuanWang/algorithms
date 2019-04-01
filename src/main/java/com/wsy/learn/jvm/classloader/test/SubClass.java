package com.wsy.learn.jvm.classloader.test;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }
}
