package com.wsy.learn.jvm.thread;

public class Main {
    private static Object lock = new Object();

    public static void main(String[] args) {
        try {
            testNotify();

            String s = "flight";
            int count = s.indexOf("fl");

            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void testNotify() throws Exception {
        MyThread t1 = new MyThread(lock, "t1");
        MyThread t2 = new MyThread(lock, "t2");

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);

        thread1.start();
        thread2.start();

        Thread.sleep(1000);
        long startTime = System.currentTimeMillis();
        synchronized (lock) {
            System.out.println("main get lock");
            lock.notifyAll();
        }

        thread1.join();
        thread2.join();
        long endTime = System.currentTimeMillis();

        System.out.println("notify lock.time =" + (endTime - startTime));
    }

    static void testInterupt() throws Exception {
        MyThread t1 = new MyThread(lock, "t1");
        MyThread t2 = new MyThread(lock, "t2");

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);

        thread1.start();
        thread2.start();

        Thread.sleep(2000);
        thread1.interrupt();
    }

    /**
     * 调用interrupt之后，线程终止
     * @throws Exception
     */
    static void testInterupt0() throws Exception {
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("loop" + i++);
            if(i == 10) {
                Thread.currentThread().interrupt();
            }
        }
        //echo true
        System.out.println(Thread.currentThread().isInterrupted());
        //echo true
        System.out.println(Thread.currentThread().interrupted());
        //echo false
        System.out.println(Thread.currentThread().isInterrupted());

        System.out.println(Thread.currentThread().isAlive());
    }

    static void testInterupt3() throws Exception {
        MyThread1 t1 = new MyThread1(lock, "t1");

        Thread thread1 = new Thread(t1);

        thread1.start();

        Thread.sleep(100);
        thread1.interrupt();
    }

    static void testInterupt4() {
        Thread.currentThread().interrupt();
    }
}
