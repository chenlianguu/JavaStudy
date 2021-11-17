package com.alevelhome.juc.c004;

/**
 * volatile并不能保证多个线程共同修改running变量时所带来的的不一致问题，也就是说volatile不能
 * 替代synchronized
 */
public class T001 {
    volatile int count = 0;

    void m() {
        for (int i = 0; i < 10000; i++) {
            synchronized (this) {
                count++;
            }
        }
    }

    public static void main(String[] args) {
        T001 t = new T001();

        for (int i = 0; i < 10; i++) {
            new Thread(t::m,""+i).start();
        }

        System.out.println(t.count);
    }
}
