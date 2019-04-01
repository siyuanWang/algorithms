package com.wsy.learn.jvm.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写程序实现,子线程循环10次,接着主线程循环20次,接着再子线程循环10次,主线程循环20次,如此反复,循环50次.
 */
public class Question11 {
    private static boolean flag = false;
    Lock lock = new ReentrantLock();
    Condition con = lock.newCondition();

    void sub() throws InterruptedException {
        lock.lock();
        try {
            if (flag) {
                con.await();
            }
            System.out.println("sub");
            for (int i = 0; i < 10; i++) {
                System.out.println("exe sub." + i);
            }
            flag = true;
            con.signal();
        } finally {
            lock.unlock();
        }
    }

    void main() throws InterruptedException {
        lock.lock();
        try {
            if (!flag) {
                con.await();
            }
            System.out.println("main");
            for (int i = 0; i < 20; i++) {
                System.out.println("exe main." + i);
            }
            flag = false;
            con.signal();
        } finally {
            lock.unlock();
        }
    }


    static class Demo {
        public static void main(String[] args) {
            Question11 ques = new Question11();
            new Thread(() -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        ques.sub();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();


            for (int i = 0; i < 10; i++) {
                try {
                    ques.main();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
