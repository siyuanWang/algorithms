package com.wsy.learn.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WeakReferenceStudy {

    private static ReferenceQueue<Object> queue = new ReferenceQueue<>();

    private Entry[] table;
    private int index;

    private List<String> keys = new ArrayList<>();

    private static class Entry extends WeakReference<Object> {
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
            ((Entry) x).bytes = null;
        }
        index++;
    }

    public void addAndKeyRef(Byte[] bytes) {
        if (table == null) {
            table = new Entry[16];
        }
        String key = "key" + index;
        table[index] = new Entry(key, bytes);
        keys.add(key);
        System.out.println("add success index =" + index);
        for (Object x; (x = queue.poll()) != null; ) {
            System.out.println("queue poll,prepare GC, index = " + index);
            ((Entry) x).bytes = null;
        }


        index++;
    }

    public void string() {
        for (Entry entry : table) {
            System.out.println(entry == null);
        }
    }

    public static void main(String[] args) {
        WeakReferenceStudy study = new WeakReferenceStudy();
        for (int i = 0; i < 16; i++) {
            //能够触发ReferenceQueue事件
            study.add(new Byte[1024 * 100]);
            //不能触发
            //study.addAndKeyRef(new Byte[1024 * 100]);
        }
        study.string();
    }

}
