package com.wsy.learn.jvm.classloader.test;

/**
 * -XX:+TraceClassLoading
 * -XX:+TraceClassUnloading
 */
public class NotInitialization {
    public static void main(String[] args) throws Exception {
        //newarray jvm指令，创建了一个SuperClass类的一维数组 [Lcom.wsy.learn.jvm.classloader.test.SuperClass
        //通过数组定义来引用类，不会触发此类的初始化
//        SuperClass[] scaArray = new SuperClass[10];
        //SuperClass sca = new SuperClass();
        System.out.println(SuperClass.value);

        while (true) {
            Thread.sleep(3000);
            System.out.println("waiting");
        }
    }
}
