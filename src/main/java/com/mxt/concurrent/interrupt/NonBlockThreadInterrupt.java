package com.mxt.concurrent.interrupt;

/**
 * Created by mxt on 18-3-8.
 * 非阻塞线程中断
 */
public class NonBlockThreadInterrupt {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            public void run() {
                //非阻塞线程的中断,子线程中短状态被改变,但是子线程仍在执行，并未结束
                //可以主动判断子线程的中断状态，来决定是否结束执行
                if (!Thread.currentThread().isInterrupted()) {
                    doSomething();
                }
                System.out.println("run leaving");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("中断线程");
        thread.interrupt();
        System.out.println("main leaving");
    }

    private static void doSomething() {
        int n = 0;
        while (true) {
            n++;
            System.out.println("run " + n);
        }
    }
}
