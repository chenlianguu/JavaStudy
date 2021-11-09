package com.alevelhome.juc.c001;


/**
 * 创建线程的5类方式：
 * 1 继承thread类
 * 2 实现runnable接口
 * 3 lamda表达式
 * 4 线程池方式
 * 5 futuretask方式
 */
public class T02_HowToCreateThread {
    private static class Thread01 extends Thread {
        @Override
        public void run() {
            System.out.println("Thread01");
        }
    }

    private static class Thread02 implements Runnable {
        @Override
        public void run() {
            System.out.println("Thead02");
        }
    }

    public static void main(String[] args) {
        // 方式一

    }
}
