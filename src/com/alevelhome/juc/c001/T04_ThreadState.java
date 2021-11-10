package com.alevelhome.juc.c001;

public class T04_ThreadState {
    private static class Mythread extends  Thread {
        @Override
        public void run() {
            System.out.println("1:"+this.getState());

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread mythread = new Mythread();

        System.out.println("2:"+mythread.getState());

        mythread.start();

        System.out.println("3:"+mythread.getState());

        try {
            mythread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("4:"+mythread.getState());

    }
}
