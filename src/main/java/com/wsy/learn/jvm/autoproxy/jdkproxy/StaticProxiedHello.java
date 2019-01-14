package com.wsy.learn.jvm.autoproxy.jdkproxy;

/**
 * 静态代理
 */
public class StaticProxiedHello implements Hello {

    Hello hello = new HelloImpl();

    @Override
    public void sayHello(String str) {
        hello.sayHello(str);
    }
}
