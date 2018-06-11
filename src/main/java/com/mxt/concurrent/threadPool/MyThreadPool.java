package com.mxt.concurrent.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyThreadPool {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool();
        myThreadPool.testQueue();
    }


    /**
     *
     * 测试几种提交任务队列
     * ArrayBlockingQueue   数组结构的阻塞队列
     * LinkedBlockingQueue  链表结构的阻塞队列       newFixedThreadPool和newSingleThreadExecutor使用
     * PriorityBlockingQueue 有优先级的阻塞队列
     * SynchronousQueue     不会存储元素的阻塞队列   直接移交任务给线程池，不存储任务|  newCachedThreadPool使用
     */
    private void testQueue() {
        //创建一个size为2的任务缓冲队列，
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);
        queue.add(new MyTask());

//        queue = new ArrayBlockingQueue<>(2, true);//true/false 是否启用公平锁
//
//        List<Runnable> list = new ArrayList<>();
//        list.add(new MyTask());
//        queue = new ArrayBlockingQueue<>(2, true, list);//true/false 是否启用公平锁


        //创建只有一个线程的线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, queue);
        threadPoolExecutor.submit(new MyTask());//提交一个任务，任务进去缓冲队列后，由缓冲队列移交给空闲线程处理，此时有一条空闲线程，线程处理此任务
        threadPoolExecutor.submit(new MyTask());//在提交一个任务，任务进去缓冲队列后，没有空闲线程，此任务无法处理，会一直在缓冲队列中，缓冲队列size=1
        threadPoolExecutor.submit(new MyTask());//继续提交一个任务，任务进入缓冲队列，依然没有空闲线程，无法处理。缓冲队列size=2
        threadPoolExecutor.submit(new MyTask());//继续提交一个任务，任务进入缓冲队列，依然没有空闲线程，此时缓冲队列已满，提交任务抛出异常
        System.out.println("任务排队数量：" + queue.size());
        threadPoolExecutor.shutdown();

        queue = new LinkedBlockingQueue<>();


    }

    private class MyTask implements Runnable{

        @Override
        public void run() {
            while (true) {
                //running
            }
        }
    }
}
