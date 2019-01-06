package com.wsy.learn.jvm;

public class ThreadLocalStudy {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void set() {
        threadLocal.set("hello thread");
    }

    public void string() {
        System.out.println("threadlocal get :" + threadLocal.get());
        System.out.println(threadLocal);
    }

    public static void main(String[] args) {
        ThreadLocalStudy threadLocalStudy = new ThreadLocalStudy();
        threadLocalStudy.set();
        threadLocalStudy.string();
    }
}
