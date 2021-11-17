package com.alevelhome.juc.c003;

/**
 * 线程判断，先别加锁，判断是否为空，如果为空在加锁初始化，更细粒度的一个锁，这叫做
 * 锁细化，也是锁优化的一步。很不幸的是这个写法是不对的，我们分析一下，第一个线程判断它为空，
 * 还没有执行下面的过程第二个线程来了，也判断它为空。第一个线程对它进行了加锁，synchronized完
 * 了之后呢把锁释放了，而第二个线程也是判断为空拿到这把锁也初始化了一遍，所以这种写法是有问题
 * 的。
 */
public class T06_Singleton {

    private static volatile T06_Singleton T;

    private T06_Singleton() {}

    public void m() {
        System.out.println("m");
    }

    public static T06_Singleton getInstance() {
        if (T == null) {
            // 双重检查
            synchronized (T06_Singleton.class) {
                if (T == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    T = new T06_Singleton();
                }
            }
        }
        return T;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(T06_Singleton.getInstance().hashCode());
            }).start();
        }
    }
}
