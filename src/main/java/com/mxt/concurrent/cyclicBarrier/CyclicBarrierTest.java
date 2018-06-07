package com.mxt.concurrent.cyclicBarrier;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 字面意思回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。我们暂且把这个状态就叫做barrier，
 * 当调用await()方法之后，线程就处于barrier了。
 * 例子1
 * 假若有若干个线程都要进行写数据操作，并且只有所有线程都完成写数据操作之后，这些线程才能继续做后面的事情，此时就可以利用CyclicBarrier了：
 */
public class CyclicBarrierTest {


    private static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        private long sleepTime;
        public Writer(CyclicBarrier cyclicBarrier, long sleepTime) {
            this.cyclicBarrier = cyclicBarrier;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName() + " 正在写入数据...");
            try {
                Thread.sleep(sleepTime);
                System.out.println("线程"+Thread.currentThread().getName() + " 写入数据完毕，等待其他线程写入完毕...");
                cyclicBarrier.await();

                //await指定时间,任务完成后等待指定时间，等待时候过后如果仍让有线程未到达barrier，则抛出异常，执行后面的任务
                //cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println("线程"+Thread.currentThread().getName() + " 所有线程写入完毕，继续处理其他任务...");
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(5, ()->{
            System.out.println("当前线程"+Thread.currentThread().getName() + ",执行额外的任务");
        });//所有任务结束后，选择一个线程执行额外的任务
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int i1 = random.nextInt(1000);
            i1 = i1 + 5000;
            /*if (i == 4) {//最后一个线程延迟启动
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
            new Writer(cyclicBarrier, i1).start();
        }

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CyclicBarrier重用");

        for (int i = 0; i < 5; i++) {
            int i1 = random.nextInt(1000);
            i1 = i1 + 5000;
            /*if (i == 4) {//最后一个线程延迟启动
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/
            new Writer(cyclicBarrier, i1).start();
        }
    }

}
