package com.wsy.learn.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WeakReferenceStudy {

    private static ReferenceQueue<Object> queue = new ReferenceQueue<>();

    private Entry[] table;
    private EntryNoQueue[] tableNoQueue;
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

    private static class EntryNoQueue extends WeakReference<Object> {
        private Byte[] bytes;
        private String key;

        EntryNoQueue(String key, Byte[] value) {
            //这行代码很关键，如果虚引用是value的话，是不会加入到队列中的
            super(key);
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

    /**
     * 原理和ThreadLocal 清理 ThreadLocalMap 的元素一样，防止引用对象本身内存泄漏。
     */
    public void clear() {
        if(table != null) {
//            int i = 0;
            //这种写法会发现e元素每次循环，都是同一个对象，不是每次拿最新的table[i] 赋值给 e,这是为什么呢？
//            for(Entry e = table[i]; i < table.length; i++) {
//                if(e.bytes == null) {
//                    table[i] = null;
//                }
//            }
            //思考了是不是int 放在外面就会减少i变量的创建，不过编译器也会优化，不会反复创建同一个基础数据类型
            int i= 0;
            for(; i < table.length; i++) {
                Entry e = table[i];
                if(e.bytes == null) {
                    table[i] = null;
                }
            }
        }

        if(tableNoQueue != null) {
            int i= 0;
            for(; i < tableNoQueue.length; i++) {
                EntryNoQueue e = tableNoQueue[i];
                if(e != null && e.bytes == null) {
                    tableNoQueue[i] = null;
                }
            }
        }
    }

    public void addNoQueue(Byte[] bytes) {
        if (table == null) {
            tableNoQueue = new EntryNoQueue[16];
        }
        String key = "key" + index;
        tableNoQueue[index] = new EntryNoQueue(key, bytes);
        System.out.println("add success index =" + index);
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
        if(table != null) {
            for (Entry entry : table) {
                System.out.println(entry == null);
            }
        }
        if(tableNoQueue != null) {
            for (EntryNoQueue entry : tableNoQueue) {
                System.out.println(entry == null);
                if(entry != null) {
                    System.out.println("byte length =" + entry.bytes.length);
                }
            }
        }
    }

    public static void main(String[] args) {
        WeakReferenceStudy study = new WeakReferenceStudy();
        for (int i = 0; i < 16; i++) {
            //能够触发ReferenceQueue事件
            study.add(new Byte[1024 * 34]);

            //不能触发
            //study.addAndKeyRef(new Byte[1024 * 100]);
            //
//            study.addNoQueue(new Byte[1024 * 100]);
        }
        study.clear();
        study.string();
    }

}
