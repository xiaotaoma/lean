package com.mxt.concurrent.interrupt;

/**
 * Created by mxt on 18-3-8.
 * 阻塞线程中断
 */
public class BlockThreadInterrupt {
    private synchronized static void print() {
        for (int i = 0; i < 10; i++) {
            String name = Thread.currentThread().getName();
            System.out.println(name + "\t" + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    print();
                    System.out.println("thread0 leaving");
                }
            }
        }, "thead0");
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    print();
                    System.out.println("thread1 leaving");
                }
            }
        }, "thead1");
        thread.start();
        thread1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();
    }
}
