package com.alevelhome.juc.c003;

/**
 * 饿汉式
 * 类加载到内存后，被实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用！
 * 唯一缺点，不管用到与否，类装载时就完成实例化
 */
public class T01_Singleton {

    private static final  T01_Singleton T = new T01_Singleton();

    private T01_Singleton() {}

    public void m() {
        System.out.println("m");
    }

    public static T01_Singleton getInstance() {
        return T;
    }

    public static void main(String[] args) {
        T01_Singleton m1 = T01_Singleton.getInstance();
        T01_Singleton m2 = T01_Singleton.getInstance();
        System.out.println(m1 == m2);
    }
}
