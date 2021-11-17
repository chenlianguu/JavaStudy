package com.alevelhome.juc.c003;

/**
 * 懒汉式
 * 非线程安全
 */
public class T03_Singleton {

    private static T03_Singleton T;

    private T03_Singleton() {}

    public void m() {
        System.out.println("m");
    }

    public static T03_Singleton getInstance() {
        if (T == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            T = new T03_Singleton();
        }
        return T;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(T03_Singleton.getInstance().hashCode());
            }).start();
        }
    }
}
