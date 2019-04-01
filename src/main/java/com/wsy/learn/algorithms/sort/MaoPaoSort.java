package com.wsy.learn.algorithms.sort;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class MaoPaoSort {
    public static void main(String[] args) {
        int[] a = {35, 99, 18, 12, 76};
        sort(a);
        List<Entry> list = new ArrayList<>();
        list.add(new Entry(35));
        list.add(new Entry(99));
        list.add(new Entry(18));
        list.add(new Entry(12));
        list.add(new Entry(76));
        sort(list);
        descSort(list);
    }

    static void sort(int[] a) {
        int temp = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }

    static void sort(List<Entry> entry) {
        int size = entry.size();
        Entry e = null;
        for(int i = 0; i < size - 1; i++) {
            for(int j = 0; j < size - 1 - i; j++) {
                if(entry.get(j).value > entry.get(j + 1).value) {
                    //大的
                    e = entry.get(j);
                    entry.set(j, entry.get(j + 1));
                    entry.set(j + 1, e);
                }
            }
        }

        System.out.println(JSON.toJSONString(entry));
    }

    static void descSort(List<Entry> entry) {
        int size = entry.size();
        Entry e = null;
        for(int i = 0; i < size - 1; i++) {
            for(int j = 0; j < size - 1 - i; j++) {
                if(entry.get(j).value < entry.get(j + 1).value) {
                    //小的
                    e = entry.get(j);
                    entry.set(j, entry.get(j + 1));
                    entry.set(j + 1, e);
                }
            }
        }

        System.out.println(JSON.toJSONString(entry));
    }

    static class Entry {
        int value;

        public Entry(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
