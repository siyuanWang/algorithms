package com.wsy.learn.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.*;

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
 */
public class ReferenceStudy {

    ThreadLocal<Map<String, Byte[]>> listThreadLocal = ThreadLocal.withInitial(HashMap::new);

    Map<String, Byte[]> weakMap = new HashMap<>();
    WeakReference<HashMap<String, Byte[]>> hashMapWeakReference = new WeakReference<>(new HashMap<>());

    WeakHashMap<Integer, Byte[]> weakHashMap = new WeakHashMap<>();
    WeakHashMap<String, Byte[]> weakHashMap1 = new WeakHashMap<>();

    public void setter() {
        ReferenceQueue<Byte[]> queue = new ReferenceQueue<>();
        //1M
        for (int i = 0; i < 100; i++) {
            WeakReference<Byte[]> wr = new WeakReference<>(new Byte[1024 * 100], queue);
            weakMap.put("key" + i, wr.get());
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.gc();
            System.out.println("i=" + i);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(weakMap.get("key" + i));
        }
    }

    public void setter2() {
        HashMap<String, Byte[]> mapRef = hashMapWeakReference.get();
        if (mapRef == null) {
            System.out.println("weakly map is collected.");
        }
        for (int i = 0; i < 100; i++) {
            SoftReference<Byte[]> softReference = new SoftReference<>(new Byte[1024 * 100]);
            mapRef.put(i + "", softReference.get());
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.gc();
            System.runFinalization();
            System.out.println("i=" + i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(mapRef.get(i + "") == null);
        }
    }

    public void setter3() {
        for (int i = 0; i < 100; i++) {
            Byte[] softReference = new Byte[1024 * 50];
            String a = "ha" + i;
            weakHashMap1.put(a, softReference);
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("j=" + weakHashMap1.size());


//            System.gc();
//            System.runFinalization();
        }
//        for(int i = 0; i< 100; i++) {
//            System.out.println(weakHashMap.get(i) == null);
//        }
    }

    public void setter4() {
        List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<>();
            d.put(new byte[1][10], new byte[50][1000]);
            maps.add(d);
            //System.gc();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int j = 0; j < i; j++) {
                System.err.println(j + " size" + maps.get(j).size());
            }
        }
    }

    public static void main(String[] args) {
        ReferenceStudy study = new ReferenceStudy();
        study.setter3();

    }
}
