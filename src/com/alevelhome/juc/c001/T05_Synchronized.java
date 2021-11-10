package com.alevelhome.juc.c001;

/**
 * 上锁方式：
 * 1 普通方法
 * 2 static方式（）
 *   考虑一下这里写synchronized(this)是否可以?
 *
 * 关键特性：可重入
 *     如果是一个同步方法调用另外一个同步方法，有一个方法加了锁，另外一个方法也需要加锁，加的是同一把锁也是同一个线程，
 *     那这个时候申请仍然会得到该对象的锁。比如说是synchronized可重入的，有一个方法m1 是synchronized有一个方法m2
 *     也是synchrionzed，m1里能不能调m2。我们m1开始的时 候这个线程得到了这把锁，然后在m1里面调用m2，如果说这个时候
 *     不允许任何线程再来拿这把锁的时候就死锁了。这个时候调m2它发现是同一个线程，因为你m2也需要申请这把锁，它发现是同
 *     一个线程 申请的这把锁，允许，可以没问题，这就叫可重入锁。
 */
public class T05_Synchronized {

    private static  int count = 10;
    private static Object o = new Object();

    public synchronized static void m1() {

            count--;
            System.out.println(Thread.currentThread().getName() + "count: = " + count);

    }

    public static void m2() {
        synchronized (T05_Synchronized.class) {
            count--;
            System.out.println(Thread.currentThread().getName() + "count: = " + count);

        }
    }
}
