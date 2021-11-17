package com.alevelhome.juc.c004;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某对象o，如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生改变
 * 应该是避免将锁定对象的引用变成另外的对象
 */
public class T002 {
    Object o = new Object();
    void m() {
        synchronized (o) {
            while (true) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }


    public static void main(String[] args) {
        T002 t002 = new T002();
        new Thread(t002::m,"m1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t002.o = new Object();
        new Thread(t002::m,"m2").start();
    }
}
