package com.wsy.learn.jvm.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadABCD {
    static ReentrantLock lock = new ReentrantLock();
    static Condition A = lock.newCondition();
    static Condition B = lock.newCondition();
    static Condition C = lock.newCondition();
    public static int state = 0;

    public static void main(String[] args) {
        int num = 10;
        Thread t1 = new Thread(new ThreadAbc("A", num));
        Thread t2 = new Thread(new ThreadAbc("B", num));
        Thread t3 = new Thread(new ThreadAbc("C", num));

        t1.start();
        t2.start();
        t3.start();


    }

    static class ThreadAbc implements Runnable {
        private String name;
        private Integer num;

        public ThreadAbc(String name, int num) {
            this.name = name;
            this.num = num;
        }

        @Override
        public void run() {
            try {
                int count = 0;
                while(count < num) {
                    if("A".equals(name) && state % 3 == 0) {
                        B.await();
                    } else if("B".equals(name) && state % 3 == 1) {
                        C.await();
                    } else {
                        A.await();
                    }
                    System.out.println(name);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
