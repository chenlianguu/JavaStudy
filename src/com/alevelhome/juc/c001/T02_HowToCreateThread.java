package com.alevelhome.juc.c001;


import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

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
            System.out.println("Thread02");
        }
    }

    private static class Thread04 implements Runnable {
        @Override
        public void run() {
            System.out.println("Thread04");
        }
    }

    private static class Thread05 implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("Thread05");
            return "return Thread05";
        }
    }

    public static void main(String[] args) {
        // 方式一
        new Thread01().start();

        // 方式二
        new Thread(new Thread02()).start();

        // 方式三
        new Thread(() -> {
            System.out.println("Thread03");
        } ).start();

        // 方式四
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Thread04());

        // 方式五
        FutureTask task = new FutureTask<String>(new Thread05());
        Thread thread04 = new Thread(task);
        thread04.start();

        try {
            String result = (String) task.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
