package com.wsy.learn.designmodel;

/**
 * 懒汉式-内部类
 */
public class SingletonLazy1 {


    private SingletonLazy1() {
    }

    private static class InnerSingleton {
        private final static SingletonLazy1 instance = new SingletonLazy1();
    }


    public static SingletonLazy1 getInstance() {

        return InnerSingleton.instance;
    }

    public static void helloworld() {
        System.out.println("hello lazy singleton.");
    }
}
