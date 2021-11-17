package com.alevelhome.juc.c003;

/**
 * 懒汉式
 * 虽然达到了按需初始化的目的，但却能带来线程不安全的问题
 * 可以通过synchronized解决，但也带来了效率下降
 */
public class T04_Singleton {

    private static T04_Singleton T;

    private T04_Singleton() {}

    public void m() {
        System.out.println("m");
    }

    public static synchronized T04_Singleton getInstance() {
        if (T == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            T = new T04_Singleton();
        }
        return T;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(T04_Singleton.getInstance().hashCode());
            }).start();
        }
    }
}
