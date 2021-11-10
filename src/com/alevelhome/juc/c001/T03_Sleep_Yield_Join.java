package com.alevelhome.juc.c001;

import java.util.concurrent.TimeUnit;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
//        testSleep();
//        testYied();
        testJoin();
    }

    /* join， 意思就是在自己当前线程加入你调用Join的线程()，本线程等待。等调用的线程运行完了，自己再去执行。t1和t2两个线程，在t1的某个点上调用了t2.join,它会跑到t2去运行，t1等待t2运 行完毕继续t1运行(自己join自己没有意义
     */
    private static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("a:  " + i);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
//                t1.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("b--" + i);
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("c:**" + i);
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    /*
     * Yield,就是当前线程正在执行的时候停止下来进入等待队列，回到等待队列里在系统的调度算 里头呢还是依然有可能把你刚回去的这个线程拿回来继续执行，当然，更大的可能性是把原来等待的那些拿出一个来执行，所以yield的意思是我让出一下CPU，后面你们能不能抢到那我不管
     */
    private static void testYied() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("a:  " + i);
                if (i % 10 == 0) Thread.yield();
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("b--" + i);
                if (i % 10 == 0) Thread.yield();
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("index" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
