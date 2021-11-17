package com.alevelhome.juc.c003;

/**
 * 线程判断，先别加锁，判断是否为空，如果为空在加锁初始化，更细粒度的一个锁，这叫做
 * 锁细化，也是锁优化的一步。很不幸的是这个写法是不对的，我们分析一下，第一个线程判断它为空，
 * 还没有执行下面的过程第二个线程来了，也判断它为空。第一个线程对它进行了加锁，synchronized完
 * 了之后呢把锁释放了，而第二个线程也是判断为空拿到这把锁也初始化了一遍，所以这种写法是有问题
 * 的。
 */
public class T05_Singleton {

    private static T05_Singleton T;

    private T05_Singleton() {}

    public void m() {
        System.out.println("m");
    }

    public static T05_Singleton getInstance() {
        if (T == null) {
            // 妄图通过减小同步代码块的方式提高效率，然后不可行
            synchronized (T05_Singleton.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                T = new T05_Singleton();
            }
        }
        return T;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(T05_Singleton.getInstance().hashCode());
            }).start();
        }
    }
}
