package com.wsy.learn.jvm.classloader.test;

public class Grandpa {
    static {
        System.out.println("爷爷在静态代码块");
    }
}
