package com.wsy.learn.worksediment;

import java.util.ArrayList;
import java.util.ListIterator;

public class CurrentModitionExceptionTest {

    class Bean {
        private int a;
        private int b;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }

    public void test4() {
        ArrayList<Bean> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Bean bean = new Bean();
            bean.setA(i);
            bean.setB(i);
            arrayList.add(bean);
        }

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ListIterator<Bean> iterator = arrayList.listIterator();
                while (iterator.hasNext()) {
                    Bean bean = iterator.next();
                    bean.setA(100 + bean.getA());
                    System.out.println("thread1 " + bean.getA());
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ListIterator<Bean> iterator = arrayList.listIterator();
                while (iterator.hasNext()) {
                    Bean bean = iterator.next();
                    bean.setB(200 + bean.getB());
                    System.out.println("thread2 " + bean.getB());
                }
            }
        });
        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        CurrentModitionExceptionTest test = new CurrentModitionExceptionTest();
        test.test4();
    }
}
