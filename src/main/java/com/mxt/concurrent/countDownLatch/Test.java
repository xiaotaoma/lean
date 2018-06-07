package com.mxt.concurrent.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 利用CountDownLatch可以实现类似计数器的功能。
 * 比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
 */
public class Test {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(()-> {
            System.out.println("子线程"+Thread.currentThread().getName() + "正在执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程"+Thread.currentThread().getName() + "正在完毕");
            countDownLatch.countDown();
        }).start();

        new Thread(()-> {
            System.out.println("子线程"+Thread.currentThread().getName() + "正在执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程"+Thread.currentThread().getName() + "正在完毕");
            countDownLatch.countDown();
        }).start();

        try {
            System.out.println("等待两个线程执行完毕..");
            countDownLatch.await();//挂起线程，等待count的值为0，继续执行
            System.out.println("2个子线程已执行完毕，继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
