package com.mxt.concurrent.executor;

import java.util.concurrent.*;

public class ExecutorTest {
    /**
     * 各种线程池，以及实现方式
     *
     */
    private void all() {
        /**
         * 是线程数量固定为1的newFixedThreadPool版本，保证池内的任务串行
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();//

        /**
         * 参数：线程池最小线程，线程池最大线程，线程空闲时间，空闲时间单位，阻塞队列
         * 内部方法new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>()
         * 会缓存的线程池
         * 线程数量可以从0到Integer.MAX_VALUE
         * 线程超时时间为60秒
         * 使用中，如果有空闲线程，会复用线程；没有空闲线程，则创建新线程。如果线程空闲一分钟，将会被回收
         *
         * 由于线程上线几乎等于无限，会将系统资源消耗尽，任务的处理速度低于任务的提交速度
         * 因此，可以为ThreadPoolExecutor提供一个阻塞队列来保存因线程不足而等待的Runnable任务,即为最后一个参数
         * 阻塞队列用来保存因线程不足而提交的任务
         *
         */
        executorService = Executors.newCachedThreadPool();

        /**
         * 固定线程数量的线程池
         * 线程池创建后，线程数量将会固定不变
         */
        executorService = Executors.newFixedThreadPool(5);

        /**
         * 创建一个可定时执行任务的线程池
         */
        executorService = Executors.newScheduledThreadPool(5);


        /**
         * 以上线程池均有下面的方法创建
         * @Param 线程池最小线程数
         * @Param 线程池最大线程数
         * @Param 线程空闲时间
         * @Param 线程空闲时间单位
         * @Param 阻塞队列  列用来保存因线程不足而提交的任务
         * 常见阻塞队列
         * ArrayBlockingQueue   数组结构的阻塞队列
         * LinkedBlockingQueue  链表结构的阻塞队列       newFixedThreadPool和newSingleThreadExecutor使用
         * PriorityBlockingQueue 有优先级的阻塞队列
         * SynchronousQueue     不会存储元素的阻塞队列   直接移交任务给线程池，不存储任务|  newCachedThreadPool使用
         *
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        threadPoolExecutor.submit(()->{
            System.out.println("task");
        });
        /**
         * 设置创建线程的工厂
         * 默认使用 DefaultThreadFactory
         */
        threadPoolExecutor.setThreadFactory(Executors.defaultThreadFactory());
        /**
         * 设置线程池的饱和策略，任务提交后保存至阻塞队列，如果阻塞队列也满了执行饱和策略
         * 默认使用
         * AbortPolicy 任务饱和后抛出异常，由调用者自己处理
         * DiscardPolicy    队列满后，后续的任务都抛弃掉
         * DiscardOldestPolicy  会将等待队列里最旧的任务踢走，让新任务得以执行。
         * CallerRunsPolicy 而是直接在当前线程运行这个任务,当前线程一般就是主线程啊，让主线程运行任务，说不定就阻塞了。如果不是想清楚了整套方案，还是少用这种策略为妙。
         */
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        threadPoolExecutor.shutdown();
    }



    public static void main(String[] args) {

    }



}
