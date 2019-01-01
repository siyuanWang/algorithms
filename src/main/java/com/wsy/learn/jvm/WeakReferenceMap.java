package com.wsy.learn.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class WeakReferenceMap<K, V> {



    private static class Entry<K, V> extends WeakReference<Object> {
        private V value;
        private int hash;
        private Entry<K, V> next;
        Entry(K key, V value,  ReferenceQueue<Object> queue, int hash, Entry<K, V> next) {
            super(key, queue);
            this.value = value;
            this.hash = hash;
            this.next = next;
        }
    }

    @SuppressWarnings("unchecked")
    private Entry<K, V>[] getTable() {
        return (Entry<K,V>[]) new Entry<?, ?>[16];
    }
}
