package com.wsy.learn.jvm.classloader;

import java.lang.reflect.Method;

public class Test {

    /**
     * 需要注意的是，需要把Simple类，放到AppClassLoader找不到的地方
     * 放到了/Users/wangsiyuan1/Desktop/路径下，就会抛出com.wsy.learn.jvm.classloader.Sample cannot be cast to com.wsy.learn.jvm.classloader.Sample异常
     * 因为两个对象的classLoader不是一个对象
     *
     * 当把Sample类加入到工程中的时候，class1和class2都的classLoader都变成了AppClassLoader，故而class1 == class2
     */
    public void testClassIdentity() {
        //String classDataRootPath = "/Users/wangsiyuan1/workspace/springtest/src/main/java/com/wsy/learn/jvm/classloader";
        String classDataRootPath = "/Users/wangsiyuan1/Desktop/";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        //String className = "com.wsy.learn.jvm.classloader.Sample";
        String className = "com.wsy.learn.jvm.classloader.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            System.out.println(class1);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            System.out.println(class2);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            System.out.println(class1 == class2);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Test().testClassIdentity();
    }
}
