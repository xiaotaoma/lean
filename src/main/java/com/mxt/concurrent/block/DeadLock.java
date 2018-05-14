package com.mxt.concurrent.block;

/**
 *
 * 死锁代码示例
 *
 * java 死锁产生的四个必要条件：
 * 1、互斥使用，即当资源被一个线程使用(占有)时，别的线程不能使用
 * 2、不可抢占，资源请求者不能强制从资源占有者手中夺取资源，资源只能由资源占有者主动释放。
 * 3、请求和保持，即当资源请求者在请求其他的资源的同时保持对原有资源的占有。
 * 4、循环等待，即存在一个等待队列：P1占有P2的资源，P2占有P3的资源，P3占有P1的资源。这样就形成了一个等待环路。
 *
 *
 * Created by mxt on 18-3-9.
 */
public class DeadLock {
    public DeadLock(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock("A");
        DeadLock deadLock1 = new DeadLock("B");
        Thread thread1 = new Thread(new LockA(deadLock, deadLock1),"thread A");
        Thread thread2 = new Thread(new LockA(deadLock1, deadLock),"thread B");
        /**
         * 线程A 先获取对象A的锁，在获取B的锁
         * 线程B 先获取对象B的锁，在获取A的锁
         * 特殊情况下
         * 线程A获取对象A的锁，在等待B的锁释放
         * 线程B获取对象B的锁，在等待A的锁释放
         * 此时 两条线程互相等待对方持有的锁，形成了一个等待环路，死锁产生
         */
        thread1.start();
        thread2.start();
    }

}
class LockA implements Runnable {
    private DeadLock deadLock1;
    private DeadLock deadLock2;

    public LockA(DeadLock deadLock1, DeadLock deadLock2) {
        this.deadLock1 = deadLock1;
        this.deadLock2 = deadLock2;
    }

    @Override
    public void run() {
        synchronized (deadLock1) {//获取对象A的锁
            System.out.println(Thread.currentThread() + ", lock " + deadLock1.getName());
            doSomething();
            synchronized (deadLock2) {//获取对象B的锁
                System.out.println(Thread.currentThread() + ", lock " + deadLock2.getName());
                doSomething();
            }
            System.out.println(Thread.currentThread() + ", release lock " + deadLock2.getName());
        }
        System.out.println(Thread.currentThread() + ", release lock " + deadLock1.getName());
    }

    private void doSomething(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
