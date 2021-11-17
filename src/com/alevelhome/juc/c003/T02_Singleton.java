package com.alevelhome.juc.c003;

/**
 * 这个方法的时候你再初始化
 * 静态代码块，在虚拟机加载类的时候就会加载执行，而且只执行一次;
 */
public class T02_Singleton {

    private static final T02_Singleton T;

    static {
        T = new T02_Singleton();
    }

    private T02_Singleton() {}

    public void m() {
        System.out.println("m");
    }

    public static T02_Singleton getInstance() {
        return T;
    }

    public static void main(String[] args) {
        T02_Singleton m1 = T02_Singleton.getInstance();
        T02_Singleton m2 = T02_Singleton.getInstance();
        System.out.println(m1 == m2);
    }
}
