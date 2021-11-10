package com.alevelhome.juc.c001;

/**
 * 很有可能读不到别的线程修改过的内容，除了这点之外count减减完了之后下面的count输出
 * 和你减完的结果不对，很容易分析:如果有一个线程把它从10减到9了，然后又有一个线程在前
 * 面一个线程还没有输出呢进来了把9又减到了8，继续输出的8，而不是9。如果你想修正它，
 * 前面第一个是在上面加volatile，改了马上就能得到。
 */
public class T06_volatile implements Runnable {
    private /*volatile*/ int count = 100;

    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + "count=" + count);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            T06_volatile myThread = new T06_volatile();
            new Thread(myThread, "THREAD" + i).start();

        }

    }
}
