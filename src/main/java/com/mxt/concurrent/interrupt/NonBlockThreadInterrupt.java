package com.mxt.concurrent.interrupt;

/**
 * Created by mxt on 18-3-8.
 * 非阻塞线程中断
 * 参考文章
 *  http://blog.csdn.net/canot/article/details/51087772 理解java线程的中断
 *  总结
 *  1、调用线程的interrupt方法，并不能真正中断线程，只是给线程做了中断状态的标志
 *  2、Thread.interrupted()：测试当前线程是否处于中断状态。执行后将中断状态标志为false
 *  3、Thread.isInterrupte()： 测试线程Thread对象是否已经处于中断状态。但不具有清除功能
 *
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
