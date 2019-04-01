package com.wsy.learn.designmodel;

/**
 * 饿汉式
 */
public class Singleton  {
    private static final Singleton INSTANCE = new Singleton();
    private Singleton(){}

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
