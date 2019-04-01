package com.wsy.learn.jvm.thread;

/**
 * 编写程序实现,子线程循环10次,接着主线程循环20次,接着再子线程循环10次,主线程循环20次,如此反复,循环50次.
 */
public class Question1 {
    private static boolean flag = false;

    synchronized void sub() throws InterruptedException {
        if (flag) {
            this.wait();
        }
        System.out.println("sub");
        for (int i = 0; i < 10; i++) {
            System.out.println("exe sub." + i);
        }
        flag = true;
        this.notify();

    }

    synchronized void main() throws InterruptedException {
        if (!flag) {
            this.wait();
        }
        System.out.println("main");
        for (int i = 0; i < 20; i++) {
            System.out.println("exe main." + i);
        }
        flag = false;
        this.notify();
    }


    static class Demo {
        public static void main(String[] args) {
            Question1 ques = new Question1();
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
