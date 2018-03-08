package com.mxt.concurrent.interrupt;

/**
 * Created by mxt on 18-3-8.
 *
 */
public class SleepInterrupt implements Runnable{
    public void run() {
        try {
            System.out.println("in run() -- about to sleep 20 s");
            Thread.sleep(20000);
            System.out.println("in run() -- woke up");
        }catch (Exception e){
            System.out.println("in run() -- interrupt while sleeping");
            //e.printStackTrace();
            //处理完中断异常后，返回到run方法入口，
            //如果没有return，线程不会实际被中断，会继续打印下面信息
            return;
        }
        System.out.println("in run -- leaving");
    }

    public static void main(String[] args) {
        SleepInterrupt sleepInterrupt = new SleepInterrupt();
        Thread thread = new Thread(sleepInterrupt);
        thread.start();
        //主线程休眠2秒，确保子线程执行
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("in main() -- interrupt other thread");
        thread.interrupt();
        System.out.println("in main() -- leaving");
    }
}
