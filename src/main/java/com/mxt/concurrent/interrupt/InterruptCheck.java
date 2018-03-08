package com.mxt.concurrent.interrupt;

/**
 * 没有任何语言方面的需求要求一个被中断的程序应该终止。
 * 中断一个线程只是为了引起该线程的注意，
 * 被中断线程可以决定如何应对中断
 * Created by mxt on 18-3-8.
 * 判断中断状态
 */
public class InterruptCheck {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println("point A : isInterrupted = " + thread.isInterrupted());
        //中断自身
        thread.interrupt();
        System.out.println("point B : isInterrupted = " + thread.isInterrupted());
        System.out.println("point C : isInterrupted = " + thread.isInterrupted());
        try {
            Thread.sleep(2000);
            System.out.println("not interrupted");
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println("interrupted");
        }
        System.out.println("point D : isInterrupted = " + thread.isInterrupted());
    }
}
