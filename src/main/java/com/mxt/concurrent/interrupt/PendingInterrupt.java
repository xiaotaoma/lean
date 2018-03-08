package com.mxt.concurrent.interrupt;

/**
 * Created by mxt on 18-3-8.
 * 待决中断
 * sleep（）方法的实现检查到休眠线程被中断，它会相当友好地终止线程，并抛出InterruptedException异常。
 * 另外一种情况，如果线程在调用sleep（）方法前被中断，那么该中断称为待决中断，
 * 它会在刚调用sleep（）方法时，立即抛出InterruptedException异常
 */
public class PendingInterrupt {
    public static void main(String[] args) {
        /**
         * 除了将中断标志（它是Thread的内部标志）设置为true外，没有其他任何影响。
         * 线程被中断了，但main线程仍然运行，
         * main线程继续监视实时时钟，并进入try块，一旦调用sleep（）方法，
         * 它就会注意到待决中断的存在，
         * 并抛出InterruptException。于是执行跳转到catch块，并打印出线程被中断的信息。
         * 最后，计算并打印出时间差
         */
        if (args.length > 0) {//
            Thread.currentThread().interrupt();
        }

        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep(2000);
            System.out.println("was not interrupt");//没有中断
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("interrupt");//中断
        }
        System.out.println("time = " + (System.currentTimeMillis() - startTime));
    }
}
