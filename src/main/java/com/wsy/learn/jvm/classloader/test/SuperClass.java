package com.wsy.learn.jvm.classloader.test;

public class SuperClass {
    static {
        System.out.println("SuperClass init");
    }
    public static int value = 123;
}
