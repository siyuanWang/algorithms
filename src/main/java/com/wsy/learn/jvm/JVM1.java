package com.wsy.learn.jvm;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xmx3M
 * -Xmn2M
 * -XX:+UseConcMarkSweepGC
 * -XX:+UseCMSCompactAtFullCollection  在FULL GC的时候,对年老代的压缩
 * -XX:CMSFullGCsBeforeCompaction=0 这里设置多少次Full GC后,对年老代进行压缩
 * -XX:CMSInitiatingOccupancyFraction=75 当老年代对象达到75%的时候，触发fullGc
 * -XX:+PrintGCDetails
 * -XX:+PrintGCTimeStamps
 * -XX:+PrintHeapAtGC
 * -Xloggc:/export/Logs/gc.log
 * -XX:+HeapDumpOnOutOfMemoryError 内存溢出时自动dump
 * -XX:HeapDumpPath=/export/Logs/gc.hprof
 *
 * jmap -histo 70036 > /export/Logs/jmap2.log 输出堆中的对象列表
 * jmap -dump:live,format=b,file=/export/Logs/dump.hprof 60730 dump堆，可以用MAT分析
 */
public class JVM1 {

    private List<Student> list = new ArrayList<>();

    public void invoke(String name, int age) {
        Student stu = new Student(name, age);
        list.add(stu);
    }

    public void sync() {
        System.out.println("Main开始Sync");
        int i = 0;
        try {
            while (true) {
                invoke("name" + i, i++);
                System.out.println("创建对象success" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JVM1 jvm1 = new JVM1();
        while(true) {
            jvm1.sync();
        }
    }

}
