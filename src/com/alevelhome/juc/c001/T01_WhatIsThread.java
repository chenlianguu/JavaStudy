package com.alevelhome.juc.c001;

import java.util.concurrent.TimeUnit;

/**
 * 知识点：
 * 1 run与start区别
 * 2 线程执行顺序
 */
public class T01_WhatIsThread {
    public static class MyThread01 extends Thread {
        @Override
        public void run() {
            for(int i=0; i<500; i++) {
                System.out.println("T1");
            }
        }
    }

    public static class MyThread02 extends Thread {
        @Override
        public void run() {
            for(int i=0; i<500; i++) {
                System.out.println("T2");
            }
        }
    }

    public static void main(String[] args) {
        new MyThread01().run();
        new MyThread02().start();

        for(int i=0; i<500; i++) {
            System.out.println("main");
        }
    }
}
