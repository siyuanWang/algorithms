package com.wsy.learn.jvm.autoproxy.cglib;

/**
 * 动态代理不能是final类
 * 如果是final类，会抛出异常：java.lang.IllegalArgumentException: Cannot subclass final class com.wsy.learn.jvm.autoproxy.cglib.HelloConcrete
 */
public class HelloConcrete {
    /**
     * 注意，此处如果定义的是final方法，则不会进入intercept方法进行方法增强
     * @param str
     * @return
     */
    public String sayHello(String str) {
        return "HelloConcrete: " + str;
    }
}
