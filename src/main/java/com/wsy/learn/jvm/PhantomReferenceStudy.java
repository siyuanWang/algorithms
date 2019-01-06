package com.wsy.learn.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * -Xmx3M
 * -Xmn2M
 * -XX:+UseConcMarkSweepGC
 * -XX:+UseCMSCompactAtFullCollection
 * -XX:CMSFullGCsBeforeCompaction=0
 * -XX:CMSInitiatingOccupancyFraction=75
 * -XX:+PrintGCDetails
 * -XX:+PrintGCTimeStamps
 * -XX:+PrintHeapAtGC
 * -Xloggc:/export/Logs/gc.log
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=/export/Logs/gc.hprof
 */
public class PhantomReferenceStudy {

    private static ReferenceQueue<Object> queue = new ReferenceQueue<>();

    private Entry[] table;
    private int index;

    private static class Entry extends PhantomReference<Object> {
        private Byte[] bytes;
        private String key;

        Entry(String key, Byte[] value) {
            //这行代码很关键，如果虚引用是value的话，是不会加入到队列中的
            super(key, queue);
            this.bytes = value;
            //此处引用key,也会报OOM
            //this.key = key;
        }
    }

    public void add(Byte[] bytes) {
        if (table == null) {
            table = new Entry[16];
        }
        String key = "key" + index;
        table[index] = new Entry(key, bytes);
        System.out.println("add success index =" + index);
        for (Object x; (x = queue.poll()) != null; ) {
            System.out.println("queue poll,prepare GC, index = " + index);
            //需要手动置为Null，不然gc回收不掉
            //置为Null之前可以do something...
            ((Entry) x).bytes = null;
        }
        index++;
    }

    public void string() {
        if (table != null) {
            for (Entry entry : table) {
                System.out.println((entry == null));
            }
        }
    }

    public static void main(String[] args) {
        PhantomReferenceStudy study = new PhantomReferenceStudy();
        for (int i = 0; i < 16; i++) {
            //能够触发ReferenceQueue事件
            study.add(new Byte[1024 * 100]);
        }
        study.string();
    }

}
