package com.wsy.learn.jvm.thread;

public class ThreadABC {


    public static void main(String[] args) throws InterruptedException {
        Object lockA = new Object();
        Object lockB = new Object();
        Object lockC = new Object();
        int time = 3;
        Thread t1 = new Thread(new ThreadAbc("A", lockA, lockB, time));
        Thread t2 = new Thread(new ThreadAbc("B", lockB, lockC, time));
        Thread t3 = new Thread(new ThreadAbc("C", lockC, lockA, time));

        t1.start();
        t2.start();
        t3.start();
        System.out.println("准备开始...");
        Thread.sleep(1000);
        System.out.println("调用开始!");
        synchronized (lockA) {
            lockA.notify();
        }
    }

    static class ThreadAbc implements Runnable {
        private String name;
        private Object lock;
        private Object nextLock;
        private int time;

        public ThreadAbc(String name, Object lock, Object nextLock, int time) {
            this.name = name;
            this.lock = lock;
            this.nextLock = nextLock;
            this.time = time;
        }

        @Override
        public void run() {
            int count = 0;
            while(count <  time) {
                try {
                    synchronized (lock) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name);
                synchronized (nextLock) {
                    nextLock.notifyAll();
                }
                count++;
            }
        }
    }
}
