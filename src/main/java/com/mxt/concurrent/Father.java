package com.mxt.concurrent;

/**
 * Created by mxt on 18-3-8.
 * 可重入锁
 */
public class Father {
    public synchronized void doSomething() {

    }


}

class Child extends Father {
    /**
     * 子类覆写了父类的同步方法，然后调用父类中的方法，此时如果没有可重入的锁，那么这段代码件产生死锁
     *
     * 重入的一种实现方法是，为每个锁关联一个获取计数值和一个所有者线程。
     * 当计数值为0时，这个锁就被认为是没有被任何线程所持有，当线程请求一个未被持有的锁时，JVM将记下锁的持有者，并且将获取计数值置为1，
     * 如果同一个线程再次获取这个锁，计数值将递增，而当线程退出同步代码块时，计数器会相应地递减。当计数值为0时，这个锁将被释放。
     */
    @Override
    public synchronized void doSomething() {
        super.doSomething();
    }
}
