package com.wsy.learn.jvm.autoproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 通过jstat -class 69118 ，得到如下的数据，装载的类共有589，共计1174.1字节，而且随时时间的进行，多次执行jstat -class命令，发现装载类的数量和空间都没有变化，说明循环调用jdkProxy，也不会每次都创建一个新的类，而是
 * 使用第一次创建好的类来创建对象（Jdkproxy 创建的类的名字 com.sun.proxy.$Proxy0），所以使用JDK proxy不会造成元空间（metaspace）内存溢出。
 * Loaded  Bytes  Unloaded  Bytes     Time
 *    589  1174.1        0     0.0       0.14
 */
public class TestProxyOOM implements InvocationHandler {
    private Hello hello;

    public TestProxyOOM(Hello hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("sayHello".equals(method.getName())) {
            System.out.println("testProxy said: " + Arrays.toString(args));
        }
        Object obj = method.invoke(hello, args);

        //System.out.println("invoke end.xxxx");
        return obj;
    }

    public static void main(String[] args) {
        Hello hello = null;
        while (true) {
            hello = (Hello) Proxy.newProxyInstance(TestProxyOOM.class.getClassLoader(), // 1. 类加载器
                    new Class<?>[]{Hello.class}, // 2. 代理需要实现的接口，可以有多个
                    new TestProxyOOM(new HelloImpl()));// 3. 方法调用的实际处理者
            hello.sayHello("world hhhh");
            System.out.println(hello);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
