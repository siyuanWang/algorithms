package com.wsy.learn.jvm.collection.queue;

public class Main {

    static void test0() {
        Productor productor = new Productor();
        Consumer consumer = new Consumer();
        Thread cusumerThread = new Thread(() -> {
            try {
                consumer.cusume();
            } catch (Exception e) {

            }

        });
        cusumerThread.start();

        try {
            productor.provide();

            cusumerThread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void test1() {
        Productor1 productor = new Productor1();
        Consumer1 consumer = new Consumer1();
        Thread cusumerThread = new Thread(() -> {
            try {
                consumer.cusume();
            } catch (Exception e) {

            }

        });
        cusumerThread.start();

        try {
            productor.provide();
            Productor1.blockQueue.offer(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void test2() {
        Boy boy = new Boy();
        boy.setCar(1);
        boy.setHouse(1);
        boy.setSingle(1);
        Girl girl = new Girl();
        Thread girlThread = new Thread(() -> {
            try {
                girl.waitLove();
            } catch (Exception e) {

            }

        });
        girlThread.start();

        try {
            Thread.sleep(1000);
            girl.boyComes(boy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        test0();

    }
}
