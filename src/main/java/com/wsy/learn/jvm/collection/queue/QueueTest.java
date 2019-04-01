package com.wsy.learn.jvm.collection.queue;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueTest {
    static LinkedList<Integer> queue = new LinkedList<>();
    static LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>(5);

    static void test0() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);

        System.out.println(queue.poll());
        System.out.println(queue.peek());

        System.out.println(queue.peekLast());
    }

    /**
     * 在限制capacity时，offer 不会抛出异常，而是返回false. add 会抛出 java.lang.IllegalStateException: Queue full
     */
    static void test1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(linkedBlockingQueue.offer(i));
        }
        while (linkedBlockingQueue.size() > 0) {
            System.out.println(linkedBlockingQueue.poll());
        }
    }

    /**
     * 队列为空时，返回null，不阻塞
     */
    static void test2() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println(linkedBlockingQueue.offer(i));
        }
        int i = 0;
        while (i++ < 10) {
            //poll 指定timeout则阻塞且最长为设定的时间
//            System.out.println(linkedBlockingQueue.poll(1, TimeUnit.SECONDS));
            //poll 不指定timeout则不阻塞直接返回
//            System.out.println(linkedBlockingQueue.poll());
            //take 则一直阻塞，直到队列中有新的元素进入
            System.out.println(linkedBlockingQueue.take());
        }
    }

    public static void main(String[] args) {
        try {
            test2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
